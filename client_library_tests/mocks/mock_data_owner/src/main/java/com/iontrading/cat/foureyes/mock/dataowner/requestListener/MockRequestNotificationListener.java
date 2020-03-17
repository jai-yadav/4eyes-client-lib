package com.iontrading.cat.foureyes.mock.dataowner.requestListener;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.iontrading.cat.foureyes.client_lib.api.FourEyesService;
import com.iontrading.cat.foureyes.client_lib.exceptions.UnsupportedOperationException;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesNotificationListener;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.cat.foureyes.mock.dataowner.DataOwnerBehaviour;
import com.iontrading.cat.foureyes.mock.dataowner.annotation.DataOwner;
import com.iontrading.cat.foureyes.mock.dataowner.storage.Storage;
import com.iontrading.cat.foureyes.mock.dataowner.xrs.NotificationDispatcher;
import com.iontrading.isf.executors.spi.ISequentialExecutorService;
import com.iontrading.isf.trace.ITracer;

public class MockRequestNotificationListener implements FourEyesNotificationListener {

	private final ITracer tracer;
	private final NotificationDispatcher dispatcher;
	private final Storage storage;
	private final FourEyesService fourEyesService;
	private static final String LOG_KEY = "MockRequestNotificationListener";
	private ISequentialExecutorService executorService;

	@Inject
	public MockRequestNotificationListener(@DataOwner ITracer tracer, NotificationDispatcher dispatcher,
			Storage storage, FourEyesService fourEyesService,
			@DataOwner Provider<ISequentialExecutorService> executorsProvider) {
		this.tracer = tracer;
		this.dispatcher = dispatcher;
		this.storage = storage;
		this.fourEyesService = fourEyesService;
		executorService = executorsProvider.get();
	}

	@Override
	public void onResyncStart() {
		tracer.INFO().key(LOG_KEY).action("Download Started").end();

	}

	@Override
	public void onResyncEnd() {
		tracer.INFO().key(LOG_KEY).action("Download Ended").end();

	}

	@Override
	public void onDisconnected() {
		tracer.INFO().key(LOG_KEY).action("Disconnected").end();

	}

	@Override
	public void onRequestPending(FourEyesRequest request) {
		tracer.INFO().key(LOG_KEY).action("Notification recieved for pending request")
				.token("RequestId", request.getRequestId()).end();
		storage.addOrUpdateRequest(request);
		dispatcher.notifyAddOrUpdate(request);
	}

	@Override
	public void onRequestApproved(FourEyesRequest request) {
		tracer.INFO().key(LOG_KEY).action("Notification recieved for approved request")
				.token("RequestId", request.getRequestId()).end();
		storage.addOrUpdateRequest(request);
		dispatcher.notifyAddOrUpdate(request);
		if (DataOwnerBehaviour.isAcknowledgementEnabled) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					if (DataOwnerBehaviour.isChangesToBeProcessed) {
						fourEyesService.acknowledgeUpdate(request, "Processed Approval");
					} else {
						try {
							fourEyesService.denyUpdate(request, "Denied Approval");
						} catch (UnsupportedOperationException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
	}

	@Override
	public void onRequestRejected(FourEyesRequest request) {
		tracer.INFO().key(LOG_KEY).action("Notification recieved for rejected request")
				.token("RequestId", request.getRequestId()).end();
		storage.addOrUpdateRequest(request);
		dispatcher.notifyAddOrUpdate(request);
		if (DataOwnerBehaviour.isAcknowledgementEnabled) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					fourEyesService.acknowledgeUpdate(request, "Processed Rejection");
				}
			});
		}
	}

	@Override
	public String getEntityType() {
		return "MockEntity";
	}

}
