package com.iontrading.cat.foureyes.client_lib.dependency;

import com.google.inject.Inject;
import com.iontrading.cat.foureyes.client_lib.annotation.FourEyes;
import com.iontrading.cat.foureyes.client_lib.guice.FourEyesDependencyModule;
import com.iontrading.isf.dependency_manager.api.DependencyChangeEvent;
import com.iontrading.isf.dependency_manager.api.DependencyObserver;
import com.iontrading.isf.dependency_manager.api.DependencySnapshot;
import com.iontrading.isf.dependency_manager.providers.IonBusDependency;
import com.iontrading.isf.service_manager.spi.IBusService;
import com.iontrading.isf.trace.ITracer;

public class FourEyesDependencyObserver implements DependencyObserver {
    
    private final ITracer tracer;
    private final FourEyesDependencyProvider provider;
    
    private static final String LOG_KEY = "FourEyesDependencyObserver";
    
    @Inject
    public FourEyesDependencyObserver(@FourEyes ITracer tracer, FourEyesDependencyProvider provider) {
        this.tracer = tracer;
        this.provider = provider;
    }

    @Override
    public void onChange(DependencyChangeEvent event) {
        DependencySnapshot<IonBusDependency> dep = event
                .getDependencySnapshot(FourEyesDependencyModule.FOUR_EYES_SUBMITTER_SERVICE, IonBusDependency.class);
            if (dep.isAvailable()) {
                IBusService service = dep.getUserdata(IBusService.class);
                tracer.INFO()
                    .key(LOG_KEY)
                    .action("Four Eyes service available")
                    .token("Source", service.getSource())
                    .token("Currency", service.getCurrency())
                    .token("Properties", service.getCustomProperty())
                    .end();

                provider.updateStatus(true, service);
            } else {
                tracer.INFO().key(LOG_KEY).action("Four Eyes service not available").end();

                provider.updateStatus(false, null);
            }
        }
    
}
