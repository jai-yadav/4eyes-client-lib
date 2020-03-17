package com.iontrading.cat.foureyes.client_lib.bus;

import java.util.List;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.talk.api.annotation.TalkFunction;
import com.iontrading.talk.api.annotation.TalkParam;
import com.iontrading.talk.api.annotation.TalkResult;

public interface NotificationFunctions {

    @TalkFunction(name = "Search4EyesRequestForSubmitter", result = @TalkResult(name = "QueueName"))
    AsyncResult<String> searchRequests(@TalkParam(name = "ProtocolVersion", nullable=true) Integer version,
            @TalkParam(name = "DataOwner", nullable = true) String dataOwner,
            @TalkParam(name = "EntityType", nullable = true) String entityType,
            @TalkParam(name = "Status", nullable = true) List<RequestStatus> list);
}
