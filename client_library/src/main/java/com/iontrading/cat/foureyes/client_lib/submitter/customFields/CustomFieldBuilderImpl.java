package com.iontrading.cat.foureyes.client_lib.submitter.customFields;

import com.iontrading.cat.foureyes.client_lib.enums.FieldType;

public class CustomFieldBuilderImpl implements CustomFieldBuilder{

	private String name;
	private String value;
	private FieldType type;
	private String displayName;
	private String description;
	private CustomFieldListBuilderImpl customFieldListBuilder;

	public CustomFieldBuilderImpl(String name, String value, CustomFieldListBuilderImpl customFieldListBuilder) {
		this.name = name;
		this.value = value;
		this.customFieldListBuilder = customFieldListBuilder;
	}

	@Override
	public CustomFieldBuilder withType(FieldType type) {
		this.type = type;
		return this;
	}

	@Override
	public CustomFieldBuilder withDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	@Override
	public CustomFieldBuilder withDescription(String description) {
		this.description = description;
		return this;
	}

	@Override
	public CustomFieldListBuilder add() {
		return customFieldListBuilder
				.addCustomFieldToList(new CustomField(name, displayName, description, type, value));
	}

}
