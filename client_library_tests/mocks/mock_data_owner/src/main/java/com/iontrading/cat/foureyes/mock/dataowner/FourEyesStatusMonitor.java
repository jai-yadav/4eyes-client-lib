package com.iontrading.cat.foureyes.mock.dataowner;

import javax.inject.Inject;

import com.iontrading.cat.foureyes.client_lib.dependency.FourEyesDependency;
import com.iontrading.cat.foureyes.mock.dataowner.annotation.DataOwner;
import com.iontrading.cat.foureyes.mock.dataowner.modules.ApplicationModule;
import com.iontrading.isf.boot.spi.IService;
import com.iontrading.isf.dependency_manager.api.DependencyChangeEvent;
import com.iontrading.isf.dependency_manager.api.DependencyManager;
import com.iontrading.isf.dependency_manager.api.DependencyObserver;
import com.iontrading.isf.dependency_manager.api.DependencySnapshot;
import com.iontrading.isf.service_manager.spi.IBusService;
import com.iontrading.isf.service_manager.spi.IBusServiceManager;
import com.iontrading.isf.trace.ITracer;

/**
 * This class uses the DependencyManager to monitor the status of the 4Eyes
 * service on the ION Platform and drive the availability of a service
 * ({@link ApplicationModule#DATAOWNER_SERVICE_ID}) accordingly.
 * <p>
 * To get notified about the availability of the 4Eyes Service a
 * {@link FourEyesDependency} can be used. It also exposes some bus-level
 * info about the server.
 * </p>
 */
public class FourEyesStatusMonitor implements IService, DependencyObserver {
    
    private ITracer tracer;
    private DependencyManager depManager;
    private IBusServiceManager serviceManager;

    @Inject
    public FourEyesStatusMonitor(@DataOwner ITracer tracer, DependencyManager depManager,
            IBusServiceManager serviceManager) {
        this.tracer = tracer;
        this.depManager = depManager;
        this.serviceManager = serviceManager;
    }

    @Override
    public String getName() {
        return "FourEyesStatusMonitor";
    }

    @Override
    public void start() throws Exception {
        depManager.addInterest(this, new FourEyesDependency());
    }

    @Override
    public void shutdown() {
        depManager.removeInterest(this);
    }

    @Override
    public void onChange(DependencyChangeEvent evt) {
        DependencySnapshot<FourEyesDependency> dep = evt.getDependencySnapshot(FourEyesDependency.ID,
                FourEyesDependency.class);
        if (dep.isAvailable()) {
            IBusService service = dep.getUserdata(IBusService.class);
            tracer.INFO()
                .key("FourEyesStatusMonitor")
                .action("4Eyes service available")
                .token("Source", service.getSource())
                .token("Currency", service.getCurrency())
                .token("Properties", service.getCustomProperty())
                .end();
            serviceManager.activateService(ApplicationModule.DATAOWNER_SERVICE_ID);
        } else {
            tracer.INFO().key("FourEyesStatusMonitor").action("4Eyes submitter service NOT available").end();
            serviceManager.deactivateService(ApplicationModule.DATAOWNER_SERVICE_ID);
        }
    }

}
