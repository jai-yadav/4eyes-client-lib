package com.iontrading.cat.foureyes.mock.server;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.iontrading.cat.foureyes.mock.server.annotation.FourEyes;
import com.iontrading.cat.foureyes.mock.server.submitter.FourEyesSubmitterModule;
import com.iontrading.cat.foureyes.mock.server.xrs.FourEyesXrsModule;
import com.iontrading.isf.boot.guice.BootModule;
import com.iontrading.isf.boot.spi.IBootService.RunPhase;
import com.iontrading.isf.configuration.guice.ConfigurationCoreModule;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.isf.service_manager.guice.ServiceManagerModule;
import com.iontrading.isf.trace.ITracer;
import com.iontrading.isf.trace.ITracerFactory;
import com.iontrading.isf.trace.guice.TracerModule;
import com.iontrading.talk.api.guice.TalkModule;

@ModuleDescriptor(requires = { BootModule.class, TracerModule.class, ConfigurationCoreModule.class,
        ServiceManagerModule.class, FourEyesSubmitterModule.class,
        FourEyesXrsModule.class })
public class ApplicationModule extends AbstractModule {

    public static final String FOUR_EYES_SUBMITTER_SERVICE = "4EyesSubmitterActions";

    @Override
    protected void configure() {
        ServiceManagerModule.addService(binder(), FOUR_EYES_SUBMITTER_SERVICE);
        BootModule.registerBootService(binder(), ApplicationService.class, RunPhase.RUNNING);
        
        TalkModule.exportFunctions(binder(), DummyFourEyeServerFunctions.class);
    }

    @Provides
    @FourEyes
    public ITracer getTracer(ITracerFactory logFactory) {
        return logFactory.createTracer("4Eyes", "N/A");
    }

}
