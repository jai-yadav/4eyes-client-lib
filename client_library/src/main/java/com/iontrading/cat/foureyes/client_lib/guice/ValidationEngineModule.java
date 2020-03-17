package com.iontrading.cat.foureyes.client_lib.guice;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.iontrading.cat.foureyes.client_lib.validation.CustomFieldsValidator;
import com.iontrading.cat.foureyes.client_lib.validation.JsonFieldsValidator;
import com.iontrading.cat.foureyes.client_lib.validation.MandatoryFieldsValidator;
import com.iontrading.cat.foureyes.client_lib.validation.PendingRequestValidationEngine;
import com.iontrading.cat.foureyes.client_lib.validation.ValidationEngine;
import com.iontrading.cat.foureyes.client_lib.validation.Validator;

public class ValidationEngineModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(ValidationEngine.class).to(PendingRequestValidationEngine.class).in(Singleton.class);
        
        Multibinder<Validator> pendingRequestValidators = Multibinder.newSetBinder(binder(), Validator.class, Names.named("PendingRequestValidators"));
        pendingRequestValidators.addBinding().to(MandatoryFieldsValidator.class);
        pendingRequestValidators.addBinding().to(JsonFieldsValidator.class);
        pendingRequestValidators.addBinding().to(CustomFieldsValidator.class);
    }

}
