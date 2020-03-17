package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import java.util.ArrayList;
import java.util.List;

import com.iontrading.cat.foureyes.client_lib.submitter.details.Entity;
import com.iontrading.cat.foureyes.client_lib.submitter.details.PropertiesCollection;
import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueList;
import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueType;

public class ValueListImpl implements ValueList {

    private final List<FieldValue> values = new ArrayList<>();

    public List<FieldValue> getValues() {
        return values;
    }

    @Override
    public ValueList item(String s) {
        NativeValue v = new NativeValue();
        v.setValue(s, ValueType.STR);
        values.add(v);
        return this;
    }

    @Override
    public ValueList item(String s, PropertiesCollection p) {
        NativeValue v = new NativeValue();
        ValueType t = p.getType();
        if (t == null) {
            t = ValueType.STR;
        }

        if (t != ValueType.STR
                && t != ValueType.ISO_DATE
                && t != ValueType.ISO_TIME
                && t != ValueType.ISO_DATETIME) {
            throw new IllegalStateException();
        }

        v.setValue(s, t);
        if (p.isHighlight()) {
            v.highlight();
        }

        values.add(v);
        return this;
    }

    @Override
    public ValueList item(int i) {
        NativeValue v = new NativeValue();
        v.setValue(i, ValueType.INT);
        values.add(v);
        return this;
    }

    @Override
    public ValueList item(int i, PropertiesCollection p) {
        NativeValue v = new NativeValue();
        ValueType t = p.getType();
        if (t == null) {
            t = ValueType.INT;
        }

        if (t != ValueType.INT
                && t != ValueType.ION_TIME
                && t != ValueType.ION_DATE) {
            throw new IllegalStateException();
        }

        v.setValue(i, t);
        if (p.isHighlight()) {
            v.highlight();
        }

        values.add(v);
        return this;
    }

    @Override
    public ValueList item(double d) {
        NativeValue v = new NativeValue();
        v.setValue(d, ValueType.REAL);
        values.add(v);
        return this;
    }

    @Override
    public ValueList item(double d, PropertiesCollection p) {
        NativeValue v = new NativeValue();
        ValueType t = p.getType();
        if (t == null) {
            t = ValueType.REAL;
        }

        if (t != ValueType.REAL) {
            throw new IllegalStateException();
        }

        v.setValue(d, t);
        if (p.isHighlight()) {
            v.highlight();
        }

        values.add(v);
        return this;
    }

    @Override
    public ValueList item(boolean b) {
        NativeValue v = new NativeValue();
        v.setValue(b, ValueType.BOOL);
        values.add(v);
        return this;
    }

    @Override
    public ValueList item(boolean b, PropertiesCollection p) {
        NativeValue v = new NativeValue();
        ValueType t = p.getType();
        if (t == null) {
            t = ValueType.BOOL;
        }

        if (t != ValueType.BOOL) {
            throw new IllegalStateException();
        }

        v.setValue(b, t);
        if (p.isHighlight()) {
            v.highlight();
        }

        values.add(v);
        return this;
    }

    @Override
    public ValueList item(String name, Entity e) {
    	ListStructuredValue v = new ListStructuredValue();
        v.setEntity(e);
        v.setName(name);
        values.add(v);
        return this;
    }

    @Override
    public ValueList item(String name, Entity e, PropertiesCollection p) {
        ListStructuredValue v = new ListStructuredValue();
        ValueType t = p.getType();
        if (t != null) {
            throw new IllegalStateException();
        }

        v.setEntity(e);
        v.setName(name);
        if (p.isHighlight()) {
            v.highlight();
        }

        values.add(v);
        return this;
    }
}
