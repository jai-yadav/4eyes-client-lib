package com.iontrading.cat.foureyes.client_lib.mock;

import java.util.HashMap;
import java.util.Map;

import com.iontrading.cat.foureyes.client_lib.bus.FourEyesFunctionResult;
import com.iontrading.cat.foureyes.client_lib.bus.SubmitterFunctions;
import com.iontrading.cat.foureyes.client_lib.enums.Fields;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.commons.async.AsyncResultPromise;
import com.iontrading.isf.commons.async.AsyncResults;

public class MockSubmitterFunctions implements SubmitterFunctions {

	private final Map<Fields, Object> submittedValues;
	private boolean isFourEyesRequestProcessed;
	private boolean isFourEyesRequestDenied;
	private boolean isWithdrawPendingRequestCalled;

	public MockSubmitterFunctions() {
		this.submittedValues = new HashMap<Fields, Object>();
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> submit4EyesRequest(int protocolVersion, String comment, String customFields,
			String details, String entitlementFeature, String entitlementNamespace, String entitlementResource,
			String entityCategory, String entityType, String entityDescription, String entityName, String externalId,
			String requestedBy, String requestGroup, String requestNamespace, String requestReason,
			String requestReasonDescription, String userData) {

		AsyncResultPromise<FourEyesFunctionResult> submit4EyesRequestResult = AsyncResults.create();

		submittedValues.put(Fields.REQUEST_PROTOCOL_VERSION, protocolVersion);
		submittedValues.put(Fields.ACTION_COMMENT, comment);
		submittedValues.put(Fields.CUSTOM_FIELDS, customFields);
		submittedValues.put(Fields.DETAILS, details);
		submittedValues.put(Fields.ENTITLEMENT_FEATURE, entitlementFeature);
		submittedValues.put(Fields.ENTITLEMENT_NAMESPACE, entitlementNamespace);
		submittedValues.put(Fields.ENTITLEMENT_RESOURCE, entitlementResource);
		submittedValues.put(Fields.ENTITY_CATEGORY, entityCategory);
		submittedValues.put(Fields.ENTITY_DESCRIPTION, entityDescription);
		submittedValues.put(Fields.ENTITY_NAME, entityName);
		submittedValues.put(Fields.EXTERNAL_ID, externalId);
		submittedValues.put(Fields.REQUESTED_BY, requestedBy);
		submittedValues.put(Fields.REQUESTE_GROUP, requestGroup);
		submittedValues.put(Fields.REQUESTE_NAMESPACE, requestNamespace);
		submittedValues.put(Fields.REQUESTE_REASON, requestReason);
		submittedValues.put(Fields.REQUESTE_REASON_DESCRIPTION, requestReasonDescription);
		submittedValues.put(Fields.ENTITY_TYPE, entityType);
		submittedValues.put(Fields.USER_DATA, userData);
		return submit4EyesRequestResult;
	}

	public Map<Fields, Object> getSubmittedValues() {
		return submittedValues;
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> fourEyesRequestProcessed(int protocolVersion, String requestId, String comment) {
		isFourEyesRequestProcessed = true;
		return null;
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> fourEyesRequestDenied(int protocolVersion, String requestId, String comment) {
		isFourEyesRequestDenied = true;
		return null;
	}

	public boolean isFourEyesRequestProcessed() {
		return isFourEyesRequestProcessed;
	}

	public boolean isFourEyesRequestDenied() {
		return isFourEyesRequestDenied;
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> withdrawPendingRequest(int protocolVersion, String requestId, String comment) {
		isWithdrawPendingRequestCalled = true;
		return null;
	}
	
	public boolean isWithdrawPendingRequestCalled() {
		return isWithdrawPendingRequestCalled;
	}

}
