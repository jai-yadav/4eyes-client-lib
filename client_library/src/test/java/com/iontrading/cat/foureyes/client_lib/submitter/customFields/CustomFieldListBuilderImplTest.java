package com.iontrading.cat.foureyes.client_lib.submitter.customFields;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.enums.FieldType;

public class CustomFieldListBuilderImplTest {

	private CustomFieldListBuilder builder;

	@Before
	public void setUp() {
		builder = new CustomFieldListBuilderImpl();
		builder.createCustomField("c1", "1").withDescription("c1_des").withDisplayName("c1_dn").withType(FieldType.INT)
				.add().createCustomField("c2", "v2").withDescription("c2_des").withDisplayName("c2_dn")
				.withType(FieldType.STR).add();
	}

	@Test
	public void testBuild() {
		List<CustomField> customFields = builder.build();

		Assert.assertEquals(2, customFields.size());

		CustomField c1 = customFields.get(0);
		Assert.assertEquals(c1.getName(), "c1");
		Assert.assertEquals(c1.getValue(), "1");
		Assert.assertEquals(c1.getDisplayName(), "c1_dn");
		Assert.assertEquals(c1.getDescription(), "c1_des");
		Assert.assertEquals(c1.getType(), FieldType.INT);

		CustomField c2 = customFields.get(1);
		Assert.assertEquals(c2.getName(), "c2");
		Assert.assertEquals(c2.getValue(), "v2");
		Assert.assertEquals(c2.getDisplayName(), "c2_dn");
		Assert.assertEquals(c2.getDescription(), "c2_des");
		Assert.assertEquals(c2.getType(), FieldType.STR);
	}
}
