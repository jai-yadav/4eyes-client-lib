package com.iontrading.cat.foureyes.client_lib.submitter.customFields;

import com.iontrading.cat.foureyes.client_lib.enums.FieldType;
import com.iontrading.proguard.annotation.KeepAll;

/**
 *Creates a single custom field
 */
@KeepAll
public interface CustomFieldBuilder {

	/**
	 * Add optional type of the custom field value. Default value is
	 * {@link FieldType#STR}
	 * 
	 * @param type {@link FieldType}
	 * @return Original custom field builder
	 */
	CustomFieldBuilder withType(FieldType type);

	/**
	 * Display name of the custom field.
	 * 
	 * @param displayName
	 * @return Original custom field builder
	 */
	CustomFieldBuilder withDisplayName(String displayName);

	/**
	 * Add description to the custom field.
	 * 
	 * @param description
	 * @return Original custom field builder
	 */
	CustomFieldBuilder withDescription(String description);

	
	/**
	 * Adds the created custom field to the list in original {@link CustomFieldListBuilder}
	 * @return Original Custom field list builder
	 */
	CustomFieldListBuilder add();

}
