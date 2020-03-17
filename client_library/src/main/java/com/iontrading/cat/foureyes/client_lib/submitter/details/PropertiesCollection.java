package com.iontrading.cat.foureyes.client_lib.submitter.details;

import com.iontrading.proguard.annotation.KeepAll;

@KeepAll
public interface PropertiesCollection {
    PropertiesCollection ofType(ValueType t);
    PropertiesCollection highlight();

    boolean isHighlight();
    ValueType getType();
}
