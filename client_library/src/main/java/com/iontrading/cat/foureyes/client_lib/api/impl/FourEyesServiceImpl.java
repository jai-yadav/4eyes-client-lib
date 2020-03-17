package com.iontrading.cat.foureyes.client_lib.api.impl;

import javax.inject.Inject;

import com.iontrading.cat.foureyes.client_lib.annotation.FourEyes;
import com.iontrading.cat.foureyes.client_lib.annotation.ProtocolVersion;
import com.iontrading.cat.foureyes.client_lib.api.FourEyesService;
import com.iontrading.cat.foureyes.client_lib.bus.FourEyesFunctionResult;
import com.iontrading.cat.foureyes.client_lib.bus.SubmitterFunctions;
import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.cat.foureyes.client_lib.exceptions.UnsupportedOperationException;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.cat.foureyes.client_lib.submitter.PendingRequestSubmissionBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.PendingRequestSubmissionBuilderImpl;
import com.iontrading.cat.foureyes.client_lib.validation.ValidationEngine;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.trace.ITracer;

public class FourEyesServiceImpl implements FourEyesService {

	private static final String LOG_KEY = "FourEyesService";
	private final SubmitterFunctions functions;
	private final Integer protocolVersion;
	private final ValidationEngine validationEngine;
	private ITracer tracer;

	@Inject
	public FourEyesServiceImpl(SubmitterFunctions functions, ValidationEngine validationEngine,
			@ProtocolVersion Integer protocolVersion, @FourEyes ITracer tracer) {
		this.functions = functions;
		this.protocolVersion = protocolVersion;
		this.validationEngine = validationEngine;
		this.tracer = tracer;
	}

	@Override
	public PendingRequestSubmissionBuilder createPendingRequest(String entitlementFeature, String entitlementNamespace,
			String entitlementResource, String entityCategory, String entityType, String entityDescription, String entityName,
			String requestedBy) {
		return new PendingRequestSubmissionBuilderImpl(functions, validationEngine, tracer, protocolVersion,
				entitlementFeature, entitlementNamespace, entitlementResource, entityCategory, entityType,
				entityDescription, entityName, requestedBy);
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> acknowledgeUpdate(FourEyesRequest request) {
		return this.acknowledgeUpdate(request, null);
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> acknowledgeUpdate(FourEyesRequest request, String comment) {
		return functions.fourEyesRequestProcessed(protocolVersion, request.getRequestId(), comment);
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> denyUpdate(FourEyesRequest request) throws UnsupportedOperationException {
		return this.denyUpdate(request, null);
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> denyUpdate(FourEyesRequest request, String comment)
			throws UnsupportedOperationException {

		if (request.getStatus() != RequestStatus.Approved) {
			tracer.ERROR().key(LOG_KEY).action("Can only reject reuqests with Approved status")
					.token("Request Id", request.getRequestId()).end();
			throw new UnsupportedOperationException("Can only deny approved request");
		}

		return functions.fourEyesRequestDenied(protocolVersion, request.getRequestId(), comment);
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> withdrawPendingRequest(String requestId) {
		return this.withdrawPendingRequest(requestId, null);
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> withdrawPendingRequest(String requestId, String comment) {
		return functions.withdrawPendingRequest(protocolVersion, requestId, comment);
	}

}