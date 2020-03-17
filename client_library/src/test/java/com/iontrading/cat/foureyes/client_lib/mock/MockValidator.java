package com.iontrading.cat.foureyes.client_lib.mock;

import com.iontrading.cat.foureyes.client_lib.submitter.ExtendedPendingRequestSubmissionBuilder;
import com.iontrading.cat.foureyes.client_lib.validation.Validator;

public class MockValidator implements Validator{
    
    private boolean isValid;
    private String identifier;
    private String errorMsg;
    
    public MockValidator(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean isValid(ExtendedPendingRequestSubmissionBuilder request) {
        return isValid;
    }

    public void setValidity(boolean isValid) {
        this.isValid = isValid;
    }
    
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getErrorMessage() {
        return errorMsg;
    }

    public void setErrorMessage(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
