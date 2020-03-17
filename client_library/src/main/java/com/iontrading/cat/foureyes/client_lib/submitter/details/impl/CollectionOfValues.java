package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueList;

public class CollectionOfValues extends FieldValue {

    private ValueList valueList;

    protected CollectionOfValues() {
        super(ValueRank.COLLECTION_OF_VALUES);
    }

    public void setValueList(final ValueList l) {
        valueList = l;
    }

    public ValueList getValueList() {
        return valueList;
    }
}
