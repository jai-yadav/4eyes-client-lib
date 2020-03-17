package com.iontrading.cat.foureyes.mock.server.submitter;

import javax.inject.Inject;
import javax.inject.Named;

import com.iontrading.cat.foureyes.client_lib.bus.FourEyesFunctionResult;
import com.iontrading.cat.foureyes.mock.server.annotation.FourEyes;
import com.iontrading.cat.foureyes.mock.server.entities.NotificationDispatcher;
import com.iontrading.cat.foureyes.mock.server.entities.StorageManager;
import com.iontrading.cat.foureyes.mock.server.workflows.SubmitWorkflowContext;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.commons.async.AsyncResultPromise;
import com.iontrading.isf.commons.async.AsyncResults;
import com.iontrading.isf.trace.ITracer;
import com.iontrading.isf.workflow_engine.impl.common.AbstractWorkflowModule;
import com.iontrading.isf.workflow_engine.spi.IWorkflow;
import com.iontrading.isf.workflow_engine.spi.IWorkflowListener;
import com.iontrading.isf.workflow_engine.spi.executors.IWorkflowExecutionRequest;
import com.iontrading.isf.workflow_engine.spi.executors.IWorkflowExecutionRequestBuilder;
import com.iontrading.talk.api.exception.TalkFunctionException;
import com.iontrading.talk.ionbus.spi.IonBusInfo;

public class SubmitterFunctionsImpl implements SubmitterFunctions {

	private static final String LOG_KEY = "SubmitterFunctions";

	private static final String WORKFLOW_EXECUTOR = "4EyesSubmitterWorkflowExecutor";

	private static int requestCounter = 0;

	private ITracer tracer;

	private IWorkflowExecutionRequestBuilder workflowRequestBuilder;
	private Class<? extends AbstractWorkflowModule> submitWorkflow;

	private StorageManager storage;

	private NotificationDispatcher notifier;

	@Inject
	public SubmitterFunctionsImpl(@FourEyes ITracer tracer, IWorkflowExecutionRequestBuilder workflowRequestBuilder,
			@Named("4EyesSubmitWorkflow") Class<? extends AbstractWorkflowModule> submitWorkflow,
			StorageManager storage, NotificationDispatcher notifier) {
		this.tracer = tracer;
		this.workflowRequestBuilder = workflowRequestBuilder;
		this.submitWorkflow = submitWorkflow;
		this.storage = storage;
		this.notifier = notifier;
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> submit4EyesRequest(int protocolVersion, String comment, String customFields,
			String details, String entitlementFeature, String entitlementNamespace, String entitlementResource,
			String entityCategory, String entityType, String entityDescription, String entityName, String externalId,
			String requestedBy, String requestGroup, String requestNamespace, String requestReason,
			String requestReasonDescription, String userData, IonBusInfo busInfo) {
		final AsyncResultPromise<FourEyesFunctionResult> result = AsyncResults.create();

		String requestId = generateId();

		tracer.INFO().key(LOG_KEY).action("New request").token("Id", requestId).token("comment", comment)
				.token("customFields", customFields).token("details", details)
				.token("entitlementFeature", entitlementFeature).token("entitlementNamespace", entitlementNamespace)
				.token("entitlementResource", entitlementResource).token("entityCategory", entityCategory)
				.token("entityDescription", entityDescription).token("entityName", entityName)
				.token("externalId", externalId).token("requestedBy", requestedBy).token("requestGroup", requestGroup)
				.token("requestNamespace", requestNamespace).token("requestReason", requestReason)
				.token("requestReasonDescription", requestReasonDescription).token("entityType", entityType)
				.token("userData", userData).end();

		final SubmitWorkflowContext ctx = new SubmitWorkflowContext(protocolVersion, requestId, comment, customFields,
				details, entitlementFeature, entitlementNamespace, entitlementResource, entityCategory,
				entityDescription, entityName, externalId, requestedBy, requestGroup, requestNamespace, requestReason,
				requestReasonDescription, entityType, userData, busInfo);
		IWorkflowExecutionRequest workflowRequest = workflowRequestBuilder.requestFor(submitWorkflow)
				.exclusiveOn(requestId).sharedOn("4EyesSubmit").withContext(ctx).withListener(new IWorkflowListener() {

					@Override
					public void started(IWorkflow workflow) {
						// Intentionally left blank
					}

					@Override
					public void completed(IWorkflow workflow) {
						if (ctx.isError()) {
							result.failure(new TalkFunctionException(ctx.getErrorMessage()));
						} else {
							result.success(new FourEyesFunctionResult(0,"{\"Code\":\"0\",\"Response\":\"OK\",\"RequestId\":\""+ctx.getRequestId()+"\"}"));
						}
					}

					@Override
					public void completedWithError(IWorkflow workflow, Throwable err) {
						tracer.ERROR().key(LOG_KEY).action("Internal Error").end();
						result.failure(err);
					}
				}).build();

		workflowRequest.executeOn(WORKFLOW_EXECUTOR);

		return result;
	}

	private String generateId() {
		return "R" + (++requestCounter);
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> fourEyesRequestProcessed(int protocolVersion, String requestId, String comment) {
		final AsyncResultPromise<FourEyesFunctionResult> result = AsyncResults.create();
		storage.done(notifier, requestId, comment, true);
		FourEyesFunctionResult functionResult = new FourEyesFunctionResult();
		functionResult.setCode(0);
		functionResult.setResponse("Approval reuqest processed, Id: " + requestId);
		result.success(functionResult);
		return result;
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> fourEyesRequestDenied(int protocolVersion, String requestId, String comment) {
		final AsyncResultPromise<FourEyesFunctionResult> result = AsyncResults.create();
		storage.done(notifier, requestId, comment, false);
		FourEyesFunctionResult functionResult = new FourEyesFunctionResult();
		functionResult.setCode(0);
		functionResult.setResponse("Approval reuqest rejected, Id: " + requestId);
		result.success(functionResult);
		return result;
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> withdrawPendingRequest(int protocolVersion, String requestId, String comment) {
		final AsyncResultPromise<FourEyesFunctionResult> result = AsyncResults.create();
        storage.cancel(notifier, requestId, comment);
        FourEyesFunctionResult functionResult = new FourEyesFunctionResult();
		functionResult.setCode(0);
		functionResult.setResponse("Cancelled Pending reuqest, Id: " + requestId);
        result.success(functionResult);
        return result;
	}

}
