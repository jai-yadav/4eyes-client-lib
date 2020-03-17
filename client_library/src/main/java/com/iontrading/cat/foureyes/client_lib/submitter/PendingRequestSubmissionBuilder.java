package com.iontrading.cat.foureyes.client_lib.submitter;

import java.util.List;

import com.iontrading.cat.foureyes.client_lib.bus.FourEyesFunctionResult;
import com.iontrading.cat.foureyes.client_lib.submitter.customFields.CustomField;
import com.iontrading.cat.foureyes.client_lib.submitter.customFields.CustomFieldListBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.details.DetailsBuilder;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.proguard.annotation.KeepAll;

/**
 * A builder to create and submit a new 4Eyes Request.
 * 
 */
@KeepAll
public interface PendingRequestSubmissionBuilder {

	/**
	 * Enrich builder with an external ID.
	 * 
	 * @param externalId External ID of the entity
	 * @return Returns the original builder
	 */
	PendingRequestSubmissionBuilder withExternalId(String externalId);

	/**
	 * Enrich builder with a request Group
	 * 
	 * @param requestGroup Group to which this request belong
	 * @return Returns the original builder
	 */
	PendingRequestSubmissionBuilder withRequestGroup(String requestGroup);

	/**
	 * Enrich builder with a request Namespace
	 * 
	 * @param requestNamespace Nampespace of the request
	 * @return Returns the original builder
	 */
	PendingRequestSubmissionBuilder withRequestNamespace(String requestNamespace);

	/**
	 * Enrich builder with a description for request reason
	 * {@link #withRequestReason(String)}
	 * 
	 * @param requestReasonDescription Description of request reason
	 * @return Returns the original builder
	 */
	PendingRequestSubmissionBuilder withRequestReasonDescription(String requestReasonDescription);

	/**
	 * Enrich builder with a custom user data.
	 * 
	 * @param userData User data to be used back by data owner
	 * @return Returns the original builder
	 */
	PendingRequestSubmissionBuilder withUserData(String userData);

	/**
	 * Enrich builder with a request reason. To add description to this see
	 * {@link #withRequestReasonDescription(String)}
	 * 
	 * @param requestReason Reason of the request.
	 * @return Returns the original builder
	 */
	PendingRequestSubmissionBuilder withRequestReason(String requestReason);

	/**
	 * Enrich builder with an additional comment
	 * 
	 * @param comment Comment String
	 * @return Returns the original builder
	 */
	PendingRequestSubmissionBuilder withComment(String comment);

	/**
	 * Enrich builder with custom fields.
	 * 
	 * @param customFields 
	 * 		A list of custom fields.
	 * @return Returns the original builder
	 * @see {@link CustomFieldListBuilder}
	 */
	PendingRequestSubmissionBuilder withCustomFields(List<CustomField> customFields);

	/**
	 * Enrich builder with details JSON string
	 * 
	 * @param JSON details string
	 * @return Returns the original builder
	 * @see {@link DetailsBuilder}
	 */
	PendingRequestSubmissionBuilder withDetails(String details);

	/**
	 * Submits the pending request to server.
	 * 
	 * @return Asynchronous result of type {@link FourEyesFunctionResult}
	 */
	AsyncResult<FourEyesFunctionResult> submit();

}
