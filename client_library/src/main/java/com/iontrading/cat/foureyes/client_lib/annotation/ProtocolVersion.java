package com.iontrading.cat.foureyes.client_lib.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.google.inject.BindingAnnotation;
import com.iontrading.proguard.annotation.Keep;
import com.iontrading.proguard.annotation.KeepPublicClassMembers;

@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
@Inherited
@Keep
@KeepPublicClassMembers
public @interface ProtocolVersion {

}
