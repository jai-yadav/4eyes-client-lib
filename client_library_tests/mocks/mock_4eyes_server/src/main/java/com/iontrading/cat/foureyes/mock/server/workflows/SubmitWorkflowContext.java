package com.iontrading.cat.foureyes.mock.server.workflows;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.talk.ionbus.spi.IonBusInfo;

public class SubmitWorkflowContext extends BaseFourEyesContext {

    private Integer protocolVersion;
    private final String comment;
    private final String customFields;
    private final String details;
    private final String entitlementFeature;
    private final String entitlementNamespace;
    private final String entitlementResource;
    private final String entityCategory;
    private final String entityDescription;
    private final String entityName;
    private final String externalId;
    private final String requestedBy;
    private final String requestGroup;
    private final String requestNamespace;
    private final String requestReason;
    private final String requestReasonDescription;
    private final String entityType;
    private final String userData;
    private IonBusInfo busInfo;

    public SubmitWorkflowContext() {
        super(null);
        this.protocolVersion = null;
        this.comment = null;
        this.customFields = null;
        this.details = null;
        this.entitlementFeature = null;
        this.entitlementNamespace = null;
        this.entitlementResource = null;
        this.entityCategory = null;
        this.entityDescription = null;
        this.entityName = null;
        this.externalId = null;
        this.requestedBy = null;
        this.requestGroup = null;
        this.requestNamespace = null;
        this.requestReason = null;
        this.requestReasonDescription = null;
        this.entityType = null;
        this.userData = null;
        this.busInfo = null;
    }

    public SubmitWorkflowContext(Integer protocolVersion, String id, String comment, String customFields, String details,
            String entitlementFeature, String entitlementNamespace, String entitlementResource, String entityCategory,
            String entityDescription, String entityName, String externalId, String requestedBy, String requestGroup,
            String requestNamespace, String requestReason, String requestReasonDescription, String entityType,
            String userData, IonBusInfo busInfo) {
        super(id);
        this.protocolVersion = protocolVersion;
        this.comment = comment;
        this.customFields = customFields;
        this.details = details;
        this.entitlementFeature = entitlementFeature;
        this.entitlementNamespace = entitlementNamespace;
        this.entitlementResource = entitlementResource;
        this.entityCategory = entityCategory;
        this.entityDescription = entityDescription;
        this.entityName = entityName;
        this.externalId = externalId;
        this.requestedBy = requestedBy;
        this.requestGroup = requestGroup;
        this.requestNamespace = requestNamespace;
        this.requestReason = requestReason;
        this.requestReasonDescription = requestReasonDescription;
        this.entityType = entityType;
        this.userData = userData;
        this.busInfo = busInfo;
    }
    
    public String getComment() {
        return comment;
    }

    public String getCustomFields() {
        return customFields;
    }

    public String getDetails() {
        return details;
    }

    public String getEntitlementFeature() {
        return entitlementFeature;
    }

    public String getEntitlementNamespace() {
        return entitlementNamespace;
    }

    public String getEntitlementResource() {
        return entitlementResource;
    }

    public String getEntityCategory() {
        return entityCategory;
    }

    public String getEntityDescription() {
        return entityDescription;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public String getRequestGroup() {
        return requestGroup;
    }

    public String getRequestNamespace() {
        return requestNamespace;
    }

    public String getRequestReason() {
        return requestReason;
    }

    public String getRequestReasonDescription() {
        return requestReasonDescription;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getUserData() {
        return userData;
    }

    @Override
    public RequestStatus[] getValidStatuses() {
        return new RequestStatus[0];
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public IonBusInfo getBusInfo() {
        return busInfo;
    }

}
