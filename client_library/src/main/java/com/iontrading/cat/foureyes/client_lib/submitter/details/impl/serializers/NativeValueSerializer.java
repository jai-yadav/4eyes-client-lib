package com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iontrading.cat.foureyes.client_lib.enums.Constants;
import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueType;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.NativeValue;

import java.io.IOException;

public class NativeValueSerializer extends StdSerializer<NativeValue> {

    public NativeValueSerializer() {
        this(null);
    }

    public NativeValueSerializer(Class<NativeValue> vc) {
        super(vc);
    }

    @Override
    public void serialize(NativeValue fieldValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        if (fieldValue.isHighlight()) {
            jsonGenerator.writeBooleanField(Constants.HIGHLIGHT, true);
        }

        switch (fieldValue.getType()) {
        case STR:
        case ISO_DATE:
        case ISO_TIME:
        case ISO_DATETIME:
            jsonGenerator.writeStringField(Constants.VALUE,  (String) fieldValue.getValue());
            break;

        case BOOL:
            jsonGenerator.writeBooleanField(Constants.VALUE, (boolean) fieldValue.getValue());
            break;

        case INT:
        case ION_DATE:
        case ION_TIME:
            jsonGenerator.writeNumberField(Constants.VALUE, (int) fieldValue.getValue());
            break;

        case REAL:
            jsonGenerator.writeNumberField(Constants.VALUE, (double) fieldValue.getValue());
            break;
        }

        if (fieldValue.getType() != ValueType.STR) {
            jsonGenerator.writeStringField(Constants.TYPE, fieldValue.getType().toString());
        }
    }
}
