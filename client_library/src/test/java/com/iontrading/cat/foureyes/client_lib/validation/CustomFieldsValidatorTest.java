package com.iontrading.cat.foureyes.client_lib.validation;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.enums.FieldType;
import com.iontrading.cat.foureyes.client_lib.submitter.ExtendedPendingRequestSubmissionBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.PendingRequestSubmissionBuilderImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.customFields.CustomField;
import com.iontrading.isf.log.support.MockITracer;

public class CustomFieldsValidatorTest {

	private CustomFieldsValidator validator;
	private ExtendedPendingRequestSubmissionBuilder request;

	@Before
	public void setUp() {
		validator = new CustomFieldsValidator(new MockITracer());
		request = new PendingRequestSubmissionBuilderImpl(null, null, new MockITracer(), 1, "entitlementFeature",
				"entitlementNamespace", "entitlementResource", "entityCategory", "entityType", "entityDescription",
				"entityName", "requestedBy");
	}

	@Test
	public void testValidationFailsIfCustomFieldNameIsNull() {
		request.withCustomFields(Arrays.asList(new CustomField(null, null, null, null, "value")));

		Assert.assertFalse(validator.isValid(request));
	}

	@Test
	public void testValidationFailsIfCustomFieldNameIsEmpty() {
		request.withCustomFields(Arrays.asList(new CustomField(StringUtils.EMPTY, null, null, null, "value")));

		Assert.assertFalse(validator.isValid(request));
	}

	@Test
	public void testValidationFailsIfCustomFieldvalueIsNull() {
		request.withCustomFields(Arrays.asList(new CustomField("Name", null, null, null, null)));

		Assert.assertFalse(validator.isValid(request));
	}

	@Test
	public void testValidationPassesForBlankValue() {
		request.withCustomFields(Arrays.asList(new CustomField("Name", null, null, null, "")));

		Assert.assertTrue(validator.isValid(request));
	}

	@Test
	public void testValidationPasses() {
		request.withCustomFields(
				Arrays.asList(new CustomField("Name", "displayValue", "description", FieldType.STR, "value")));

		Assert.assertTrue(validator.isValid(request));
	}

	@Test
	public void testValidationDateValid() {
		request.withCustomFields(
				Arrays.asList(new CustomField("Name", "displayValue", "description", FieldType.DATE, "2011-06-22")));

		Assert.assertTrue(validator.isValid(request));
	}

	@Test
	public void testValidationDateInvalid() {
		request.withCustomFields(
				Arrays.asList(new CustomField("Name", "displayValue", "description", FieldType.DATE, "20110622")));

		Assert.assertFalse(validator.isValid(request));
	}

	@Test
	public void testValidationTimeValid() {
		request.withCustomFields(
				Arrays.asList(new CustomField("Name", "displayValue", "description", FieldType.TIME, "10:55:22")));

		Assert.assertTrue(validator.isValid(request));
	}

	@Test
	public void testValidationTimeInvalid() {
		request.withCustomFields(
				Arrays.asList(new CustomField("Name", "displayValue", "description", FieldType.TIME, "105522")));

		Assert.assertFalse(validator.isValid(request));
	}

	@Test
	public void testValidationDateTimeValid() {
		request.withCustomFields(Arrays.asList(
				new CustomField("Name", "displayValue", "description", FieldType.DATETIME, "2011-06-22T10:55:22")));

		Assert.assertTrue(validator.isValid(request));
	}

	@Test
	public void testValidationDateTimeInvalid() {
		request.withCustomFields(Arrays.asList(
				new CustomField("Name", "displayValue", "description", FieldType.DATETIME, "2011-06-22T105522")));

		Assert.assertFalse(validator.isValid(request));
	}

	@Test
	public void testValidationIntValid() {
		request.withCustomFields(
				Arrays.asList(new CustomField("Name", "displayValue", "description", FieldType.INT, "100")));

		Assert.assertTrue(validator.isValid(request));
	}

	@Test
	public void testValidationIntInvalid() {
		request.withCustomFields(
				Arrays.asList(new CustomField("Name", "displayValue", "description", FieldType.INT, "100x")));

		Assert.assertFalse(validator.isValid(request));
	}

	@Test
	public void testValidationRealValid() {
		request.withCustomFields(
				Arrays.asList(new CustomField("Name", "displayValue", "description", FieldType.REAL, "100.00")));

		Assert.assertTrue(validator.isValid(request));
	}

	@Test
	public void testValidationRealInvalid() {
		request.withCustomFields(
				Arrays.asList(new CustomField("Name", "displayValue", "description", FieldType.REAL, "100.00x")));

		Assert.assertFalse(validator.isValid(request));
	}

	@Test
	public void testValidationDefaultStrvalid() {
		request.withCustomFields(
				Arrays.asList(new CustomField("Name", "displayValue", "description", null, "100.00x")));

		Assert.assertTrue(validator.isValid(request));
	}

}
