package com.iontrading.cat.foureyes.client_lib.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iontrading.cat.foureyes.client_lib.enums.Fields;
import com.iontrading.cat.foureyes.client_lib.exceptions.ValidationException;

public class ValidationHelper {
    
    public static void notNull(Object value, Fields field) throws ValidationException {
        if(value==null)
            throw new ValidationException(field, null, "Value can not be null");
    }
    
    public static void notBlank(String value, Fields field) throws ValidationException {
        if(value==null)
            throw new ValidationException(field, value, "Value can not be null");
        if(value.isEmpty())
            throw new ValidationException(field, value, "Value can not be empty");
    }
    
    public static void checkJson(String value, Fields field) throws ValidationException {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(value);
        } catch (IOException e) {
            throw new ValidationException(field, value, "Not a valid JSON"); 
        }
    }

}
