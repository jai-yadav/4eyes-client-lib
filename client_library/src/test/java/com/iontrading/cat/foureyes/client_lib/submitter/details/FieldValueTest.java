package com.iontrading.cat.foureyes.client_lib.submitter.details;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.CollectionOfValues;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.EntityFactoryImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.SimpleEntityImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.FieldImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.NativeValue;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.PropertiesFactoryImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.StructuredValue;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.ValueListFactoryImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.ValueListImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.CollectionOfValuesSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.SimpleEntitySerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.FieldSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.NativeValueSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.StructuredValueSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.ValueListSerializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FieldValueTest {

    SimpleModule module = new SimpleModule("DetailsSerializer", new Version(1, 0, 0, null, null, null)) {{
        addSerializer(SimpleEntityImpl.class, new SimpleEntitySerializer());
        addSerializer(FieldImpl.class, new FieldSerializer());
        addSerializer(NativeValue.class, new NativeValueSerializer());
        addSerializer(CollectionOfValues.class, new CollectionOfValuesSerializer());
        addSerializer(ValueListImpl.class, new ValueListSerializer());
        addSerializer(StructuredValue.class, new StructuredValueSerializer());
    }};

    private ObjectMapper objectMapper = new ObjectMapper() {{
        registerModule(module);
    }};

    @Before
    public void setUp() {
    }

    @Test
    public void testGenerationOfStringValue() throws JsonProcessingException {

        String actualJson, expectedJson;
        FieldImpl field = new FieldImpl("StringField").withValue("abc");

        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"StringField\",\"value\":\"abc\"}";
        Assert.assertEquals(expectedJson, actualJson);

        field.highlight();
        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"StringField\",\"highlight\":true,\"value\":\"abc\"}";
        Assert.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGenerationOfIntValue() throws JsonProcessingException {

        String actualJson, expectedJson;
        FieldImpl field = new FieldImpl("IntField").withValue(12345);

        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"IntField\",\"value\":12345,\"type\":\"INT\"}";
        Assert.assertEquals(expectedJson, actualJson);

        field.highlight();
        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"IntField\",\"highlight\":true,\"value\":12345,\"type\":\"INT\"}";
        Assert.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGenerationOfIonDateValue() throws JsonProcessingException {

        String actualJson, expectedJson;
        FieldImpl field = new FieldImpl("DateField").withValue(20190528, ValueType.ION_DATE);

        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"DateField\",\"value\":20190528,\"type\":\"ION_DATE\"}";
        Assert.assertEquals(expectedJson, actualJson);

        field.highlight();
        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"DateField\",\"highlight\":true,\"value\":20190528,\"type\":\"ION_DATE\"}";
        Assert.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGenerationOfIonTimeValue() throws JsonProcessingException {

        String actualJson, expectedJson;
        FieldImpl field = new FieldImpl("TimeField").withValue(153804, ValueType.ION_TIME);

        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"TimeField\",\"value\":153804,\"type\":\"ION_TIME\"}";
        Assert.assertEquals(expectedJson, actualJson);

        field.highlight();
        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"TimeField\",\"highlight\":true,\"value\":153804,\"type\":\"ION_TIME\"}";
        Assert.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGenerationOfBooleanValue() throws JsonProcessingException {

        String actualJson, expectedJson;
        FieldImpl field = new FieldImpl("BoolField").withValue(true);

        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"BoolField\",\"value\":true,\"type\":\"BOOL\"}";
        Assert.assertEquals(expectedJson, actualJson);

        field.highlight();
        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"BoolField\",\"highlight\":true,\"value\":true,\"type\":\"BOOL\"}";
        Assert.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGenerationOfRealValue() throws JsonProcessingException {

        String actualJson, expectedJson;
        FieldImpl field = new FieldImpl("RealField").withValue(3.14);

        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"RealField\",\"value\":3.14,\"type\":\"REAL\"}";
        Assert.assertEquals(expectedJson, actualJson);

        field.highlight();
        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"RealField\",\"highlight\":true,\"value\":3.14,\"type\":\"REAL\"}";
        Assert.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGenerationOfIsoDateValue() throws JsonProcessingException {

        String actualJson, expectedJson;
        FieldImpl field = new FieldImpl("IsoDateField").withValue("2013-07-16", ValueType.ISO_DATE);

        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"IsoDateField\",\"value\":\"2013-07-16\",\"type\":\"ISO_DATE\"}";
        Assert.assertEquals(expectedJson, actualJson);

        field.highlight();
        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"IsoDateField\",\"highlight\":true,\"value\":\"2013-07-16\",\"type\":\"ISO_DATE\"}";
        Assert.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGenerationOfIsoTimeValue() throws JsonProcessingException {

        String actualJson, expectedJson;
        FieldImpl field = new FieldImpl("IsoTimeField").withValue("23:59:59", ValueType.ISO_TIME);

        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"IsoTimeField\",\"value\":\"23:59:59\",\"type\":\"ISO_TIME\"}";
        Assert.assertEquals(expectedJson, actualJson);

        field.highlight();
        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"IsoTimeField\",\"highlight\":true,\"value\":\"23:59:59\",\"type\":\"ISO_TIME\"}";
        Assert.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGenerationOfIsoDateTimeValue() throws JsonProcessingException {

        String actualJson, expectedJson;
        FieldImpl field = new FieldImpl("IsoDateTimeField").withValue("2012-03-29T10:05:45", ValueType.ISO_DATETIME);

        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"IsoDateTimeField\",\"value\":\"2012-03-29T10:05:45\",\"type\":\"ISO_DATETIME\"}";
        Assert.assertEquals(expectedJson, actualJson);

        field.highlight();
        actualJson = objectMapper.writeValueAsString(field);
        expectedJson = "{\"name\":\"IsoDateTimeField\",\"highlight\":true,\"value\":\"2012-03-29T10:05:45\",\"type\":\"ISO_DATETIME\"}";
        Assert.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGenerationOfStructuredValue() throws JsonProcessingException {

        EntityFactory ef = new EntityFactoryImpl();
        PropertiesFactory pf = new PropertiesFactoryImpl();

        FieldImpl field = new FieldImpl("StructuredField")
                .withValue(ef.create()
                        .field("SubProp-string" ,"abc")
                        .field("SubProp-int", 12345, pf.create().highlight()));

        String actualJson = objectMapper.writeValueAsString(field);
        String expectedJson =
                "{" + "\"name\":\"StructuredField\",\"children\":[" +
                        "{\"name\":\"SubProp-string\",\"value\":\"abc\"}," +
                        "{\"name\":\"SubProp-int\",\"highlight\":true,\"value\":12345,\"type\":\"INT\"}" + "]" +
                "}";
        Assert.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testGenerationOfCollectionOfValues() throws JsonProcessingException {

        ValueListFactory lf = new ValueListFactoryImpl();
        PropertiesFactory pf = new PropertiesFactoryImpl();

        FieldImpl field = new FieldImpl("ListField")
                .withValue(lf.create()
                        .item("abc")
                        .item("2019-10-08", pf.create().ofType(ValueType.ISO_DATE).highlight())
                        .item(3.14)
                        .item(128)
                        .item(20191008, pf.create().ofType(ValueType.ION_DATE)));

        String actualJson = objectMapper.writeValueAsString(field);
        String expectedJson =
                "{" + "\"name\":\"ListField\",\"children\":[" +
                        "{\"value\":\"abc\"}," +
                        "{\"highlight\":true,\"value\":\"2019-10-08\",\"type\":\"ISO_DATE\"}," +
                        "{\"value\":3.14,\"type\":\"REAL\"}," +
                        "{\"value\":128,\"type\":\"INT\"}," +
                        "{\"value\":20191008,\"type\":\"ION_DATE\"}" +
                        "]" +
                "}";
        Assert.assertEquals(expectedJson, actualJson);
    }
}
