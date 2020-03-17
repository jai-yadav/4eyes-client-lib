package com.iontrading.cat.foureyes.client_lib.dependency;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.guice.FourEyesDependencyModule;
import com.iontrading.isf.dependency_manager.support.MockDependencyChangeEvent;
import com.iontrading.isf.dependency_manager.support.MockDependencySnapshot;
import com.iontrading.isf.lock.impl.LockFactory;
import com.iontrading.isf.log.support.MockITracer;
import com.iontrading.isf.service_manager.support.MockIBusService;
import com.iontrading.isf.trace.ITracer;

public class FourEyesDependencyObserverTest {
    
    private FourEyesDependencyObserver dependencyObserver;
    private ITracer tracer;
    private FourEyesDependencyProvider provider;
    private MockIBusService busService;
    private MockDependencyChangeEvent dependencyEvent;
    private MockDependencySnapshot dependencySnapshot;
    
    @Before
    public void setUp() {
        tracer = new MockITracer();
        LockFactory lockFactory = new LockFactory();
        provider = new FourEyesDependencyProvider(lockFactory);
        busService = new MockIBusService("id","source","currency");
        
        dependencyObserver = new FourEyesDependencyObserver(tracer, provider);
        
        dependencyEvent = new MockDependencyChangeEvent();
        dependencySnapshot = new MockDependencySnapshot(FourEyesDependencyModule.FOUR_EYES_SUBMITTER_SERVICE);
        dependencySnapshot.setUserdata(busService);
        dependencyEvent.addDependencySnapshot(dependencySnapshot);
    }
    
    @Test
    public void testDependecyAvailable() {

        dependencySnapshot.setAvailable(true);
        dependencyObserver.onChange(dependencyEvent);
        
        Assert.assertEquals(true, provider.isAvailable());
        Assert.assertEquals(busService, provider.getUserData());
        
    }
    
    @Test
    public void testDependecyUnavailable() {

        dependencySnapshot.setAvailable(false);
        dependencyObserver.onChange(dependencyEvent);
        
        Assert.assertEquals(false, provider.isAvailable());
        Assert.assertEquals(null, provider.getUserData());
        
    }

}

