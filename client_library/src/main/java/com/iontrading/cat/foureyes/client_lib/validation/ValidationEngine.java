package com.iontrading.cat.foureyes.client_lib.validation;

import com.iontrading.cat.foureyes.client_lib.submitter.ExtendedPendingRequestSubmissionBuilder;

public interface ValidationEngine {
    
    boolean validate(ExtendedPendingRequestSubmissionBuilder request);

    String getErrorMessage();

}
