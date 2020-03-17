package com.iontrading.cat.foureyes.client_lib.submitter.customFields;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.enums.FieldType;
import com.iontrading.isf.log.support.MockITracer;

public class CustomFieldsJsonGeneratorTest {
	
	private CustomFieldsJsonGenerator customFieldsJsonGenerator;
	
	@Before
	public void setUp() {
		customFieldsJsonGenerator = new CustomFieldsJsonGenerator(new MockITracer());
	}
	
	@Test
	public void testGenerateJsonWhenListIsEmpty() {
		Assert.assertNull(customFieldsJsonGenerator.generateJson());
	}
	
	@Test
	public void testGenerateJsonOnlyMandatoryFields() {
		CustomField customField = new CustomField("name", null, null, null, "value");
		customFieldsJsonGenerator.setCustomFieldList(Arrays.asList(customField));
		
		String expectedJson = "{" +
				"\"customFields\":[{" + 
				"\"name\":\"name\"," + 
				"\"value\":\"value\"" + 
				"}]" + 
				"}";
		
		Assert.assertEquals(expectedJson, customFieldsJsonGenerator.generateJson());
	}
	
	@Test
	public void testGenerateJsonAllFields() {
		CustomField customField = new CustomField("name", "displayName", "description", FieldType.STR, "value");
		customFieldsJsonGenerator.setCustomFieldList(Arrays.asList(customField));
		
		String expectedJson = "{" +
				"\"customFields\":[{" + 
				"\"name\":\"name\"," + 
				"\"displayName\":\"displayName\"," + 
				"\"value\":\"value\"," + 
				"\"description\":\"description\"," + 
				"\"type\":\"STR\"" + 
				"}]" + 
				"}";
		
		Assert.assertEquals(expectedJson, customFieldsJsonGenerator.generateJson());
	}

}
