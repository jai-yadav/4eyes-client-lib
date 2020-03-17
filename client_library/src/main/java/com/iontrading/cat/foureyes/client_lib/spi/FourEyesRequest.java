package com.iontrading.cat.foureyes.client_lib.spi;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.proguard.annotation.KeepAll;

@KeepAll
public interface FourEyesRequest {

    String getRequestId();

    String getActionComment();

    String getCustomFields();

    String getDetails();

    String getEntitlementFeature();

    String getEntitlementNamespace();

    String getEntitlementResource();

    String getEntityCategory();

    String getEntityDescription();

    String getEntityName();

    String getExternalId();

    String getRequestedBy();

    String getRequestGroup();

    String getRequestNamespace();

    String getRequestReason();

    String getRequestReasonDescription();

    String getEntityType();

    String getUserData();

    RequestStatus getStatus();

    String getDataOwner();

	String getActionedBy();

	String getActionTime();

	int getAppliedAfterApproval();

    int getRequestProtocolVersion();

	String getCompletionTime();

	RequestStatus getPreviousStatus();

}