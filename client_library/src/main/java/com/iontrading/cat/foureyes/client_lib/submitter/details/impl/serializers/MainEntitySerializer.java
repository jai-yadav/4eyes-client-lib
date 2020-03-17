package com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iontrading.cat.foureyes.client_lib.enums.Constants;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.MainEntityImpl;

public class MainEntitySerializer extends StdSerializer<MainEntityImpl> {

    public MainEntitySerializer() {
        this(null);
    }

    public MainEntitySerializer(Class<MainEntityImpl> vc) {
        super(vc);
    }

    @Override
    public void serialize(MainEntityImpl entity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(Constants.ID, entity.getId());
        jsonGenerator.writeObjectField(Constants.FIELDS, entity.getEntity());
        jsonGenerator.writeEndObject();
    }
}
