package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import com.iontrading.cat.foureyes.client_lib.submitter.details.Entity;
import com.iontrading.cat.foureyes.client_lib.submitter.details.EntityFactory;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public Entity create() {
        return new SimpleEntityImpl();
    }
}
