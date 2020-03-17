package com.iontrading.cat.foureyes.client_lib.submitter.details;

import com.iontrading.proguard.annotation.KeepAll;

@KeepAll
public interface ValueList {
    /**
     * Appends to this list a new item evaluating to the specified string argument.
     * @param s a string.
     * @return a reference to this object.
     */
    ValueList item(final String s);
    ValueList item(final String s, final PropertiesCollection p);

    /**
     * Appends to this list a new item evaluating to the specified integer argument.
     * @param i an integer.
     * @return a reference to this object.
     */
    ValueList item(final int i);
    ValueList item(final int i, final PropertiesCollection p);

    /**
     * Appends to this list a new item evaluating to the specified double argument.
     * @param d a double.
     * @return a reference to this object.
     */
    ValueList item(final double d);
    ValueList item(final double d, final PropertiesCollection p);

    /**
     * Appends to this list a new item evaluating to the specified boolean argument.
     * @param b a boolean.
     * @return a reference to this object.
     */
    ValueList item(final boolean b);
    ValueList item(final boolean b, final PropertiesCollection p);

    /**
     * Appends to this list a new item evaluating to the specified Entity argument.
     * @param e an Entity.
     * @return a reference to this object.
     */
    ValueList item(String name, final Entity e);
    ValueList item(String name, final Entity e, final PropertiesCollection p);
}
