package com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iontrading.cat.foureyes.client_lib.enums.Constants;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.ListStructuredValue;

public class ListStructuredValueSerializer extends StdSerializer<ListStructuredValue> {

    public ListStructuredValueSerializer() {
        this(null);
    }

    public ListStructuredValueSerializer(Class<ListStructuredValue> vc) {
        super(vc);
    }

    @Override
    public void serialize(ListStructuredValue structuredValue, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {

        if (structuredValue.isHighlight()) {
            jsonGenerator.writeBooleanField(Constants.HIGHLIGHT, true);
        }
        
        jsonGenerator.writeStringField(Constants.NAME, structuredValue.getName());
        jsonGenerator.writeObjectField(Constants.CHILDREN, structuredValue.getEntity());

    }
}
