package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import java.util.LinkedList;
import java.util.List;

import com.iontrading.cat.foureyes.client_lib.submitter.details.Entity;
import com.iontrading.cat.foureyes.client_lib.submitter.details.MainEntity;

public class DetailsImpl {

    private final int version = 1;
    private final List< MainEntity > entities = new LinkedList();

    public void add(final String name, final Entity entity) {
        MainEntity entry = new MainEntityImpl(name, entity);
        entities.add( entry );
    }

    public int getVersion() {
        return version;
    }

    public List< MainEntity > getEntities() {
        return entities;
    }
}
