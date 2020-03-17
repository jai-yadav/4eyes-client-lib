package com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iontrading.cat.foureyes.client_lib.enums.Constants;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.StructuredValue;

import java.io.IOException;

public class StructuredValueSerializer extends StdSerializer<StructuredValue> {

    public StructuredValueSerializer() {
        this(null);
    }

    public StructuredValueSerializer(Class<StructuredValue> vc) {
        super(vc);
    }

    @Override
    public void serialize(StructuredValue structuredValue, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {

        if (structuredValue.isHighlight()) {
            jsonGenerator.writeBooleanField(Constants.HIGHLIGHT, true);
        }

        jsonGenerator.writeObjectField(Constants.CHILDREN, structuredValue.getEntity());
    }
}
