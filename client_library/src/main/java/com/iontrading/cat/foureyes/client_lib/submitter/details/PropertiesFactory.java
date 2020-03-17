package com.iontrading.cat.foureyes.client_lib.submitter.details;

import com.iontrading.proguard.annotation.KeepAll;

@KeepAll
public interface PropertiesFactory {
    /**
     * Creates and returns a new instance of a property collection object.
     * @return a new property collection instance.
     */
    PropertiesCollection create();
}
