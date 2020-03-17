package com.iontrading.cat.foureyes.client_lib.exceptions;

import com.iontrading.cat.foureyes.client_lib.enums.Fields;
import com.iontrading.proguard.annotation.KeepAll;

@KeepAll
public class ValidationException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 2512922417734223793L;
    
    private Fields field;
    private String value;

    public ValidationException(Fields field, String value, String message) {
        super("{"+field.getServerString()+" : " +message+"}");
        this.field = field;
        this.value = value;
    }   
    
    public ValidationException(String message) {
        super(message);
    }

    public Fields getField() {
        return field;
    }

    public String getValue() {
        return value;
    }
}
