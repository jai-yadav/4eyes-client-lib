package com.iontrading.cat.foureyes.client_lib.submitter.details;

import com.iontrading.proguard.annotation.KeepAll;

@KeepAll
public interface Entity {
    /**
     * Appends to this entity a new field evaluating to the specified string argument.
     * @param s a string.
     * @return a reference to this object.
     */
    Entity field(final String n, final String s);
    Entity field(final String n, final String s, final PropertiesCollection p);

    /**
     * Appends to this entity a new field evaluating to the specified integer argument.
     * @param i an integer
     * @return a reference to this object.
     */
    Entity field(final String n, final int i);
    Entity field(final String n, final int i, final PropertiesCollection p);

    /**
     * Appends to this entity a new field evaluating to the specified double argument.
     * @param d a double.
     * @return a reference to this object.
     */
    Entity field(final String n, final double d);
    Entity field(final String n, final double d, final PropertiesCollection p);

    /**
     * Appends to this entity a new field evaluating to the specified boolean argument.
     * @param b a boolean.
     * @return a reference to this object.
     */
    Entity field(final String n, final boolean b);
    Entity field(final String n, final boolean b, final PropertiesCollection p);

    /**
     * Appends to this entity a new field evaluating to the specified list argument.
     * @param l a list of values.
     * @return a reference to this object.
     */
    Entity field(final String n, final ValueList l);
    Entity field(final String n, final ValueList l, final PropertiesCollection p);

    /**
     * Appends to this entity a new field evaluating to the specified Entity argument.
     * @param e an entity.
     * @return a reference to this object.
     */
    Entity field(final String n, final Entity e);
    Entity field(final String n, final Entity e, final PropertiesCollection p);
}
