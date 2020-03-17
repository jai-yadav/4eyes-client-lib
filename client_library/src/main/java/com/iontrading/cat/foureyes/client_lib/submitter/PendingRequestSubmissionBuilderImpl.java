package com.iontrading.cat.foureyes.client_lib.submitter;

import java.util.List;

import com.iontrading.cat.foureyes.client_lib.bus.FourEyesFunctionResult;
import com.iontrading.cat.foureyes.client_lib.bus.SubmitterFunctions;
import com.iontrading.cat.foureyes.client_lib.exceptions.ValidationException;
import com.iontrading.cat.foureyes.client_lib.submitter.customFields.CustomField;
import com.iontrading.cat.foureyes.client_lib.submitter.customFields.CustomFieldsJsonGenerator;
import com.iontrading.cat.foureyes.client_lib.validation.ValidationEngine;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.commons.async.AsyncResultPromise;
import com.iontrading.isf.commons.async.AsyncResults;
import com.iontrading.isf.trace.ITracer;

public class PendingRequestSubmissionBuilderImpl implements ExtendedPendingRequestSubmissionBuilder {

	public final ValidationEngine validationEngine;
	public final SubmitterFunctions functions;

	public final Integer protocolVersion; // Mandatory
	public String comment;
	public CustomFieldsJsonGenerator customFieldsJsonGen;
	public String details;
	public final String entitlementFeature; // Mandatory
	public final String entitlementNamespace; // Mandatory
	public final String entitlementResource; // Mandatory
	public final String entityCategory; // Mandatory
	public final String entityType; // Mandatory
	public final String entityDescription; // Mandatory
	public final String entityName; // Mandatory
	public String externalId;
	public final String requestedBy; // Mandatory
	public String requestGroup;
	public String requestNamespace;
	public String requestReason;
	public String requestReasonDescription;
	public String userData;

	public PendingRequestSubmissionBuilderImpl(SubmitterFunctions functions, ValidationEngine validationEngine,
			ITracer tracer, Integer protocolVersion, String entitlementFeature, String entitlementNamespace,
			String entitlementResource, String entityCategory, String entityType, String entityDescription,
			String entityName, String requestedBy) {
		this.functions = functions;
		this.validationEngine = validationEngine;
		this.protocolVersion = protocolVersion;
		this.entitlementFeature = entitlementFeature;
		this.entitlementNamespace = entitlementNamespace;
		this.entitlementResource = entitlementResource;
		this.entityCategory = entityCategory;
		this.entityDescription = entityDescription;
		this.entityName = entityName;
		this.requestedBy = requestedBy;
		this.entityType = entityType;
		this.customFieldsJsonGen = new CustomFieldsJsonGenerator(tracer);

	}

	@Override
	public PendingRequestSubmissionBuilder withComment(String comment) {
		this.comment = comment;
		return this;
	}

	@Override
	public PendingRequestSubmissionBuilder withCustomFields(List<CustomField> customFields) {
		this.customFieldsJsonGen.setCustomFieldList(customFields);
		return this;
	}

	@Override
	public PendingRequestSubmissionBuilder withDetails(String details) {
		this.details = details;
		return this;
	}

	@Override
	public PendingRequestSubmissionBuilder withExternalId(String externalId) {
		this.externalId = externalId;
		return this;
	}

	@Override
	public PendingRequestSubmissionBuilder withRequestGroup(String requestGroup) {
		this.requestGroup = requestGroup;
		return this;
	}

	@Override
	public PendingRequestSubmissionBuilder withRequestNamespace(String requestNamespace) {
		this.requestNamespace = requestNamespace;
		return this;
	}

	@Override
	public PendingRequestSubmissionBuilder withRequestReason(String requestReason) {
		this.requestReason = requestReason;
		return this;
	}

	@Override
	public PendingRequestSubmissionBuilder withRequestReasonDescription(String requestReasonDescription) {
		this.requestReasonDescription = requestReasonDescription;
		return this;
	}

	@Override
	public PendingRequestSubmissionBuilder withUserData(String userData) {
		this.userData = userData;
		return this;
	}

	@Override
	public AsyncResult<FourEyesFunctionResult> submit() {
		if (!validationEngine.validate(this)) {
			final AsyncResultPromise<FourEyesFunctionResult> result = AsyncResults.create();
			result.failure(new ValidationException(validationEngine.getErrorMessage()));
			return result;
		}
		
		return functions.submit4EyesRequest(protocolVersion, comment, customFieldsJsonGen.generateJson(), details,
				entitlementFeature, entitlementNamespace, entitlementResource, entityCategory, entityType,
				entityDescription, entityName, externalId, requestedBy, requestGroup, requestNamespace, requestReason,
				requestReasonDescription, userData);
	}

	@Override
	public Integer getProtocolVersion() {
		return protocolVersion;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public List<CustomField> getCustomFiledsList() {
		return customFieldsJsonGen.getCustomFieldsList();
	}

	@Override
	public String getCustomFieldsJson() {
		return customFieldsJsonGen.generateJson();
	}

	@Override
	public String getDetails() {
		return details;
	}

	@Override
	public String getEntitlementFeature() {
		return entitlementFeature;
	}

	@Override
	public String getEntitlementNamespace() {
		return entitlementNamespace;
	}

	@Override
	public String getEntitlementResource() {
		return entitlementResource;
	}

	@Override
	public String getEntityCategory() {
		return entityCategory;
	}

	@Override
	public String getEntityDescription() {
		return entityDescription;
	}

	@Override
	public String getEntityName() {
		return entityName;
	}

	@Override
	public String getExternalId() {
		return externalId;
	}

	@Override
	public String getRequestedBy() {
		return requestedBy;
	}

	@Override
	public String getRequestGroup() {
		return requestGroup;
	}

	@Override
	public String getRequestNamespace() {
		return requestNamespace;
	}

	@Override
	public String getRequestReason() {
		return requestReason;
	}

	@Override
	public String getRequestReasonDescription() {
		return requestReasonDescription;
	}

	@Override
	public String getEntityType() {
		return entityType;
	}

	@Override
	public String getUserData() {
		return userData;
	}
}