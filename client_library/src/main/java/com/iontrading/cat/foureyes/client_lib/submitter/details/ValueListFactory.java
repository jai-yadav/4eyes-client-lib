package com.iontrading.cat.foureyes.client_lib.submitter.details;

import com.iontrading.proguard.annotation.KeepAll;

@KeepAll
public interface ValueListFactory {
    /**
     * Creates and returns a new instance of a ValueList object.
     * @return a ValueList instance.
     */
    ValueList create();
}
