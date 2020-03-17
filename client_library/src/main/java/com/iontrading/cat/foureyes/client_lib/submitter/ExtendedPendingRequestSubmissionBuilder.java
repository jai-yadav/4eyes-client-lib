package com.iontrading.cat.foureyes.client_lib.submitter;

import java.util.List;

import com.iontrading.cat.foureyes.client_lib.submitter.customFields.CustomField;

public interface ExtendedPendingRequestSubmissionBuilder extends PendingRequestSubmissionBuilder {

    String getUserData();

    String getEntityType();

    String getRequestReasonDescription();

    String getRequestReason();

    String getRequestNamespace();

    String getRequestGroup();

    String getRequestedBy();

    String getExternalId();

    String getEntityName();

    String getEntityDescription();

    String getEntityCategory();

    String getEntitlementResource();

    String getEntitlementNamespace();

    String getEntitlementFeature();

    String getDetails();

    String getCustomFieldsJson();

    String getComment();

    Integer getProtocolVersion();

	List<CustomField> getCustomFiledsList();

}
