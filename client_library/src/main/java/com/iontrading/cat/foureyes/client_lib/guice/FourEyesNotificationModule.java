package com.iontrading.cat.foureyes.client_lib.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.iontrading.cat.foureyes.client_lib.annotation.FourEyes;
import com.iontrading.cat.foureyes.client_lib.notification.FourEyesNotificationDispatcher;
import com.iontrading.cat.foureyes.client_lib.notification.FourEyesNotificationDispatcherFactory;
import com.iontrading.cat.foureyes.client_lib.notification.FourEyesNotificationDispatcherImpl;
import com.iontrading.cat.foureyes.client_lib.notification.FourEyesNotificationService;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesNotificationListener;
import com.iontrading.isf.boot.guice.BootModule;
import com.iontrading.isf.boot.spi.IBootService.RunPhase;
import com.iontrading.isf.configuration.guice.ConfigurationValueProviderModule;
import com.iontrading.isf.dependency_manager.guice.DependencyManagerModule;
import com.iontrading.isf.executors.spi.ISequentialExecutorService;
import com.iontrading.isf.executors.spi.ISequentialExecutorServiceFactory;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.talk.ionbus.guice.TalkIonBusModule;
import com.iontrading.xrs.guice.XrsLibClientDependencyModule;

@ModuleDescriptor(requires = { BootModule.class, XrsLibClientDependencyModule.class, DependencyManagerModule.class,
        TalkIonBusModule.class, ConfigurationValueProviderModule.class })
public class FourEyesNotificationModule extends AbstractModule {

    public static final String SUBMITER_NOTIFICATION_SERVICE = "4EyesSubmitterNotification";

    @Override
    protected void configure() {
        BootModule.registerBootService(binder(), FourEyesNotificationService.class, RunPhase.RUNNING);

        Multibinder.newSetBinder(binder(), new TypeLiteral<Class<? extends FourEyesNotificationListener>>() {
        });

        install(new FactoryModuleBuilder()
            .implement(FourEyesNotificationDispatcher.class, FourEyesNotificationDispatcherImpl.class)
            .build(FourEyesNotificationDispatcherFactory.class));

    }

    @Provides
    @FourEyes
    public ISequentialExecutorService getSequentialExecutorService(ISequentialExecutorServiceFactory factory) {
        return factory.create("FourEyesNotification");
    }

}
