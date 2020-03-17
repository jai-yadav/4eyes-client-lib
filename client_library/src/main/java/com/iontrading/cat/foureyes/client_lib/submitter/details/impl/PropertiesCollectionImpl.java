package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import com.iontrading.cat.foureyes.client_lib.submitter.details.PropertiesCollection;
import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueType;

public class PropertiesCollectionImpl implements PropertiesCollection {
    private ValueType type;
    private boolean isHighlight;

    @Override
    public PropertiesCollection ofType(ValueType t) {
        type = t;
        return this;
    }

    @Override
    public PropertiesCollection highlight() {
        isHighlight = true;
        return this;
    }

    @Override
    public boolean isHighlight() {
        return isHighlight;
    }

    @Override
    public ValueType getType() {
        return type;
    }
}
