package com.iontrading.cat.foureyes.client_lib.guice;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.iontrading.cat.foureyes.client_lib.api.FourEyesService;
import com.iontrading.cat.foureyes.client_lib.api.impl.FourEyesServiceImpl;
import com.iontrading.cat.foureyes.client_lib.bus.SubmitterFunctions;
import com.iontrading.cat.foureyes.client_lib.submitter.customFields.CustomFieldListBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.customFields.CustomFieldListBuilderImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.DetailsBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.details.EntityFactory;
import com.iontrading.cat.foureyes.client_lib.submitter.details.PropertiesFactory;
import com.iontrading.cat.foureyes.client_lib.submitter.details.ValueListFactory;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.DetailsBuilderImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.EntityFactoryImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.PropertiesFactoryImpl;
import com.iontrading.cat.foureyes.client_lib.submitter.details.impl.ValueListFactoryImpl;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.talk.api.guice.TalkModule;
import com.iontrading.talk.ionbus.guice.TalkIonBusModule;

@ModuleDescriptor(requires = { TalkIonBusModule.class })
public class FourEyesSubmitterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(FourEyesService.class).to(FourEyesServiceImpl.class).in(Singleton.class);
        bind(DetailsBuilder.class).to(DetailsBuilderImpl.class);
        bind(EntityFactory.class).to(EntityFactoryImpl.class);
        bind(PropertiesFactory.class).to(PropertiesFactoryImpl.class);
        bind(ValueListFactory.class).to(ValueListFactoryImpl.class);
        bind(CustomFieldListBuilder.class).to(CustomFieldListBuilderImpl.class);
        TalkModule.importFunctions(binder(), SubmitterFunctions.class).withService(FourEyesDependencyModule.FOUR_EYES_SUBMITTER_SERVICE);
    }
    
}
