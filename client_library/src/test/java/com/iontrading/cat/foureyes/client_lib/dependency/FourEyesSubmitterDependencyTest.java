package com.iontrading.cat.foureyes.client_lib.dependency;

import org.junit.Assert;
import org.junit.Test;

public class FourEyesSubmitterDependencyTest {
    
    private FourEyesDependency dependency;
    
    @Test
    public void testDependencyId() {
        dependency = new FourEyesDependency();
        Assert.assertEquals(FourEyesDependency.ID, dependency.getId());
    }

}
