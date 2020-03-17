package com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iontrading.cat.foureyes.client_lib.enums.Constants;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.CollectionOfValues;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.FieldValue;

import java.io.IOException;

public class CollectionOfValuesSerializer extends StdSerializer<CollectionOfValues> {

    public CollectionOfValuesSerializer() {
        this(null);
    }

    public CollectionOfValuesSerializer(Class<CollectionOfValues> vc) {
        super(vc);
    }

    @Override
    public void serialize(CollectionOfValues collectionOfValues, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {

        if (collectionOfValues.isHighlight()) {
            jsonGenerator.writeBooleanField(Constants.HIGHLIGHT, true);
        }

        jsonGenerator.writeArrayFieldStart(Constants.CHILDREN);
        jsonGenerator.writeObject(collectionOfValues.getValueList());
        jsonGenerator.writeEndArray();
    }
}
