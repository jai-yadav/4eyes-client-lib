package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueList;
import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueListFactory;

public class ValueListFactoryImpl implements ValueListFactory {

    @Override
    public ValueList create() {
        return new ValueListImpl();
    }
}
