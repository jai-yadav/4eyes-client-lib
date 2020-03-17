package com.iontrading.cat.foureyes.client_lib.validation;

import com.iontrading.cat.foureyes.client_lib.submitter.ExtendedPendingRequestSubmissionBuilder;

public interface Validator {
    boolean isValid(ExtendedPendingRequestSubmissionBuilder request);
    String getErrorMessage();
}
