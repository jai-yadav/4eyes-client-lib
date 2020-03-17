package com.iontrading.cat.foureyes.client_lib.submitter.details;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iontrading.cat.foureyes.client_lib.exceptions.ValidationException;
import com.iontrading.proguard.annotation.KeepAll;

/**
 * <p>
 * This builder is used to generate the JSON string of details data.
 * <p>
 *@see {@link #withEntity(String, Entity)}<br>
 *@see {@link #build()}
 */
@KeepAll
public interface DetailsBuilder {

	/**
	 * Add a named entity to the Details data.
	 * @param name the name of the entity
	 * @param entity the reference to the entity to add
	 * @return the instance of a builder specialized for defining an entity
	 */
	DetailsBuilder withEntity(final String name, final Entity entity);

	/**
	 * Generate the UI complaint JSON string and Clears the builder.
	 * @return JSON string
	 * @throws ValidationException If column Id of any row is not found
	 * @throws JsonProcessingException If Creation of JSON string fails
	 */
	String build() throws ValidationException, JsonProcessingException;

}
