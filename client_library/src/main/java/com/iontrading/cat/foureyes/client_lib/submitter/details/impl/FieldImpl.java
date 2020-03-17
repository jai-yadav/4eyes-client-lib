package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import com.iontrading.cat.foureyes.client_lib.submitter.details.Entity;
import com.iontrading.cat.foureyes.client_lib.submitter.details.Field;
import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueList;
import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueType;

public class FieldImpl implements Field {

    private final String name;
    private FieldValue value;

    public FieldImpl(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public FieldValue getValue() {
        return value;
    }

    public FieldImpl withValue(final String s) {
        return withNativeValue(s, ValueType.STR);
    }

    public FieldImpl withValue(final String s, final ValueType t) {
        if (t != ValueType.STR &&
                t != ValueType.ISO_TIME &&
                t != ValueType.ISO_DATE &&
                t != ValueType.ISO_DATETIME) {
            String message = String.format("Unexpected type: specified '%s', but expecting one of (%s, %s, %s, %s)"
                    , t, ValueType.STR, ValueType.ISO_TIME, ValueType.ISO_DATE, ValueType.ISO_DATETIME);
            throw  new java.lang.IllegalArgumentException(message);
        }

        return withNativeValue(s, t);
    }

    public FieldImpl withValue(int i) {
        return withNativeValue(i, ValueType.INT);
    }

    public FieldImpl withValue(double d) {
        return withNativeValue(d, ValueType.REAL);
    }

    public FieldImpl withValue(boolean b) {
        return withNativeValue(b, ValueType.BOOL);
    }

    public FieldImpl withValue(int i, ValueType t) {
        if (t != ValueType.INT &&
            t != ValueType.ION_DATE &&
            t != ValueType.ION_TIME) {
            String message = String.format("Unexpected type: specified '%s', but expecting one of (%s, %s, %s)"
                    , t, ValueType.INT, ValueType.ION_DATE, ValueType.ION_TIME);
            throw  new java.lang.IllegalArgumentException(message);
        }

        return withNativeValue(i, t);
    }

    private FieldImpl withNativeValue(final Object v, final ValueType t) {

        NativeValue nativeValue;
        if (value == null) {
            value = nativeValue = new NativeValue();
        }
        else if (value instanceof NativeValue) {
            nativeValue = (NativeValue) value;
        }
        else {
            throw  new java.lang.IllegalArgumentException();
        }
        nativeValue.setValue(v, t);

        return this;
    }

    public FieldImpl withValue(final Entity e) {

        StructuredValue structuredValue;
        if (value == null) {
            value = structuredValue = new StructuredValue();
        }
        else if (value instanceof StructuredValue) {
            structuredValue = (StructuredValue) value;
        }
        else {
            throw new java.lang.IllegalStateException();
        }
        structuredValue.setEntity(e);

        return this;
    }

    public FieldImpl withValue(final ValueList l) {

        CollectionOfValues collectionOfValues = null;
        if (value == null) {
            value = collectionOfValues = new CollectionOfValues();
        }
        else if (!(value instanceof CollectionOfValues)) {
            throw new java.lang.IllegalStateException();
        }
        collectionOfValues.setValueList(l);

        return this;
    }

    public void highlight() {
        if (value == null) {
            throw new IllegalStateException();
        }

        value.highlight();
    }
}
