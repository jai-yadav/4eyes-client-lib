package com.iontrading.cat.foureyes.client_lib.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.submitter.ExtendedPendingRequestSubmissionBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.PendingRequestSubmissionBuilderImpl;
import com.iontrading.isf.log.support.MockITracer;

public class MandatoryFieldsValidatorTest {

	private MandatoryFieldsValidator mandatoryFieldsValidator;
	private ExtendedPendingRequestSubmissionBuilder request;

	@Before
	public void setUp() {
		mandatoryFieldsValidator = new MandatoryFieldsValidator(new MockITracer());
	}

	@Test
	public void testValidRequest() {
		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "entityCategory", "entityType", "entityDescription",
				"entityName", "requestedBy");

		Assert.assertTrue(mandatoryFieldsValidator.isValid(request));
	}

	@Test
	public void testProtocolVersion() {
		request = new PendingRequestSubmissionBuilderImpl(null, null, null, null, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "entityCategory", "entityType", "entityDescription",
				"entityName", "requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));
	}

	@Test
	public void testEntitlementFeature() {
		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "", "entitlementNamespace",
				"entitlementResource", "entityCategory", "entityType", "entityDescription", "entityName",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));

		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, null, "entitlementNamespace",
				"entitlementResource", "entityCategory", "entityType", "entityDescription", "entityName",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));
	}

	@Test
	public void testEntitlementNamespace() {
		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature", "",
				"entitlementResource", "entityCategory", "entityType", "entityDescription", "entityName",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));

		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature", null,
				"entitlementResource", "entityCategory", "entityType", "entityDescription", "entityName",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));
	}

	@Test
	public void testEntitlementResource() {
		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "", "entityCategory", "entityType", "entityDescription", "entityName",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));

		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", null, "entityCategory", "entityType", "entityDescription", "entityName",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));
	}

	@Test
	public void testEntityCategory() {
		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "", "entityType", "entityDescription", "entityName",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));

		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", null, "entityType", "entityDescription", "entityName",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));
	}

	@Test
	public void testEntityDescription() {
		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "entityCategory", "entityType", "", "entityName",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));

		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "entityCategory", "entityType", null, "entityName",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));
	}

	@Test
	public void testEntityName() {
		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "entityCategory", "entityType", "entityDescription", "",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));

		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "entityCategory", "entityType", "entityDescription",
				null, "requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));
	}

	@Test
	public void testRequestedBy() {
		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "entityCategory", "entityType", "entityDescription",
				"entityName", "");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));

		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "entityCategory", "entityType", "entityDescription",
				"entityName", null);

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));
	}

	@Test
	public void testEntityType() {
		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "entityCategory", "", "entityDescription", "entityName",
				"requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));

		request = new PendingRequestSubmissionBuilderImpl(null, null, null, 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "entityCategory", null, "entityDescription",
				"entityName", "requestedBy");

		Assert.assertFalse(mandatoryFieldsValidator.isValid(request));
	}

}
