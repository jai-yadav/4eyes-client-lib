package com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iontrading.cat.foureyes.client_lib.enums.Constants;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.FieldImpl;

import java.io.IOException;

    public class FieldSerializer extends StdSerializer<FieldImpl> {

    public FieldSerializer() {
        this(null);
    }

    public FieldSerializer(Class<FieldImpl> vc) {
        super(vc);
    }

    @Override
    public void serialize(FieldImpl field, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(Constants.NAME, field.getName());
        jsonGenerator.writeObject(field.getValue());
        jsonGenerator.writeEndObject();
    }
}
