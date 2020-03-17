package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueType;

public class NativeValue extends FieldValue {

    private Object value;
    private ValueType type;

    protected NativeValue() {
        super(ValueRank.NATIVE_VALUE);
    }

    public void setValue(final Object v, final ValueType t) {
        value = v;
        type = t;
    }

    public Object getValue() {
        return value;
    }

    public ValueType getType() {
        return type;
    }
}
