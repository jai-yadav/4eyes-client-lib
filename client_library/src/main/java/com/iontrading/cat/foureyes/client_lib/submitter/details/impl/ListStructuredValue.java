package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import com.iontrading.cat.foureyes.client_lib.submitter.details.Entity;

public class ListStructuredValue extends FieldValue {

    private Entity entity;
    private String name;

    ListStructuredValue() {
        super(ValueRank.STRUCTURED_VALUE);
    }

    public void setEntity(Entity e) {
        entity = e;
    }

    public Entity getEntity() {
        return entity;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
