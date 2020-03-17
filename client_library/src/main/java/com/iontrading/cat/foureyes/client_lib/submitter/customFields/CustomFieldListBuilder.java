package com.iontrading.cat.foureyes.client_lib.submitter.customFields;

import java.util.List;

import com.iontrading.proguard.annotation.KeepAll;

/**
 *Create a list of custom fields.
 */
@KeepAll
public interface CustomFieldListBuilder {

	/**
	 * Create a custom field.
	 * @param name
	 * 		Name of the custom field
	 * @param value
	 * 		value of the custom field
	 * @return A single Custom field builder.
	 * @see {@link CustomFieldBuilder}
	 */
	CustomFieldBuilder createCustomField(String name, String value);

	/**
	 * Generates and return a list containing Custom fields
	 * @return List of Custom fields
	 */
	List<CustomField> build();

}
