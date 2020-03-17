package com.iontrading.cat.foureyes.client_lib.enums;

import com.iontrading.mkv.enums.MkvFieldType;
import com.iontrading.proguard.annotation.KeepAll;

@KeepAll
public enum Fields {
    
    REQUEST_PROTOCOL_VERSION("RequestProtocolVersion", MkvFieldType.INT),
    
    REQUEST_ID("RequestId", MkvFieldType.STR),
    
    ACTION_COMMENT("ActionComment", MkvFieldType.STR),
    
    CUSTOM_FIELDS("CustomFields", MkvFieldType.STR),
    
    DETAILS("Details", MkvFieldType.STR),
    
    ENTITLEMENT_FEATURE("EntitlementFeature", MkvFieldType.STR),
    
    ENTITLEMENT_NAMESPACE("EntitlementNamespace", MkvFieldType.STR),
    
    ENTITLEMENT_RESOURCE("EntitlementResource", MkvFieldType.STR),
    
    ENTITY_CATEGORY("EntityCategory", MkvFieldType.STR),
    
    ENTITY_DESCRIPTION("EntityDescription", MkvFieldType.STR),
    
    ENTITY_NAME("EntityName", MkvFieldType.STR),
    
    EXTERNAL_ID("ExternalId", MkvFieldType.STR),
    
    REQUESTED_BY("RequestedBy", MkvFieldType.STR),
    
    REQUESTE_GROUP("RequestGroup", MkvFieldType.STR),
    
    REQUESTE_NAMESPACE("RequestNamespace", MkvFieldType.STR),
    
    REQUESTE_REASON("RequestReason", MkvFieldType.STR),
    
    REQUESTE_REASON_DESCRIPTION("RequestReasonDescription", MkvFieldType.STR),
    
    ENTITY_TYPE("EntityType", MkvFieldType.STR),
    
    USER_DATA("UserData", MkvFieldType.STR),
    
    STATUS("Status", MkvFieldType.STR),
    
    PREVIOUS_STATUS("PreviousStatus", MkvFieldType.STR),
    
    ACTIONED_BY("ActionedBy", MkvFieldType.STR),
    
    ACTION_TIME("ActionTime", MkvFieldType.STR),
    
    APPLIED_AFTER_APPROVAL("AppliedAfterApproval", MkvFieldType.INT),
    
    COMPLETION_TIME("CompletionTime", MkvFieldType.STR),
    
    DATA_OWNER("DataOwner", MkvFieldType.STR);
    
    private Fields(String serverString, MkvFieldType type) {
        this.serverString = serverString;
        this.type = type;
    }
    
    private String serverString;
    private MkvFieldType type;
    
    public String getServerString() {
        return serverString;
    }
    
    public MkvFieldType getFieldType() {
        return type;
    }

}
