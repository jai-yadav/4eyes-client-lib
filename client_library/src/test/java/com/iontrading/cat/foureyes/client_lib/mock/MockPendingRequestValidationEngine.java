package com.iontrading.cat.foureyes.client_lib.mock;

import com.iontrading.cat.foureyes.client_lib.submitter.ExtendedPendingRequestSubmissionBuilder;
import com.iontrading.cat.foureyes.client_lib.validation.ValidationEngine;

public class MockPendingRequestValidationEngine implements ValidationEngine{
    
    private boolean isValid;
    private String errorMessage;

    @Override
    public boolean validate(ExtendedPendingRequestSubmissionBuilder request) {
        return isValid;
    }

    public void setValidity(boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
