package com.iontrading.cat.foureyes.mock.server;

import com.google.inject.Inject;
import com.iontrading.cat.foureyes.mock.server.entities.NotificationDispatcher;
import com.iontrading.cat.foureyes.mock.server.entities.StorageManager;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.commons.async.AsyncResultPromise;
import com.iontrading.isf.commons.async.AsyncResults;
import com.iontrading.isf.service_manager.spi.IBusServiceManager;
import com.iontrading.talk.api.annotation.TalkFunction;
import com.iontrading.talk.api.annotation.TalkParam;

public class DummyFourEyeServerFunctions {

    private StorageManager storage;
    private NotificationDispatcher notifier;
    private IBusServiceManager serviceManager;

    @Inject
    public DummyFourEyeServerFunctions(StorageManager storage, NotificationDispatcher notifier,
            IBusServiceManager serviceManager) {
        this.storage = storage;
        this.notifier = notifier;
        this.serviceManager = serviceManager;
    }

    @TalkFunction
    public AsyncResult<String> deleteAllRequests() {
        final AsyncResultPromise<String> result = AsyncResults.create();
        storage.deleteAll(notifier);
        result.success("Deleted All");
        return result;

    }

    @TalkFunction
    public AsyncResult<String> activeSubmitterActionsService() {
        final AsyncResultPromise<String> result = AsyncResults.create();
        serviceManager.activateService(ApplicationModule.FOUR_EYES_SUBMITTER_SERVICE);
        result.success("Activated " + ApplicationModule.FOUR_EYES_SUBMITTER_SERVICE + " service.");
        return result;

    }

    @TalkFunction
    public AsyncResult<String> deactiveSubmitterActionsService() {
        final AsyncResultPromise<String> result = AsyncResults.create();
        serviceManager.deactivateService(ApplicationModule.FOUR_EYES_SUBMITTER_SERVICE);
        result.success("Deactivated " + ApplicationModule.FOUR_EYES_SUBMITTER_SERVICE + " service.");
        return result;

    }

    @TalkFunction(name="Approve4EyesRequest")
    public AsyncResult<String> approvePendingRequest(@TalkParam(name = "ProtocolVersion") int protocolVersion, @TalkParam(name = "RequestId") String id, @TalkParam(name = "Comment", nullable = true) String comment) {
        final AsyncResultPromise<String> result = AsyncResults.create();
        storage.approve(notifier, id, comment);
        result.success("Approved Pending reuqest, Id: " + id);
        return result;
    }

    @TalkFunction(name="Reject4EyesRequest")
    public AsyncResult<String> rejectPendingRequest(@TalkParam(name = "ProtocolVersion") int protocolVersion, @TalkParam(name = "RequestId") String id, @TalkParam(name = "Comment", nullable = true) String comment) {
        final AsyncResultPromise<String> result = AsyncResults.create();
        storage.reject(notifier, id, comment);
        result.success("Rejected Pending reuqest, Id: " + id);
        return result;
    }
}