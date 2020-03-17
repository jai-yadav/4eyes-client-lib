package com.iontrading.cat.foureyes.client_lib.validation;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.iontrading.cat.foureyes.client_lib.annotation.FourEyes;
import com.iontrading.cat.foureyes.client_lib.enums.Fields;
import com.iontrading.cat.foureyes.client_lib.exceptions.ValidationException;
import com.iontrading.cat.foureyes.client_lib.submitter.ExtendedPendingRequestSubmissionBuilder;
import com.iontrading.cat.foureyes.client_lib.utils.ValidationHelper;
import com.iontrading.isf.trace.ITracer;

public class MandatoryFieldsValidator implements Validator {

    private final ITracer tracer;
    private String errorMsg;
    private static final String LOG_KEY = "MandatorFieldsValidator";

    @Inject
    public MandatoryFieldsValidator(@FourEyes ITracer tracer) {
        this.tracer = tracer;
        this.errorMsg = StringUtils.EMPTY;
    }

    @Override
    public boolean isValid(ExtendedPendingRequestSubmissionBuilder request) {
        try {
            ValidationHelper.notNull(request.getProtocolVersion(), Fields.REQUEST_PROTOCOL_VERSION);
            ValidationHelper.notBlank(request.getEntitlementFeature(), Fields.ENTITLEMENT_FEATURE);
            ValidationHelper.notBlank(request.getEntitlementNamespace(), Fields.ENTITLEMENT_NAMESPACE);
            ValidationHelper.notBlank(request.getEntitlementResource(), Fields.ENTITLEMENT_RESOURCE);
            ValidationHelper.notBlank(request.getEntityCategory(), Fields.ENTITY_CATEGORY);
            ValidationHelper.notBlank(request.getEntityDescription(), Fields.ENTITY_DESCRIPTION);
            ValidationHelper.notBlank(request.getEntityName(), Fields.ENTITY_NAME);
            ValidationHelper.notBlank(request.getRequestedBy(), Fields.REQUESTED_BY);
            ValidationHelper.notBlank(request.getEntityType(), Fields.ENTITY_TYPE);
        } catch (ValidationException e) {
            tracer.ERROR()
                .key(LOG_KEY)
                .action("Checking mandatory parameters")
                .token(e.getField().getServerString(), e.getValue())
                .end();
            
            errorMsg = e.getMessage();
            
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMsg;
    }
    
}
