package com.iontrading.cat.foureyes.mock.server.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;

public class FourEyesRequestImpl implements FourEyesRequest {

    private int protocolVersion;
    private final String id;
    private String comment;
    private String customFields;
    private String details;
    private String entitlementFeature; // Mandatory
    private String entitlementNamespace; // Mandatory
    private String entitlementResource; // Mandatory
    private String entityCategory; // Mandatory
    private String entityDescription; // Mandatory
    private String entityName; // Mandatory
    private String externalId;
    private String requestedBy; // Mandatory
    private String requestGroup;
    private String requestNamespace;
    private String requestReason;
    private String requestReasonDescription;
    private String entityType; // Mandatory
    private String userData;
    private String dataOwner;

    private RequestStatus status;
	private String actionedBy;
	private String actionTime;
	private int appliedAfterApproval;
	private String completionTime;
	private RequestStatus previousStatus;

    public FourEyesRequestImpl(int protocolVersion, String id, String comment, String customFields, String details,
            String entitlementFeature, String entitlementNamespace, String entitlementResource, String entityCategory,
            String entityDescription, String entityName, String externalId, String requestedBy, String requestGroup,
            String requestNamespace, String requestReason, String requestReasonDescription, String entityType,
            String userData, String dataOwner) {
        this.protocolVersion = protocolVersion;
        this.id = id;
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
        this.status = RequestStatus.Pending;
        this.dataOwner = dataOwner;
        this.actionedBy="DataOwner";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.actionTime = dateFormat.format(new Date());
        this.appliedAfterApproval = 0;
        this.previousStatus = null;
    }

    @Override
    public String getRequestId() {
        return id;
    }

    @Override
    public String getActionComment() {
        return comment;
    }

    public void setActionComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String getCustomFields() {
        return customFields;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }

    @Override
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String getEntitlementFeature() {
        return entitlementFeature;
    }

    public void setEntitlementFeature(String entitlementFeature) {
        this.entitlementFeature = entitlementFeature;
    }

    @Override
    public String getEntitlementNamespace() {
        return entitlementNamespace;
    }

    public void setEntitlementNamespace(String entitlementNamespace) {
        this.entitlementNamespace = entitlementNamespace;
    }

    @Override
    public String getEntitlementResource() {
        return entitlementResource;
    }

    public void setEntitlementResource(String entitlementResource) {
        this.entitlementResource = entitlementResource;
    }

    @Override
    public String getEntityCategory() {
        return entityCategory;
    }
    
    public void setEntityCategory(String entityCategory) {
        this.entityCategory = entityCategory;
    }

    @Override
    public String getEntityDescription() {
        return entityDescription;
    }

    public void setEntityDescription(String entityDescription) {
        this.entityDescription = entityDescription;
    }

    @Override
    public String getEntityName() {
        return entityName;
    }
    
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    @Override
    public String getRequestGroup() {
        return requestGroup;
    }

    public void setRequestGroup(String requestGroup) {
        this.requestGroup = requestGroup;
    }

    @Override
    public String getRequestNamespace() {
        return requestNamespace;
    }

    public void setRequestNamespace(String requestNamespace) {
        this.requestNamespace = requestNamespace;
    }

    @Override
    public String getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    @Override
    public String getRequestReasonDescription() {
        return requestReasonDescription;
    }

    public void setRequestReasonDescription(String requestReasonDescription) {
        this.requestReasonDescription = requestReasonDescription;
    }

    @Override
    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    @Override
    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    @Override
    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public int getRequestProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public String getDataOwner() {
        return dataOwner;
    }

    public void setDataOwner(String dataOwner) {
        this.dataOwner = dataOwner;
    }

	@Override
	public String getActionedBy() {
		return actionedBy;
	}

	@Override
	public String getActionTime() {
		return actionTime;
	}

	@Override
	public int getAppliedAfterApproval() {
		return appliedAfterApproval;
	}

	@Override
	public String getCompletionTime() {
		return completionTime;
	}

	@Override
	public RequestStatus getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(RequestStatus previousStatus) {
		this.previousStatus = previousStatus;
	}

	public void setAppliedAfterApproval(int isApplied) {
		this.appliedAfterApproval = isApplied;
	}

	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}

	public void setActionedBy(String actionedBy) {
		this.actionedBy = actionedBy;
	}

	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
    
}
