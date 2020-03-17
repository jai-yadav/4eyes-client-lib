package com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.FieldValue;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.ValueListImpl;

import java.io.IOException;

public class ValueListSerializer extends StdSerializer<ValueListImpl> {

    public ValueListSerializer() {
        this(null);
    }

    public ValueListSerializer(Class<ValueListImpl> vc) {
        super(vc);
    }

    @Override
    public void serialize(ValueListImpl valueList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        for (FieldValue fieldValue : valueList.getValues()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObject(fieldValue);
            jsonGenerator.writeEndObject();
        }
    }
}
