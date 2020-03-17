package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import com.iontrading.cat.foureyes.client_lib.submitter.details.Entity;

public class StructuredValue extends FieldValue {

    private Entity entity;

    StructuredValue() {
        super(ValueRank.STRUCTURED_VALUE);
    }

    public void setEntity(Entity e) {
        entity = e;
    }

    public Entity getEntity() {
        return entity;
    }
}
