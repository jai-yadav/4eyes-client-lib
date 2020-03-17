package com.iontrading.cat.foureyes.client_lib.submitter.customFields;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.iontrading.cat.foureyes.client_lib.enums.Constants;
import com.iontrading.cat.foureyes.client_lib.enums.FieldType;
import com.iontrading.proguard.annotation.KeepAll;

@JsonPropertyOrder({ Constants.NAME, Constants.DISPLAYNAME, Constants.VALUE,
		Constants.DESCRIPTION, Constants.TYPE })
@KeepAll
public class CustomField {

	private final String name;
	private final String displayName;
	private final FieldType fieldType;
	private final String value;
	private final String description;

	public CustomField(String name, String displayName, String description, FieldType type, String value) {
		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.fieldType = type;
		this.value = value;
	}

	@Override
	public String toString() {
		return "CustomField [name=" + name + ", displayName=" + displayName + ", type=" + fieldType + ", value=" + value
				+ ", description=" + description + "]";
	}

	@JsonProperty(Constants.NAME)
	public String getName() {
		return name;
	}

	@JsonProperty(Constants.DISPLAYNAME)
	@JsonInclude(Include.NON_EMPTY)
	public String getDisplayName() {
		return displayName;
	}

	@JsonProperty(Constants.TYPE)
	@JsonInclude(Include.NON_NULL)
	public FieldType getType() {
		return fieldType;
	}

	@JsonProperty(Constants.VALUE)
	public String getValue() {
		return value;
	}

	@JsonProperty(Constants.DESCRIPTION)
	@JsonInclude(Include.NON_EMPTY)
	public String getDescription() {
		return description;
	}

}
