package com.iontrading.cat.foureyes.client_lib.notification;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesNotificationListener;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.isf.executors.spi.ISequentialExecutorService;
import com.iontrading.isf.trace.ITracer;

public class FourEyesRequestNotifierImpl implements FourEyesRequestNotifier {

	private final FourEyesNotificationListener listener;
	private final ISequentialExecutorService executor;
	private final ITracer tracer;
	private static final String LOG_KEY = "FourEyesNotifier";

	public FourEyesRequestNotifierImpl(FourEyesNotificationListener listener, ISequentialExecutorService executor,
			ITracer tracer) {
		this.listener = listener;
		this.executor = executor;
		this.tracer = tracer;
	}

	@Override
	public void notifyRequest(FourEyesRequest request) {
		RequestStatus status = request.getStatus();
		switch (status) {
		case Approved:
			this.notifyApproved(request);
			break;
		case Pending:
			this.notifyPending(request);
			break;
		case Rejected:
			this.notifyRejected(request);
			break;
		default:
			break;
		}
	}

	@Override
	public void notifyPending(final FourEyesRequest request) {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				listener.onRequestPending(request);
				tracer.INFO().key(LOG_KEY).action("Notified Pending Request").token("RequestId", request.getRequestId())
						.token("Listener Entity Name", listener.getEntityType()).end();
			}
		});
	}

	@Override
	public void notifyApproved(final FourEyesRequest request) {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				listener.onRequestApproved(request);
				tracer.INFO().key(LOG_KEY).action("Notified Approved Request")
						.token("RequestId", request.getRequestId())
						.token("Listener Entity Name", listener.getEntityType()).end();
			}
		});
	}

	@Override
	public void notifyRejected(final FourEyesRequest request) {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				listener.onRequestRejected(request);
				tracer.INFO().key(LOG_KEY).action("Notified Rejected Request")
						.token("RequestId", request.getRequestId())
						.token("Listener Entity Name", listener.getEntityType()).end();
			}
		});
	}

	@Override
	public void notifyResyncStart() {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				listener.onResyncStart();
				tracer.INFO().key(LOG_KEY).action("Download Started")
						.token("Listener Entity Name", listener.getEntityType()).end();
			}
		});
	}

	@Override
	public void notifyResyncEnd() {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				listener.onResyncEnd();
				tracer.INFO().key(LOG_KEY).action("Download Ended")
						.token("Listener Entity Name", listener.getEntityType()).end();
			}
		});
	}

	@Override
	public void notifyDisconnected() {
		listener.onDisconnected();
		tracer.INFO().key(LOG_KEY).action("Disconnected").token("Listener Entity Name", listener.getEntityType()).end();
	}

}
