package com.iontrading.cat.foureyes.mock.server.elements;

import javax.inject.Inject;

import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.cat.foureyes.mock.server.FourEyesException;
import com.iontrading.cat.foureyes.mock.server.entities.StorageManager;
import com.iontrading.cat.foureyes.mock.server.workflows.SubmitWorkflowContext;
import com.iontrading.isf.commons.callback.Callback;
import com.iontrading.isf.workflow_engine.spi.elements.ICompletionToken;
import com.iontrading.isf.workflow_engine.spi.elements.UserTaskElement;
import com.iontrading.isf.workflow_engine.spi.elements.WorkflowException;

public class SaveNewRequest extends UserTaskElement {

    private SubmitWorkflowContext context;
    private StorageManager storage;

    @Inject
    public SaveNewRequest(SubmitWorkflowContext context, StorageManager storage) {
        this.context = context;
        this.storage = storage;
    }

    @Override
    public void process(final ICompletionToken token) throws WorkflowException {

        int protocolVersion = context.getProtocolVersion();
        String id = context.getRequestId();
        String comment = context.getComment();
        String customFields = context.getCustomFields();
        String details = context.getDetails();
        String entitlementFeature = context.getEntitlementFeature();
        String entitlementNamespace = context.getEntitlementNamespace();
        String entitlementResource = context.getEntitlementResource();
        String entityCategory = context.getEntityCategory();
        String entityDescription = context.getEntityDescription();
        String entityName = context.getEntityName();
        String externalId = context.getExternalId();
        String requestedBy = context.getRequestedBy();
        String requestGroup = context.getRequestGroup();
        String requestNamespace = context.getRequestNamespace();
        String requestReason = context.getRequestReason();
        String requestReasonDescription = context.getRequestReasonDescription();
        String entityType = context.getEntityType();
        String userData = context.getUserData();
        String dataOwner = context.getBusInfo().getCaller();

        storage
            .saveNewRequest(protocolVersion, id, comment, customFields, details, entitlementFeature, entitlementNamespace,
                    entitlementResource, entityCategory, entityDescription, entityName, externalId, requestedBy,
                    requestGroup, requestNamespace, requestReason, requestReasonDescription, entityType, userData, dataOwner)
            .addCallback(new Callback<FourEyesRequest>() {

                @Override
                public void onSuccess(FourEyesRequest req) {
                    context.setRequest(req);
                    token.done();
                }

                @Override
                public void onFailure(Throwable exception) {
                    token.doneWithError(new FourEyesException(exception));
                }
            });
    }
}
