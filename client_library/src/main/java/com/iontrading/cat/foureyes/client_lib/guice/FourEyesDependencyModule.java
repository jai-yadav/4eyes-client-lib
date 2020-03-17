package com.iontrading.cat.foureyes.client_lib.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.iontrading.cat.foureyes.client_lib.annotation.FourEyes;
import com.iontrading.cat.foureyes.client_lib.dependency.FourEyeDependencyService;
import com.iontrading.cat.foureyes.client_lib.dependency.FourEyesDependencyObserver;
import com.iontrading.cat.foureyes.client_lib.dependency.FourEyesDependencyProvider;
import com.iontrading.isf.boot.guice.BootModule;
import com.iontrading.isf.boot.spi.IBootService.RunPhase;
import com.iontrading.isf.dependency_manager.api.DependencyObserver;
import com.iontrading.isf.dependency_manager.guice.DependencyManagerModule;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.xrs.guice.XrsLibClientDependencyModule;

@ModuleDescriptor(requires = { BootModule.class, XrsLibClientDependencyModule.class, DependencyManagerModule.class })
public class FourEyesDependencyModule extends AbstractModule {

    public static final String FOUR_EYES_SUBMITTER_SERVICE = "4EyesSubmitterActions";

    @Override
    protected void configure() {

        bind(FourEyesDependencyProvider.class).in(Singleton.class);
        bind(DependencyObserver.class).annotatedWith(FourEyes.class).to(FourEyesDependencyObserver.class).in(Singleton.class);
        
        BootModule.registerBootService(binder(), FourEyeDependencyService.class, RunPhase.RUNNING);
        DependencyManagerModule.registerDependencyProvider(binder(), FourEyesDependencyProvider.class);
    }

}