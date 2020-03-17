package com.iontrading.cat.foureyes.client_lib.submitter.customFields;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.iontrading.cat.foureyes.client_lib.enums.Constants;
import com.iontrading.isf.trace.ITracer;

@JsonPropertyOrder({ Constants.CUSTOM_FIELDS })
public class CustomFieldsJsonGenerator {

	private static final String LOG_KEY = "CustomFieldsJsonGenerator";
	private List<CustomField> customFieldsList;
	private ITracer tracer;

	@JsonProperty(Constants.CUSTOM_FIELDS)
	public List<CustomField> getCustomFieldsList() {
		return customFieldsList;
	}

	@Inject
	public CustomFieldsJsonGenerator(ITracer tracer) {
		this.tracer = tracer;
		this.customFieldsList = new ArrayList<>();
	}

	@JsonIgnore
	public void setCustomFieldList(List<CustomField> customFields) {
		this.customFieldsList = customFields;
	}

	@JsonIgnore
	public String generateJson() {
		if (customFieldsList.isEmpty())
			return null;

		ObjectMapper objectMapper = new ObjectMapper();
		String json = new String();
		try {
			json = objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			tracer.ERROR().key(LOG_KEY).action("Custom fields JSON generation failed")
					.token("Custom Fields: ", customFieldsList).end();
		}

		return json;
	}

}
