package com.iontrading.cat.foureyes.client_lib.dependency;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.isf.dependency_manager.support.MockDependencyObservable;
import com.iontrading.isf.lock.impl.LockFactory;
import com.iontrading.isf.service_manager.support.MockIBusService;

public class FourEyesDependencyProviderTest {
    
    private FourEyesDependencyProvider dependencyProvider;
    private MockDependencyObservable dependencyObservable;
    private MockIBusService busService;
    
    @Before
    public void setUp() {
        LockFactory lockFactory = new LockFactory();
        busService = new MockIBusService("id", "source", "currency");
        dependencyProvider = new FourEyesDependencyProvider(lockFactory);
        dependencyProvider.setUserData(busService);
        dependencyObservable = new MockDependencyObservable();
        
    }
    
    @Test
    public void testGetDependencyType() {
        Assert.assertEquals(FourEyesDependency.class, dependencyProvider.getDependencyType());
    }
    
    @Test
    public void testDependencyRequiredAndItIsAvaialble() {
        dependencyProvider.setAvailable(true);
        dependencyProvider.dependencyRequired(null, dependencyObservable);
        
        Assert.assertEquals(busService, dependencyObservable.getUserData());
        Assert.assertEquals(true, dependencyObservable.getAvailable());
    }
    
    @Test
    public void testDependencyRequiredAndItIsNotAvaialble() {
        dependencyProvider.setAvailable(false);
        dependencyProvider.dependencyRequired(null, dependencyObservable);
        
        Assert.assertEquals(busService, dependencyObservable.getUserData());
        Assert.assertEquals(false, dependencyObservable.getAvailable());
    }
    
    @Test
    public void testDependencyNoLongerRequired() {
        dependencyProvider.dependencyNoLongerRequired(null);
        dependencyProvider.notifyStatus();
        
        Assert.assertEquals(null, dependencyObservable.getUserData());
        Assert.assertEquals(null, dependencyObservable.getAvailable());
    }
    
    @Test
    public void testUpdateStatus() {
        dependencyProvider.dependencyRequired(null, dependencyObservable);
        
        dependencyProvider.updateStatus(true, busService);
        Assert.assertEquals(busService, dependencyObservable.getUserData());
        Assert.assertEquals(true, dependencyObservable.getAvailable());
        
        dependencyProvider.updateStatus(false, busService);
        Assert.assertEquals(busService, dependencyObservable.getUserData());
        Assert.assertEquals(false, dependencyObservable.getAvailable());
    }

}
