package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iontrading.cat.foureyes.client_lib.exceptions.ValidationException;
import com.iontrading.cat.foureyes.client_lib.submitter.details.DetailsBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.details.Entity;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.CollectionOfValuesSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.DetailsSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.FieldSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.ListStructuredValueSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.MainEntitySerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.NativeValueSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.SimpleEntitySerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.StructuredValueSerializer;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.serializers.ValueListSerializer;

public class DetailsBuilderImpl implements DetailsBuilder {

    private DetailsImpl details = new DetailsImpl();
    private SimpleModule module;

    public DetailsBuilderImpl() {
        module = new SimpleModule("DetailsSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(DetailsImpl.class, new DetailsSerializer());
        module.addSerializer(MainEntityImpl.class, new MainEntitySerializer());
        module.addSerializer(SimpleEntityImpl.class, new SimpleEntitySerializer());
        module.addSerializer(FieldImpl.class, new FieldSerializer());
        module.addSerializer(NativeValue.class, new NativeValueSerializer());
        module.addSerializer(StructuredValue.class, new StructuredValueSerializer());
        module.addSerializer(ListStructuredValue.class, new ListStructuredValueSerializer());
        module.addSerializer(CollectionOfValues.class, new CollectionOfValuesSerializer());
        module.addSerializer(ValueListImpl.class, new ValueListSerializer());
    }

    @Override
    public DetailsBuilder withEntity(String name, Entity entity) {
        details.add(name, entity);
        return this;
    }

    @Override
    public String build() throws ValidationException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(module);

        String json = objectMapper.writeValueAsString(details);
        return json;
    }
}
