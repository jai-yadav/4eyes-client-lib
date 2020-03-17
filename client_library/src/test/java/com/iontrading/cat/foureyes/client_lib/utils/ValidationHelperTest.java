package com.iontrading.cat.foureyes.client_lib.utils;


import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.enums.Fields;
import com.iontrading.cat.foureyes.client_lib.exceptions.ValidationException;

public class ValidationHelperTest {
    
    @Test
    public void testNotNullValid() throws ValidationException {
        ValidationHelper.notNull(1, Fields.ACTION_COMMENT);
    }
    
    @Test
    public void testNotBlankCheckingNullValid() throws ValidationException {
        ValidationHelper.notBlank("ValidValue", Fields.ACTION_COMMENT);
    }
    
    @Test
    public void testCheckJsonValid() throws ValidationException {
        ValidationHelper.checkJson("[\r\n" + 
                "  {\r\n" + 
                "    \"name\": \"John\",\r\n" + 
                "    \"age\": 20,\r\n" + 
                "    \"city\": \"New York\"\r\n" + 
                "  }\r\n" + 
                "]", Fields.ACTION_COMMENT);
    }
    
    @Test(expected = ValidationException.class)
    public void testNotNullInvalid() throws ValidationException {
        ValidationHelper.notNull(null, Fields.ACTION_COMMENT);
    }
    
    @Test(expected = ValidationException.class)
    public void testNotBlankCheckingNullInvalid() throws ValidationException {
        ValidationHelper.notBlank(null, Fields.ACTION_COMMENT);
    }
    
    @Test(expected = ValidationException.class)
    public void testNotBlankCheckingBlankInvalid() throws ValidationException {
        ValidationHelper.notBlank("", Fields.ACTION_COMMENT);
    }
    
    @Test(expected = ValidationException.class)
    public void testCheckJsonInvalid() throws ValidationException {
        ValidationHelper.checkJson("InvalidJsonString", Fields.ACTION_COMMENT);
    }

}
