package com.iontrading.cat.foureyes.client_lib.notification;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;

public interface ModifiableFourEyesRequest extends FourEyesRequest {

    void setActionComment(String comment);

    void setCustomFields(String customFields);

    void setDetails(String details);

    void setEntitlementFeature(String entitlementFeature);

    void setEntitlementNamespace(String entitlementNamespace);

    void setEntitlementResource(String entitlementResource);

    void setEntityCategory(String entityCategory);

    void setEntityDescription(String entityDescription);

    void setEntityName(String entityName);

    void setExternalId(String externalId);

    void setRequestedBy(String requestedBy);

    void setRequestGroup(String requestGroup);

    void setRequestNamespace(String requestNamespace);

    void setRequestReason(String requestReason);

    void setRequestReasonDescription(String requestReasonDescription);

    void setEntityType(String entityType);

    void setUserData(String userData);
    
    void setStatus(RequestStatus status);

    void setDataOwner(String dataOwner);
	
	void setActionedBy(String actionedBy);
	
	void setActionTime(String actionTime);
	
	void setAppliedAfterApproval(int isApplied);
	
	void setCompletionTime(String completionTime);
	
	void setPreviousStatus(RequestStatus previousStatus);

}
