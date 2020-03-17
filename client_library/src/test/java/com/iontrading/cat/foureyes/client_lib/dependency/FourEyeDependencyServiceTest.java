package com.iontrading.cat.foureyes.client_lib.dependency;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.guice.FourEyesDependencyModule;
import com.iontrading.isf.dependency_manager.providers.IonBusDependency;
import com.iontrading.isf.dependency_manager.support.MockDependencyManager;
import com.iontrading.isf.dependency_manager.support.MockDependencyObserver;


public class FourEyeDependencyServiceTest {
    private FourEyeDependencyService service;
    private MockDependencyManager dependencyManager;
    private MockDependencyObserver observer;
    
    @Before
    public void setUp() {
        observer = new MockDependencyObserver();
        dependencyManager = new MockDependencyManager();
        service = new FourEyeDependencyService(dependencyManager, observer);
    }
    
    @Test
    public void testStart() throws Exception {
        service.start();
        dependencyManager.verifyInteterstAdded(new IonBusDependency(FourEyesDependencyModule.FOUR_EYES_SUBMITTER_SERVICE));
    }
    
    @Test
    public void testShutdown() throws Exception {
        service.shutdown();
        Assert.assertNull(dependencyManager.getObserver());
    }
    
    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("FourEyeDependencyService", service.getName());
    }

}
