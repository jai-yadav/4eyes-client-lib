package com.iontrading.cat.foureyes.client_lib.submitter.details;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.CollectionOfValues;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.SimpleEntityImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.FieldImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.NativeValue;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.PropertiesFactoryImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.StructuredValue;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.CollectionOfValuesSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.SimpleEntitySerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.FieldSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.NativeValueSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.StructuredValueSerializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EntityBuilderTest {

    SimpleModule module = new SimpleModule("DetailsSerializer", new Version(1, 0, 0, null, null, null)) {{
        addSerializer(SimpleEntityImpl.class, new SimpleEntitySerializer());
        addSerializer(FieldImpl.class, new FieldSerializer());
        addSerializer(NativeValue.class, new NativeValueSerializer());
        addSerializer(StructuredValue.class, new StructuredValueSerializer());
        addSerializer(CollectionOfValues.class, new CollectionOfValuesSerializer());
    }};

    private ObjectMapper objectMapper = new ObjectMapper() {{
        registerModule(module);
    }};

    @Before
    public void setUp() {
    }

    @Test
    public void testGenerationOfAnEntity()
            throws JsonProcessingException {

        PropertiesFactory pf = new PropertiesFactoryImpl();

        String actualJson, expectedJson;
        Entity entity = new SimpleEntityImpl()
                .field("Id", "T0001")
                .field("Price",99.102)
                .field("Quantity", 10, pf.create().highlight());

        actualJson = objectMapper.writeValueAsString(entity);
        expectedJson = "["
                + "{\"name\":\"Id\",\"value\":\"T0001\"},"
                + "{\"name\":\"Price\",\"value\":99.102,\"type\":\"REAL\"},"
                + "{\"name\":\"Quantity\",\"highlight\":true,\"value\":10,\"type\":\"INT\"}"
                + "]";
        Assert.assertEquals(expectedJson, actualJson);
    }
}
