package com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iontrading.cat.foureyes.client_lib.enums.Constants;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.DetailsImpl;

public class DetailsSerializer extends StdSerializer<DetailsImpl> {

    public DetailsSerializer() {
        this(null);
    }

    public DetailsSerializer(Class<DetailsImpl> vc) {
        super(vc);
    }

    @Override
    public void serialize(DetailsImpl details, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField(Constants.VERSION, details.getVersion());
        jsonGenerator.writeObjectField(Constants.ENTITIES, details.getEntities());
        jsonGenerator.writeEndObject();
    }

}
