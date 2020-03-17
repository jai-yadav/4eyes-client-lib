package com.iontrading.cat.foureyes.client_lib.notification;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.talk.api.annotation.TalkProperty;
import com.iontrading.talk.api.annotation.TalkType;

@TalkType
public class FourEyesRequestImpl implements ModifiableFourEyesRequest {

    private String actionedBy;
    private String actionComment;
    private String actionTime;
    private int appliedAfterApproval;
    private String completionTime;
    private String creationTime;
    private String customFields;
    private String dataOwner;
    private String details;
    private String entitlementFeature;
    private String entitlementNamespace;
    private String entitlementResource;
    private String entityCategory;
    private String entityDescription;
    private String entityName;
    private String externalId;
    private RequestStatus previousStatus;
    private String requestedBy;
    private String requestGroup;
    private String requestId;
    private String requestNamespace;
    private int requestProtocolVersion;
    private String requestReason;
    private String requestReasonDescription;
    private String entityType;
    private RequestStatus status;
    private String userData;


    @Override
    @TalkProperty
    public String getRequestId() {
        return requestId;
    }

    @Override
    @TalkProperty(nullable = true)
    public String getActionComment() {
        return actionComment;
    }

    @Override
    public void setActionComment(String comment) {
        this.actionComment = comment;
    }

    @Override
    @TalkProperty(nullable = true)
    public String getCustomFields() {
        return customFields;
    }

    @Override
    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }

    @Override
    @TalkProperty
    public String getDetails() {
        return details;
    }

    @Override
    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    @TalkProperty(nullable = true)
    public String getEntitlementFeature() {
        return entitlementFeature;
    }

    @Override
    public void setEntitlementFeature(String entitlementFeature) {
        this.entitlementFeature = entitlementFeature;
    }

    @Override
    @TalkProperty
    public String getEntitlementNamespace() {
        return entitlementNamespace;
    }

    @Override
    public void setEntitlementNamespace(String entitlementNamespace) {
        this.entitlementNamespace = entitlementNamespace;
    }

    @Override
    @TalkProperty
    public String getEntitlementResource() {
        return entitlementResource;
    }

    @Override
    public void setEntitlementResource(String entitlementResource) {
        this.entitlementResource = entitlementResource;
    }

    @Override
    @TalkProperty
    public String getEntityCategory() {
        return entityCategory;
    }
    
    @Override
    public void setEntityCategory(String entityCategory) {
        this.entityCategory = entityCategory;
    }

    @Override
    @TalkProperty(nullable = true)
    public String getEntityDescription() {
        return entityDescription;
    }

    @Override
    public void setEntityDescription(String entityDescription) {
        this.entityDescription = entityDescription;
    }

    @Override
    @TalkProperty
    public String getEntityName() {
        return entityName;
    }
    
    @Override
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Override
    @TalkProperty(nullable = true)
    public String getExternalId() {
        return externalId;
    }

    @Override
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    @TalkProperty
    public String getRequestedBy() {
        return requestedBy;
    }

    @Override
    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    @Override
    @TalkProperty(nullable = true)
    public String getRequestGroup() {
        return requestGroup;
    }

    @Override
    public void setRequestGroup(String requestGroup) {
        this.requestGroup = requestGroup;
    }

    @Override
    @TalkProperty(nullable = true)
    public String getRequestNamespace() {
        return requestNamespace;
    }

    @Override
    public void setRequestNamespace(String requestNamespace) {
        this.requestNamespace = requestNamespace;
    }

    @Override
    @TalkProperty(nullable = true)
    public String getRequestReason() {
        return requestReason;
    }

    @Override
    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    @Override
    @TalkProperty(nullable = true)
    public String getRequestReasonDescription() {
        return requestReasonDescription;
    }

    @Override
    public void setRequestReasonDescription(String requestReasonDescription) {
        this.requestReasonDescription = requestReasonDescription;
    }

    @Override
    @TalkProperty
    public String getEntityType() {
        return entityType;
    }

    @Override
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    @Override
    @TalkProperty(nullable = true)
    public String getUserData() {
        return userData;
    }

    @Override
    public void setUserData(String userData) {
        this.userData = userData;
    }

    @Override
    @TalkProperty
    public RequestStatus getStatus() {
        return status;
    }
    
    @Override
    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public int getRequestProtocolVersion() {
        return requestProtocolVersion;
    }

    @Override
    @TalkProperty
    public String getDataOwner() {
        return dataOwner;
    }

    @Override
    public void setDataOwner(String dataOwner) {
        this.dataOwner = dataOwner;
    }

    @Override
    @TalkProperty
	public String getActionedBy() {
		return actionedBy;
	}
    
    @Override
    @TalkProperty
	public String getActionTime() {
		return actionTime;
	}

    @Override
    @TalkProperty(nullable = true)
	public int getAppliedAfterApproval() {
		return appliedAfterApproval;
	}
    
    @Override
    @TalkProperty(nullable = true)
	public String getCompletionTime() {
		return completionTime;
	}

	public String getCreationTime() {
		return creationTime;
	}
    
    @Override
    @TalkProperty(nullable = true)
	public RequestStatus getPreviousStatus() {
		return previousStatus;
	}

	@Override
	public void setPreviousStatus(RequestStatus previousStatus) {
		this.previousStatus = previousStatus;
	}

	@Override
	public void setAppliedAfterApproval(int isApplied) {
		this.appliedAfterApproval = isApplied;
	}

	@Override
	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}

	@Override
	public void setActionedBy(String actionedBy) {
		this.actionedBy = actionedBy;
	}

	@Override
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
    
}
