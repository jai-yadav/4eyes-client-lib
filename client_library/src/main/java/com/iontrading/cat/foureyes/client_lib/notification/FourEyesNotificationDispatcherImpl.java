package com.iontrading.cat.foureyes.client_lib.notification;

import java.util.Arrays;

import javax.inject.Inject;

import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import com.iontrading.cat.foureyes.client_lib.annotation.FourEyes;
import com.iontrading.cat.foureyes.client_lib.annotation.ProtocolVersion;
import com.iontrading.cat.foureyes.client_lib.bus.NotificationFunctions;
import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.cat.foureyes.client_lib.guice.FourEyesNotificationModule;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesNotificationListener;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.isf.commons.callback.Callback;
import com.iontrading.isf.configuration.spi.ConfigurationValueProvider;
import com.iontrading.isf.dependency_manager.api.DependencyChangeEvent;
import com.iontrading.isf.dependency_manager.api.DependencyManager;
import com.iontrading.isf.dependency_manager.api.DependencyObserver;
import com.iontrading.isf.executors.spi.ISequentialExecutorService;
import com.iontrading.isf.trace.ITracer;
import com.iontrading.talk.functions.spi.Importer;
import com.iontrading.talk.ionbus.mqsubscriber.spi.MQSubscriber;
import com.iontrading.talk.ionbus.mqsubscriber.spi.MQSubscription;
import com.iontrading.talk.ionbus.mqsubscriber.spi.event.MQSubscriptionDataEvent;
import com.iontrading.talk.ionbus.mqsubscriber.spi.event.MQSubscriptionErrorEvent;
import com.iontrading.talk.ionbus.mqsubscriber.spi.event.MQSubscriptionEvent;
import com.iontrading.talk.ionbus.mqsubscriber.spi.event.MQSubscriptionMessageEvent;
import com.iontrading.talk.ionbus.mqsubscriber.spi.event.MQSubscriptionOptionalDataEvent;
import com.iontrading.talk.ionbus.mqsubscriber.spi.listener.MQListenerAdapter;
import com.iontrading.xrs.api.client.XrsContextService;
import com.iontrading.xrs.api.client.XrsContextServiceDependency;

public class FourEyesNotificationDispatcherImpl implements FourEyesNotificationDispatcher, DependencyObserver {

	private final FourEyesNotificationListener listener;
	private final ISequentialExecutorService executor;
	private final ITracer tracer;
	private final Importer importer;
	private final MQSubscriber subscriber;
	private final Integer protocolVersion;
	private final String entityType;
	private MQSubscription subscription;
	private ConfigurationValueProvider configurationProvider;

	private static final String LOG_KEY = "FourEyesNotificationDispatcher";
	private DependencyManager dependencyManager;

	@Inject
	public FourEyesNotificationDispatcherImpl(@Assisted FourEyesNotificationListener listener,
			@Assisted("Entity_Type") String entityType,
			@FourEyes Provider<ISequentialExecutorService> executorsProvider, @FourEyes ITracer tracer,
			Importer importer, MQSubscriber subscriber, @ProtocolVersion Integer protocolVersion,
			ConfigurationValueProvider configurationProvider, DependencyManager dependencyManager) {
		this.listener = listener;
		this.entityType = entityType;
		this.tracer = tracer;
		this.importer = importer;
		this.subscriber = subscriber;
		this.protocolVersion = protocolVersion;
		this.configurationProvider = configurationProvider;
		this.dependencyManager = dependencyManager;
		this.executor = executorsProvider.get();
	}

	@Override
	public void start() {
		dependencyManager.addInterest(this,
				new XrsContextServiceDependency(FourEyesNotificationModule.SUBMITER_NOTIFICATION_SERVICE));

	}

	@Override
	public void stop() {
		dependencyManager.removeInterest(this);
	}

	@Override
	public void onChange(DependencyChangeEvent event) {
		if (event.getChangedDependency().isAvailable()) {
			Object dataOwnerComponentName = configurationProvider.getPropertyValue("component");

			if (dataOwnerComponentName == null) {
				tracer.ERROR().key(LOG_KEY).action("Unable to find component name").end();
				return;
			}

			XrsContextService contextService = XrsContextServiceDependency
					.getContextService(FourEyesNotificationModule.SUBMITER_NOTIFICATION_SERVICE, event);
			String serverSource = contextService.getServiceDetails().getSource();

			NotificationFunctions xrs = importer.importFunctions(NotificationFunctions.class, serverSource);

			xrs.searchRequests(protocolVersion, dataOwnerComponentName.toString(), entityType,
					Arrays.asList(RequestStatus.Pending, RequestStatus.Approved, RequestStatus.Rejected))
					.addCallback(new Callback<String>() {

						@Override
						public void onSuccess(String queueName) {
							stop();
							subscribeAndNotify(queueName);
						}

						@Override
						public void onFailure(Throwable error) {
							tracer.ERROR().key(LOG_KEY).action("Function call to search four eyes requests failed")
									.throwable(error).end();
							start();
						}
					});
		} else {
			tracer.INFO().key(LOG_KEY).action("Xrs context not available").token("Entity", entityType)
					.token("Context Service", FourEyesNotificationModule.SUBMITER_NOTIFICATION_SERVICE).end();
		}
	}

	private void subscribeAndNotify(final String queueName) {
		FourEyesRequestNotifierImpl notifier = new FourEyesRequestNotifierImpl(listener, executor, tracer);
		subscription = subscriber.subscribe(queueName, FourEyesRequestImpl.class)
				.withListener(new MQListenerAdapter<FourEyesRequest>() {
					@Override
					public void onAdd(MQSubscriptionDataEvent<FourEyesRequest> event) {
						FourEyesRequest data = event.getData();
						logEvent(queueName, data, "Add");
						notifier.notifyRequest(data);
					}

					@Override
					public void onUpdate(MQSubscriptionDataEvent<FourEyesRequest> event) {
						FourEyesRequest data = event.getData();
						logEvent(queueName, data, "Update");
						notifier.notifyRequest(data);
					}

					private void logEvent(final String queueName, FourEyesRequest data, String action) {
						tracer.INFO().key(LOG_KEY).action("MQ Event").token("Action", action)
								.token("QueueName", queueName).token("RequestId", data.getRequestId())
								.token("RequestExternalId", data.getExternalId())
								.token("RequestStatus", data.getStatus()).end();
					}

					@Override
					public void onDelete(MQSubscriptionOptionalDataEvent<FourEyesRequest> event) {
						tracer.DEBUG().key(LOG_KEY).action("MQ Event").token("Action", "Delete")
								.token("QueueName", queueName).token("RecordName", event.getRecordName()).end();
					}

					@Override
					public void onUserAction(MQSubscriptionMessageEvent event) {
						String xrsState = event.getMessage();
						tracer.INFO().key(LOG_KEY).action("Query Status Transition").token("QueueName", queueName)
								.token("Status", xrsState).end();

						if (xrsState.startsWith("2:Stale")) {
							cancelSubscriptionAndRestartMonitoring(notifier);
						} else if (xrsState.startsWith("14:DOWNLOAD_START")) {
							notifier.notifyResyncStart();
						} else if (xrsState.startsWith("15:DOWNLOAD_END")) {
							notifier.notifyResyncEnd();
						} else {
							tracer.DEBUG().key(LOG_KEY).action("Ignoring Query Status Transition")
									.token("QueueName", queueName).token("Status", xrsState).end();
						}
					}

					@Override
					public void onError(MQSubscriptionErrorEvent event) {
						tracer.INFO().key(LOG_KEY).action("MQ Error").token("QueueName", queueName)
								.token("Error", event.getCause().getMessage()).end();

						cancelSubscriptionAndRestartMonitoring(notifier);
					}

					@Override
					public void onConnected(MQSubscriptionEvent event) {
						tracer.INFO().key(LOG_KEY).action("MQ Connected").token("QueueName", queueName).end();
					}

					@Override
					public void onDisconnected(MQSubscriptionEvent event) {
						tracer.INFO().key(LOG_KEY).action("MQ Disconnected").token("QueueName", queueName).end();

						cancelSubscriptionAndRestartMonitoring(notifier);
					}
				}).ignoreDataOnDeleteNotification().start();
	}

	private void cancelSubscriptionAndRestartMonitoring(FourEyesRequestNotifierImpl notifier) {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				notifier.notifyDisconnected();

				if (subscription != null) {
					subscription.cancel();
					subscription = null;
					start();
				}
			}
		});
	}

}
