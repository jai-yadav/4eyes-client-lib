package com.iontrading.cat.foureyes.client_lib.submitter.customFields;

import java.util.ArrayList;
import java.util.List;

public class CustomFieldListBuilderImpl implements CustomFieldListBuilder{
	
	private List<CustomField> customFields;
	
	public CustomFieldListBuilderImpl() {
		customFields = new ArrayList<>();
	}
	
	@Override
	public CustomFieldBuilder createCustomField(String name, String value) {
		return new CustomFieldBuilderImpl(name,value,this);
	}
	
	public CustomFieldListBuilder addCustomFieldToList(CustomField customField) {
		this.customFields.add(customField);
		return this;
	}
	
	@Override
	public List<CustomField> build() {
		ArrayList<CustomField> finalList = new ArrayList<CustomField>(customFields);
		customFields.clear();
		return finalList;
	}
}
