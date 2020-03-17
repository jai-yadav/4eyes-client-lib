package com.iontrading.cat.foureyes.client_lib.dependency;

import java.util.concurrent.locks.Lock;

import javax.inject.Inject;

import com.iontrading.isf.dependency_manager.api.DependencyObservable;
import com.iontrading.isf.dependency_manager.api.DependencyProvider;
import com.iontrading.isf.lock.impl.LockFactory;
import com.iontrading.isf.service_manager.spi.IBusService;

public class FourEyesDependencyProvider implements DependencyProvider<FourEyesDependency>{

    private final Lock statusLock;

    private volatile boolean available;
    private IBusService userData;
    private DependencyObservable dependencyObservable;

    @Inject
    public FourEyesDependencyProvider(LockFactory lockFactory) {
        this.statusLock = lockFactory.create("4EyesDepStatus");
    }

    public void notifyStatus() {
        try {
            statusLock.lock();

            if (dependencyObservable == null) {
                return;
            } else if (this.available) {
                dependencyObservable.setAvailable(this.userData);
            } else {
                dependencyObservable.setUnavailable(this.userData);
            }
        } finally {
            statusLock.unlock();
        }
    }

    @Override
    public Class<FourEyesDependency> getDependencyType() {
        return FourEyesDependency.class;
    }

    @Override
    public boolean dependencyRequired(FourEyesDependency dependency, DependencyObservable dependencyObservable) {
        this.dependencyObservable = dependencyObservable;

        notifyStatus();

        return true;
    }
    
    void updateStatus(boolean available, IBusService userData) {
            this.setAvailable(available);
            this.setUserData(userData);
            this.notifyStatus();
    }

    @Override
    public void dependencyNoLongerRequired(FourEyesDependency dependency) {
        this.dependencyObservable = null;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setUserData(IBusService userData) {
        this.userData = userData;
    }

    public boolean isAvailable() {
        return available;
    }

    public IBusService getUserData() {
        return userData;
    }

}
