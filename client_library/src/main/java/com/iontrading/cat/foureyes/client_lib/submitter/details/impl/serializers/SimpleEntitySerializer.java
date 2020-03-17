package com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.Field;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.SimpleEntityImpl;

import java.io.IOException;

public class SimpleEntitySerializer extends StdSerializer<SimpleEntityImpl> {

    public SimpleEntitySerializer() {
        this(null);
    }

    public SimpleEntitySerializer(Class<SimpleEntityImpl> vc) {
        super(vc);
    }

    @Override
    public void serialize(SimpleEntityImpl entity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartArray();
        for (Field field : entity.getFields()) {
            jsonGenerator.writeObject(field);
        }
        jsonGenerator.writeEndArray();
    }
}
