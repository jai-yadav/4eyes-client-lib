package com.iontrading.cat.foureyes.client_lib.submitter.details;

import com.iontrading.proguard.annotation.KeepAll;

@KeepAll
public interface EntityFactory {
    /**
     * Creates and returns a new instance of an entity object.
     * @return a new entity instance.
     */
    Entity create();
}
