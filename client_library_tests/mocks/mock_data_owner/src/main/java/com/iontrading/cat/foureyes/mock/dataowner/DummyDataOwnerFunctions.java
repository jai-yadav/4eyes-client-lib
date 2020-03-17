package com.iontrading.cat.foureyes.mock.dataowner;

import com.google.inject.Provider;
import com.iontrading.cat.foureyes.client_lib.api.FourEyesService;
import com.iontrading.cat.foureyes.client_lib.bus.FourEyesFunctionResult;
import com.iontrading.cat.foureyes.client_lib.enums.FieldType;
import com.iontrading.cat.foureyes.client_lib.submitter.PendingRequestSubmissionBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.customFields.CustomFieldListBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.details.DetailsBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.details.EntityFactory;
import com.iontrading.cat.foureyes.client_lib.submitter.details.PropertiesFactory;
import com.iontrading.cat.foureyes.mock.dataowner.annotation.DataOwner;
import com.iontrading.cat.foureyes.mock.dataowner.storage.Storage;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.commons.async.AsyncResultPromise;
import com.iontrading.isf.commons.async.AsyncResults;
import com.iontrading.isf.commons.callback.Callback;
import com.iontrading.isf.trace.ITracer;
import com.iontrading.proguard.annotation.KeepAll;
import com.iontrading.talk.api.annotation.TalkFunction;
import com.iontrading.talk.api.annotation.TalkParam;

import javax.inject.Inject;

/**
 * This is just a function used to trigger the creation of a 4Eyes request.
 */
@KeepAll
public class DummyDataOwnerFunctions {

	private final ITracer tracer;
	private final FourEyesService fourEyesService;
	private final EntityFactory entityFactory;
	private final PropertiesFactory propertyFactory;
	private Storage storage;
	private CustomFieldListBuilder customFieldListBuilder;
	private Provider<DetailsBuilder> detailsBuilderProvider;

	@Inject
	public DummyDataOwnerFunctions(@DataOwner ITracer tracer, FourEyesService fourEyesService, Storage storage,
			CustomFieldListBuilder customFieldListBuilder, Provider<DetailsBuilder> detailsBuilderProvider, EntityFactory entityFactory, PropertiesFactory propertyFactory) {
		this.tracer = tracer;
		this.fourEyesService = fourEyesService;
		this.storage = storage;
		this.customFieldListBuilder = customFieldListBuilder;
		this.detailsBuilderProvider = detailsBuilderProvider;
		this.entityFactory = entityFactory;
		this.propertyFactory = propertyFactory;
	}

	@TalkFunction
	public AsyncResult<String> submitPendingRequest(@TalkParam(name = "Comment", nullable = true) String comment,
			@TalkParam(name = "CustomFieldName", nullable = true) String customFieldName,
			@TalkParam(name = "CustomFieldDisplayName", nullable = true) String customFieldDisplayName,
			@TalkParam(name = "CustomFieldDescription", nullable = true) String customFieldDesc,
			@TalkParam(name = "CustomFieldType", nullable = true) String customFieldType,
			@TalkParam(name = "CustomFieldValue", nullable = true) String customFieldvalue,
			@TalkParam(name = "EntitlementFeature", nullable = true) String entitlementFeature,
			@TalkParam(name = "EntitlementNamespace", nullable = true) String entitlementNamespace,
			@TalkParam(name = "EntitlementResource", nullable = true) String entitlementResource,
			@TalkParam(name = "EntityCategory", nullable = true) String entityCategory,
			@TalkParam(name = "EntityType", nullable = true) String entityType,
			@TalkParam(name = "EntityDescription", nullable = true) String entityDescription,
			@TalkParam(name = "EntityName", nullable = true) String entityName,
			@TalkParam(name = "ExternalId", nullable = true) String externalId,
			@TalkParam(name = "RequestedBy", nullable = true) String requestedBy,
			@TalkParam(name = "RequestGroup", nullable = true) String requestGroup,
			@TalkParam(name = "RequestNamespace", nullable = true) String requestNamespace,
			@TalkParam(name = "RequestReason", nullable = true) String requestReason,
			@TalkParam(name = "RequestReasonDescription", nullable = true) String requestReasonDescription,
			@TalkParam(name = "UserData", nullable = true) String userData) {
		
		// use the service to submit a new 4E request
		PendingRequestSubmissionBuilder submissionBuilder = fourEyesService
				.createPendingRequest(entitlementFeature, entitlementNamespace, entitlementResource, entityCategory,
						entityType, entityDescription, entityName, requestedBy)
				.withComment(comment).withExternalId(externalId).withRequestGroup(requestGroup)
				.withRequestNamespace(requestNamespace).withRequestReason(requestReason)
				.withRequestReasonDescription(requestReasonDescription).withUserData(userData);

		//Dummy details generation
		generateDummyDetails(submissionBuilder);

		//Custom Fields generation
		generateCustomFields(customFieldName, customFieldDisplayName, customFieldDesc, customFieldType,
				customFieldvalue, submissionBuilder);

		AsyncResultPromise<String> submitResult = AsyncResults.create();
		submissionBuilder.submit().addCallback(new Callback<FourEyesFunctionResult>() {

			@Override
			public void onSuccess(FourEyesFunctionResult result) {
				tracer.INFO().key("SubmitPendingRequest").action("FourEyesSubmit").token("Result", "Success")
						.token("RequestId", result.getRequestId()).end();
				submitResult.success(result.getRequestId());
			}

			@Override
			public void onFailure(Throwable exception) {
				tracer.INFO().key("SubmitPendingRequest").action("FourEyesSubmit").token("Result", "Failure")
						.token("Error", exception.getMessage()).end();
				submitResult.failure(exception);
			}
		});

		return submitResult;
	}

	private void generateCustomFields(String customFieldName, String customFieldDisplayName, String customFieldDesc,
			String customFieldType, String customFieldvalue, PendingRequestSubmissionBuilder submissionBuilder) {
		if (customFieldName != null && customFieldvalue != null) {
			submissionBuilder.withCustomFields(customFieldListBuilder
					.createCustomField(customFieldName, customFieldvalue).withDescription(customFieldDesc)
					.withDisplayName(customFieldDisplayName)
					.withType(customFieldType == null ? null : FieldType.valueOf(customFieldType)).add().build());
		}
	}

	private void generateDummyDetails(PendingRequestSubmissionBuilder submissionBuilder) {
		DetailsBuilder detailsBuilder = detailsBuilderProvider.get();

		detailsBuilder.withEntity("dummy", entityFactory.create()
				.field("f1", "v1", propertyFactory.create().highlight())
				.field("f2", "v2", propertyFactory.create().highlight()));

		try {
			submissionBuilder.withDetails(detailsBuilder.build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@TalkFunction(description = "Clear Four-Eyes Requests received back from server as notification")
	public void clear4EyesRequestRecieved() {
		storage.clear();
	}

	@TalkFunction(description = "Set behaviour to process or deny incoming changes on a pending request, \'Process\' to accept changes \'Deny\' to discard changes")
	public void ProcessOrDenyChanges(@TalkParam(name = "ProcessOrDeny") String behaviour) {
		if (behaviour.equals("Process")) {
			DataOwnerBehaviour.isChangesToBeProcessed = true;
		} else if (behaviour.equals("Deny")) {
			DataOwnerBehaviour.isChangesToBeProcessed = false;
		}
	}

	@TalkFunction(description = "Acknowledge or not to acknowledge changes received on status, \'Enable\' to send Ack or \'Disable\' to restrict Ack")
	public void EnableOrDisableAcknowledgement(@TalkParam(name = "EnableOrDisable") String behaviour) {
		if (behaviour.equals("Enable")) {
			DataOwnerBehaviour.isAcknowledgementEnabled = true;
		} else if (behaviour.equals("Disable")) {
			DataOwnerBehaviour.isAcknowledgementEnabled = false;
		}
	}

	@TalkFunction(description = "Cancel a pending request")
	public AsyncResult<FourEyesFunctionResult> WithdrawPendingRequest(@TalkParam(name = "RequestId") String requestId,
			@TalkParam(name = "Comment", nullable = true) String comment) {
		return fourEyesService.withdrawPendingRequest(requestId, comment);
	}

}