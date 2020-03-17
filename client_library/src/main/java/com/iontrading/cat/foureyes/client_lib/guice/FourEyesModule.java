package com.iontrading.cat.foureyes.client_lib.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.iontrading.cat.foureyes.client_lib.annotation.FourEyes;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesNotificationListener;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.isf.trace.ITracer;
import com.iontrading.isf.trace.ITracerFactory;
import com.iontrading.proguard.annotation.KeepAll;

/**
 *Use this module to access and initialize all the features for Four-Eyes Approval 
 */
@KeepAll
@ModuleDescriptor(requires = { PropertiesModule.class, FourEyesSubmitterModule.class, ValidationEngineModule.class,
        FourEyesDependencyModule.class, FourEyesNotificationModule.class })
public class FourEyesModule extends AbstractModule {
    
    @Override
    protected void configure() {
    }

    @Provides
    @FourEyes
    private ITracer getTracer(ITracerFactory logFactory) {
        return logFactory.createTracer("4Eyes");
    }
    
    /**
     * Register a notification listener.
     * @param binder
     * 		Guice binder
     * @param lnerClass
     * 		Class Type of the notification listener
     */
    public static void registerNotificationListener(Binder binder,
            Class<? extends FourEyesNotificationListener> lnerClass) {
        Multibinder<Class<? extends FourEyesNotificationListener>> multibinder = Multibinder.newSetBinder(binder,
                new TypeLiteral<Class<? extends FourEyesNotificationListener>>() {});
        multibinder.addBinding().toInstance(lnerClass);
    }

}
