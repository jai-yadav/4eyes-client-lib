package com.iontrading.cat.foureyes.client_lib.validation;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.iontrading.cat.foureyes.client_lib.submitter.ExtendedPendingRequestSubmissionBuilder;

public class PendingRequestValidationEngine implements ValidationEngine{
    
    private final Set<Validator> validators;
    private String errorMsg;
    
    @Inject
    public PendingRequestValidationEngine(@Named("PendingRequestValidators") Set<Validator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean validate(ExtendedPendingRequestSubmissionBuilder request) {
        
        for (Validator validator : validators) {
            if(!validator.isValid(request)) {
                this.errorMsg = validator.getErrorMessage();
                return false;
                
            }
        }
        
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMsg;
    }

}
