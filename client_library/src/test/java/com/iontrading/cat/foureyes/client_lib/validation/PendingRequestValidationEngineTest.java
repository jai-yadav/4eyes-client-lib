package com.iontrading.cat.foureyes.client_lib.validation;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.mock.MockValidator;

public class PendingRequestValidationEngineTest {
    
    private PendingRequestValidationEngine validationEngine;
    private Set<Validator> validators;
    private MockValidator validation1;
    private MockValidator validation2;
    
    @Before
    public void setUp() {
        validators = new HashSet<Validator>();
        validation1 = new MockValidator("Validation1");
        validation2 = new MockValidator("Validation2");
        validators.add(validation1);
        validators.add(validation2);
        validationEngine = new PendingRequestValidationEngine(validators);
    }
    
    @Test
    public void testValidateAllPassed() {
        validation1.setValidity(true);
        validation2.setValidity(true);
        
        Assert.assertTrue(validationEngine.validate(null));
    }
    
    @Test
    public void testValidateSomeFailed() {
        validation1.setValidity(true);
        validation2.setValidity(false);
        
        Assert.assertFalse(validationEngine.validate(null));
    }

}
