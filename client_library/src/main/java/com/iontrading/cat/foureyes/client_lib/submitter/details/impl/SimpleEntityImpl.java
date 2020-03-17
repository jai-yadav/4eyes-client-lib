package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import java.util.ArrayList;
import java.util.List;

import com.iontrading.cat.foureyes.client_lib.submitter.details.Entity;
import com.iontrading.cat.foureyes.client_lib.submitter.details.Field;
import com.iontrading.cat.foureyes.client_lib.submitter.details.PropertiesCollection;
import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueList;
import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueType;

public class SimpleEntityImpl implements Entity {

    private final List<Field> fields = new ArrayList<>();

    public List<Field> getFields() {
        return fields;
    }

    private FieldImpl create(final String n) {
        FieldImpl f = new FieldImpl(n);
        fields.add(f);
        return f;
    }

    @Override
    public Entity field(String n, String s) {
        create(n).withValue(s);
        return this;
    }

    @Override
    public Entity field(String n, String s, PropertiesCollection p) {
        FieldImpl f = create(n);

        if (p.getType() == null) {
            f.withValue(s);
        }
        else {
            f.withValue(s, p.getType());
        }

        if (p.isHighlight()) {
            f.getValue().highlight();
        }

        return this;
    }

    @Override
    public Entity field(String n, int i) {
        create(n).withValue(i);
        return this;
    }

    @Override
    public Entity field(String n, int i, PropertiesCollection p) {
        FieldImpl f = create(n);

        if (p.getType() == null) {
            f.withValue(i);
        }
        else {
            f.withValue(i, p.getType());
        }

        if (p.isHighlight()) {
            f.getValue().highlight();
        }

        return this;
    }

    @Override
    public Entity field(String n, double d) {
        create(n).withValue(d);
        return this;
    }

    @Override
    public Entity field(String n, double d, PropertiesCollection p) {
        FieldImpl f = create(n);

        if (p.getType() == null || p.getType() == ValueType.REAL)  {
            f.withValue(d);
        }
        else {
            throw new IllegalStateException();
        }

        if (p.isHighlight()) {
            f.getValue().highlight();
        }

        return this;
    }

    @Override
    public Entity field(String n, boolean b) {
        create(n).withValue(b);
        return this;
    }

    @Override
    public Entity field(String n, boolean b, PropertiesCollection p) {
        FieldImpl f = create(n);

        if (p.getType() == null || p.getType() == ValueType.BOOL) {
            f.withValue(b);
        }
        else {
            throw new IllegalStateException();
        }

        if (p.isHighlight()) {
            f.getValue().highlight();
        }

        return this;
    }

    @Override
    public Entity field(String n, ValueList l) {
        create(n).withValue(l);
        return this;
    }

    @Override
    public Entity field(String n, ValueList l, PropertiesCollection p) {
        FieldImpl f = create(n);
        if (p.getType() == null) {
            f.withValue(l);
        }
        else {
            throw new IllegalStateException();
        }

        if (p.isHighlight()) {
            f.getValue().highlight();
        }

        return this;
    }

    @Override
    public Entity field(String n, Entity e) {
        create(n).withValue(e);
        return this;
    }

    @Override
    public Entity field(String n, Entity e, PropertiesCollection p) {
        FieldImpl f = create(n);

        if (p.getType() == null) {
            f.withValue(e);
        }
        else {
            throw new IllegalStateException();
        }

        if (p.isHighlight()) {
            f.getValue().highlight();
        }

        return this;
    }
}
