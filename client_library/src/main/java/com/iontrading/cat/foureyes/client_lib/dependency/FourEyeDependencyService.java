package com.iontrading.cat.foureyes.client_lib.dependency;

import com.google.inject.Inject;
import com.iontrading.cat.foureyes.client_lib.annotation.FourEyes;
import com.iontrading.cat.foureyes.client_lib.guice.FourEyesDependencyModule;
import com.iontrading.isf.boot.spi.IService;
import com.iontrading.isf.dependency_manager.api.DependencyManager;
import com.iontrading.isf.dependency_manager.api.DependencyObserver;
import com.iontrading.isf.dependency_manager.providers.IonBusDependency;

public class FourEyeDependencyService implements IService {

    private final DependencyManager depManager;
    private final DependencyObserver observer;

    @Inject
    public FourEyeDependencyService(DependencyManager depManager,
            @FourEyes DependencyObserver observer) {
        this.depManager = depManager;
        this.observer = observer;
    }

    @Override
    public String getName() {
        return "FourEyeDependencyService";
    }

    @Override
    public void start() throws Exception {
        depManager.addInterest(observer, new IonBusDependency(FourEyesDependencyModule.FOUR_EYES_SUBMITTER_SERVICE));
    }

    @Override
    public void shutdown() {
        depManager.removeInterest(observer);
    }

}
