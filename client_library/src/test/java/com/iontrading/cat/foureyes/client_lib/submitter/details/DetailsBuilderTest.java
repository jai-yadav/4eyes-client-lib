package com.iontrading.cat.foureyes.client_lib.submitter.details;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iontrading.cat.foureyes.client_lib.exceptions.ValidationException;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.DetailsBuilderImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.EntityFactoryImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.PropertiesFactoryImpl;

public class DetailsBuilderTest {

	private EntityFactory entityFactory;
	private DetailsBuilder detailsBuilder;
	private PropertiesFactoryImpl propertyFactory;

	@Before
	public void setUp() {
		entityFactory = new EntityFactoryImpl();
		propertyFactory = new PropertiesFactoryImpl();
		detailsBuilder = new DetailsBuilderImpl();
	}

	@Test
	public void testValid() throws JsonProcessingException, ValidationException {

		Entity old = entityFactory
				.create()
				.field("Quantity", 100,  propertyFactory.create().highlight())
				.field("Lot Size", 1, propertyFactory.create().highlight());
		detailsBuilder.withEntity("old", old);

		String actualJson = detailsBuilder.build();
		String expectedJson = "{\"version\":1,\"entities\":[{\"id\":\"old\",\"fields\":[{\"name\":\"Quantity\",\"highlight\":true,\"value\":100,\"type\":\"INT\"},{\"name\":\"Lot Size\",\"highlight\":true,\"value\":1,\"type\":\"INT\"}]}]}";
		Assert.assertEquals(expectedJson, actualJson);
	}
}
