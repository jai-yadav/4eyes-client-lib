package com.iontrading.cat.foureyes.client_lib.exceptions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.enums.Fields;

public class FieldValidationExceptionTest {
    
    private ValidationException exception;
    
    @Before
    public void setUp() {
        exception = new ValidationException(Fields.ACTION_COMMENT, "FieldValue", "ErrorMsg");
    }
    
    @Test
    public void testContents() {
        Assert.assertEquals(Fields.ACTION_COMMENT, exception.getField());
        Assert.assertEquals("FieldValue", exception.getValue());
        Assert.assertEquals("{ActionComment : ErrorMsg}", exception.getMessage());
    }

}
