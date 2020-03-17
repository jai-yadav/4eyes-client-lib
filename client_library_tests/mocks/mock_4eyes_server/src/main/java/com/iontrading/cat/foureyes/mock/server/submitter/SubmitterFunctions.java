package com.iontrading.cat.foureyes.mock.server.submitter;

import com.iontrading.cat.foureyes.client_lib.bus.FourEyesFunctionResult;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.proguard.annotation.KeepAll;
import com.iontrading.talk.api.annotation.TalkFunction;
import com.iontrading.talk.api.annotation.TalkParam;
import com.iontrading.talk.ionbus.spi.IonBusInfo;

@KeepAll
public interface SubmitterFunctions {

    @TalkFunction(name = "Submit4EyesRequest")
    AsyncResult<FourEyesFunctionResult> submit4EyesRequest(@TalkParam(name = "ProtocolVersion") int protocolVersion,
            @TalkParam(name = "Comment", nullable = true) String comment,
            @TalkParam(name = "CustomFields", nullable = true) String customFields,
            @TalkParam(name = "Details", nullable = true) String details,
            @TalkParam(name = "EntitlementFeature", nullable = true) String entitlementFeature,
            @TalkParam(name = "EntitlementNamespace", nullable = true) String entitlementNamespace,
            @TalkParam(name = "EntitlementResource", nullable = true) String entitlementResource,
            @TalkParam(name = "EntityCategory", nullable = true) String entityCategory,
            @TalkParam(name = "EntityType", nullable = true) String entityType,
            @TalkParam(name = "EntityDescription", nullable = true) String entityDescription,
            @TalkParam(name = "EntityName", nullable = true) String entityName,
            @TalkParam(name = "ExternalId", nullable = true) String externalId,
            @TalkParam(name = "RequestedBy", nullable = true) String requestedBy,
            @TalkParam(name = "RequestGroup", nullable = true) String requestGroup,
            @TalkParam(name = "RequestNamespace", nullable = true) String requestNamespace,
            @TalkParam(name = "RequestReason", nullable = true) String requestReason,
            @TalkParam(name = "RequestReasonDescription", nullable = true) String requestReasonDescription,
            @TalkParam(name = "UserData", nullable = true) String userData, IonBusInfo busInfo);

    @TalkFunction(name = "4EyesRequestProcessed")
    AsyncResult<FourEyesFunctionResult> fourEyesRequestProcessed(@TalkParam(name = "ProtocolVersion") int protocolVersion,
            @TalkParam(name = "RequestId") String requestId, @TalkParam(name = "Comment", nullable = true) String comment);

    @TalkFunction(name = "4EyesRequestDenied")
    AsyncResult<FourEyesFunctionResult> fourEyesRequestDenied(@TalkParam(name = "ProtocolVersion") int protocolVersion,
            @TalkParam(name = "RequestId") String requestId, @TalkParam(name = "Comment", nullable = true) String comment);
    
    @TalkFunction(name = "Withdraw4EyesRequest")
    AsyncResult<FourEyesFunctionResult> withdrawPendingRequest(@TalkParam(name = "ProtocolVersion") int protocolVersion,
            @TalkParam(name = "RequestId") String requestId, @TalkParam(name = "Comment", nullable = true) String comment);
}
