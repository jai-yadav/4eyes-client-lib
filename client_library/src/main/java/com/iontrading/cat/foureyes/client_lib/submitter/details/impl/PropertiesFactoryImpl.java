package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import com.iontrading.cat.foureyes.client_lib.submitter.details.PropertiesCollection;
import com.iontrading.cat.foureyes.client_lib.submitter.details.PropertiesFactory;

public class PropertiesFactoryImpl implements PropertiesFactory {
    @Override
    public PropertiesCollection create() {
        return new PropertiesCollectionImpl();
    }
}
