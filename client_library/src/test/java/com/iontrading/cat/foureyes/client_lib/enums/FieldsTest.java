package com.iontrading.cat.foureyes.client_lib.enums;


import org.junit.Assert;
import org.junit.Test;

import com.iontrading.mkv.enums.MkvFieldType;

public class FieldsTest {
    
    @Test
    public void testGetServerString() {
        Assert.assertEquals("RequestProtocolVersion", Fields.REQUEST_PROTOCOL_VERSION.getServerString());
        Assert.assertEquals("RequestId", Fields.REQUEST_ID.getServerString());
        Assert.assertEquals("ActionComment", Fields.ACTION_COMMENT.getServerString());
        Assert.assertEquals("CustomFields", Fields.CUSTOM_FIELDS.getServerString());
        Assert.assertEquals("Details", Fields.DETAILS.getServerString());
        Assert.assertEquals("EntitlementFeature", Fields.ENTITLEMENT_FEATURE.getServerString());
        Assert.assertEquals("EntitlementNamespace", Fields.ENTITLEMENT_NAMESPACE.getServerString());
        Assert.assertEquals("EntitlementResource", Fields.ENTITLEMENT_RESOURCE.getServerString());
        Assert.assertEquals("EntityCategory", Fields.ENTITY_CATEGORY.getServerString());
        Assert.assertEquals("EntityDescription", Fields.ENTITY_DESCRIPTION.getServerString());
        Assert.assertEquals("EntityName", Fields.ENTITY_NAME.getServerString());
        Assert.assertEquals("ExternalId", Fields.EXTERNAL_ID.getServerString());
        Assert.assertEquals("RequestedBy", Fields.REQUESTED_BY.getServerString());
        Assert.assertEquals("RequestGroup", Fields.REQUESTE_GROUP.getServerString());
        Assert.assertEquals("RequestNamespace", Fields.REQUESTE_NAMESPACE.getServerString());
        Assert.assertEquals("RequestReason", Fields.REQUESTE_REASON.getServerString());
        Assert.assertEquals("RequestReasonDescription", Fields.REQUESTE_REASON_DESCRIPTION.getServerString());
        Assert.assertEquals("EntityType", Fields.ENTITY_TYPE.getServerString());
        Assert.assertEquals("UserData", Fields.USER_DATA.getServerString());
        Assert.assertEquals("Status", Fields.STATUS.getServerString());
    }
    
    @Test
    public void testGetFieldType() {
        Assert.assertEquals(MkvFieldType.INT, Fields.REQUEST_PROTOCOL_VERSION.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.REQUEST_ID.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.ACTION_COMMENT.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.CUSTOM_FIELDS.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.DETAILS.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.ENTITLEMENT_FEATURE.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.ENTITLEMENT_NAMESPACE.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.ENTITLEMENT_RESOURCE.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.ENTITY_CATEGORY.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.ENTITY_DESCRIPTION.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.ENTITY_NAME.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.EXTERNAL_ID.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.REQUESTED_BY.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.REQUESTE_GROUP.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.REQUESTE_NAMESPACE.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.REQUESTE_REASON.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.REQUESTE_REASON_DESCRIPTION.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.ENTITY_TYPE.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.USER_DATA.getFieldType());
        Assert.assertEquals(MkvFieldType.STR, Fields.STATUS.getFieldType());
    }

}
