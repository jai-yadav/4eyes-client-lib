package com.iontrading.cat.foureyes.client_lib.submitter;

import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iontrading.cat.foureyes.client_lib.bus.FourEyesFunctionResult;
import com.iontrading.cat.foureyes.client_lib.enums.FieldType;
import com.iontrading.cat.foureyes.client_lib.enums.Fields;
import com.iontrading.cat.foureyes.client_lib.exceptions.ValidationException;
import com.iontrading.cat.foureyes.client_lib.mock.MockPendingRequestValidationEngine;
import com.iontrading.cat.foureyes.client_lib.mock.MockSubmitterFunctions;
import com.iontrading.cat.foureyes.client_lib.submitter.customFields.CustomField;
import com.iontrading.cat.foureyes.client_lib.submitter.details.DetailsBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.details.EntityFactory;
import com.iontrading.cat.foureyes.client_lib.submitter.details.PropertiesFactory;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.DetailsBuilderImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.EntityFactoryImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.PropertiesFactoryImpl;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.log.support.MockITracer;

public class PendingRequestSubmissionBuilderTest {

	private PendingRequestSubmissionBuilderImpl submissionBuilder;
	private MockSubmitterFunctions functions;
	private MockPendingRequestValidationEngine validationEngine;
	private Integer protocolVersion;
	private DetailsBuilder detailsBuilder;

	@Before
	public void setUp() {
		protocolVersion = Integer.valueOf(999);
		functions = new MockSubmitterFunctions();
		validationEngine = new MockPendingRequestValidationEngine();

		submissionBuilder = new PendingRequestSubmissionBuilderImpl(functions, validationEngine, new MockITracer(),
				protocolVersion, "entitlementFeature", "entitlementNamespace", "entitlementResource", "entityCategory",
				"entityType", "entityDescription", "entityName", "requestedBy");

		detailsBuilder = new DetailsBuilderImpl();

        submissionBuilder.withComment("comment")
                .withCustomFields(Arrays.asList(new CustomField("name", "displayName", "description", FieldType.STR, "value")))
                .withExternalId("externalId")
                .withRequestGroup("requestGroup")
                .withRequestNamespace("requestNamespace")
                .withRequestReason("requestReason")
                .withRequestReasonDescription("requestReasonDescription")
                .withUserData("userData");
	}

	@Test
	public void testSubmitWhenRequestIsValid() {

		validationEngine.setValidity(true);

		AsyncResult<FourEyesFunctionResult> result = submissionBuilder.submit();

		Map<Fields, Object> submittedValues = functions.getSubmittedValues();

		Assert.assertNull(result.getFailure());

		Assert.assertEquals(protocolVersion, submittedValues.get(Fields.REQUEST_PROTOCOL_VERSION));
		Assert.assertEquals("comment", submittedValues.get(Fields.ACTION_COMMENT));
		Assert.assertEquals(submissionBuilder.getCustomFieldsJson(), submittedValues.get(Fields.CUSTOM_FIELDS));
		Assert.assertEquals("entitlementFeature", submittedValues.get(Fields.ENTITLEMENT_FEATURE));
		Assert.assertEquals("entitlementNamespace", submittedValues.get(Fields.ENTITLEMENT_NAMESPACE));
		Assert.assertEquals("entitlementResource", submittedValues.get(Fields.ENTITLEMENT_RESOURCE));
		Assert.assertEquals("entityCategory", submittedValues.get(Fields.ENTITY_CATEGORY));
		Assert.assertEquals("entityDescription", submittedValues.get(Fields.ENTITY_DESCRIPTION));
		Assert.assertEquals("entityName", submittedValues.get(Fields.ENTITY_NAME));
		Assert.assertEquals("externalId", submittedValues.get(Fields.EXTERNAL_ID));
		Assert.assertEquals("requestedBy", submittedValues.get(Fields.REQUESTED_BY));
		Assert.assertEquals("requestGroup", submittedValues.get(Fields.REQUESTE_GROUP));
		Assert.assertEquals("requestNamespace", submittedValues.get(Fields.REQUESTE_NAMESPACE));
		Assert.assertEquals("requestReason", submittedValues.get(Fields.REQUESTE_REASON));
		Assert.assertEquals("requestReasonDescription", submittedValues.get(Fields.REQUESTE_REASON_DESCRIPTION));
		Assert.assertEquals("entityType", submittedValues.get(Fields.ENTITY_TYPE));
		Assert.assertEquals("userData", submittedValues.get(Fields.USER_DATA));
		Assert.assertEquals(
				"{\"customFields\":[{\"name\":\"name\",\"displayName\":\"displayName\",\"value\":\"value\",\"description\":\"description\",\"type\":\"STR\"}]}",
				submittedValues.get(Fields.CUSTOM_FIELDS));
	}

	@Test
	public void testSubmitWithValidDetailsBuilder() throws JsonProcessingException, ValidationException {
		validationEngine.setValidity(true);

        EntityFactory ef = new EntityFactoryImpl();
        PropertiesFactory pf = new PropertiesFactoryImpl();

        detailsBuilder.withEntity("OldValue", ef.create()
                .field("Quantity", 100, pf.create().highlight())
                .field("LotSize", 1, pf.create().highlight()));

        AsyncResult<FourEyesFunctionResult> result = submissionBuilder.withDetails(detailsBuilder.build()).submit();

		Map<Fields, Object> submittedValues = functions.getSubmittedValues();
        String expectedDetails = "{\"version\":1,\"entities\":[{\"id\":\"OldValue\",\"fields\":[{\"name\":\"Quantity\",\"highlight\":true,\"value\":100,\"type\":\"INT\"},{\"name\":\"LotSize\",\"highlight\":true,\"value\":1,\"type\":\"INT\"}]}]}";
        Object actualDetails = submittedValues.get(Fields.DETAILS);
		Assert.assertEquals(expectedDetails, actualDetails);
		Assert.assertNull(result.getFailure());
	}

	@Test
	public void testSubmitWhenRequestIsNotValid() {
		validationEngine.setValidity(false);
		validationEngine.setErrorMessage("Invalid Four-Eyes creation request, Validation failed");
		AsyncResult<FourEyesFunctionResult> result = submissionBuilder.submit();

		Throwable failure = result.getFailure();
		Assert.assertNotNull(failure);
		Assert.assertEquals("Invalid Four-Eyes creation request, Validation failed", failure.getMessage());

	}

}
