package com.iontrading.cat.foureyes.mock.server.xrs;

import com.iontrading.cat.foureyes.client_lib.enums.Fields;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.xrs.api.IXRSObject;

public class XrsFourEyesRequest implements IXRSObject {

    private FourEyesRequest request;

    public XrsFourEyesRequest(FourEyesRequest request) {
        this.request = request;
    }

    @Override
    public String getId() {
        return request.getRequestId();
    }

    @Override
    public Object getFieldValue(String fieldName) {
        Object result = null;

        if (fieldName.equals(Fields.REQUEST_PROTOCOL_VERSION.getServerString())) {
            result = request.getRequestProtocolVersion();
        } else if (fieldName.equals(Fields.REQUEST_ID.getServerString())) {
            result = request.getRequestId();
        } else if (fieldName.equals(Fields.ACTION_COMMENT.getServerString())) {
            result = request.getActionComment();
        } else if (fieldName.equals(Fields.CUSTOM_FIELDS.getServerString())) {
            result = request.getCustomFields();
        } else if (fieldName.equals(Fields.DETAILS.getServerString())) {
            result = request.getDetails();
        } else if (fieldName.equals(Fields.ENTITLEMENT_FEATURE.getServerString())) {
            result = request.getEntitlementFeature();
        } else if (fieldName.equals(Fields.ENTITLEMENT_NAMESPACE.getServerString())) {
            result = request.getEntitlementNamespace();
        } else if (fieldName.equals(Fields.ENTITLEMENT_RESOURCE.getServerString())) {
            result = request.getEntitlementResource();
        } else if (fieldName.equals(Fields.ENTITY_CATEGORY.getServerString())) {
            result = request.getEntityCategory();
        } else if (fieldName.equals(Fields.ENTITY_DESCRIPTION.getServerString())) {
            result = request.getEntityDescription();
        } else if (fieldName.equals(Fields.ENTITY_NAME.getServerString())) {
            result = request.getEntityName();
        } else if (fieldName.equals(Fields.EXTERNAL_ID.getServerString())) {
            result = request.getExternalId();
        } else if (fieldName.equals(Fields.REQUESTED_BY.getServerString())) {
            result = request.getRequestedBy();
        } else if (fieldName.equals(Fields.REQUESTE_GROUP.getServerString())) {
            result = request.getRequestGroup();
        } else if (fieldName.equals(Fields.REQUESTE_NAMESPACE.getServerString())) {
            result = request.getRequestNamespace();
        } else if (fieldName.equals(Fields.REQUESTE_REASON.getServerString())) {
            result = request.getRequestReason();
        } else if (fieldName.equals(Fields.REQUESTE_REASON_DESCRIPTION.getServerString())) {
            result = request.getRequestReasonDescription();
        } else if (fieldName.equals(Fields.ENTITY_TYPE.getServerString())) {
            result = request.getEntityType();
        } else if (fieldName.equals(Fields.USER_DATA.getServerString())) {
            result = request.getUserData();
        } else if (fieldName.equals(Fields.STATUS.getServerString())) {
            result = request.getStatus().toString();
        } else if (fieldName.equals(Fields.DATA_OWNER.getServerString())) {
            result = request.getDataOwner();
        } else if (fieldName.equals(Fields.PREVIOUS_STATUS.getServerString())) {
            result = request.getPreviousStatus();
        } else if (fieldName.equals(Fields.ACTIONED_BY.getServerString())) {
            result = request.getActionedBy();
        } else if (fieldName.equals(Fields.ACTION_TIME.getServerString())) {
            result = request.getActionTime();
        } else if (fieldName.equals(Fields.APPLIED_AFTER_APPROVAL.getServerString())) {
            result = request.getAppliedAfterApproval();
        } else if (fieldName.equals(Fields.COMPLETION_TIME.getServerString())) {
            result = request.getCompletionTime();
        }

        return result;
    }

    @Override
    public boolean isFieldSet(String fieldName) {
        return true;
    }

}
