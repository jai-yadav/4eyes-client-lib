package com.iontrading.cat.foureyes.client_lib.mock;

import java.util.List;

import com.iontrading.cat.foureyes.client_lib.bus.NotificationFunctions;
import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.commons.async.AsyncResultPromise;
import com.iontrading.isf.commons.async.AsyncResults;

public class MockFourEyesNotificationFunctions implements NotificationFunctions {

    private boolean isSearchRequestsCalled;
    private boolean searchRequestExpectedReturn=true;

    @Override
    public AsyncResult<String> searchRequests(Integer version, String dataOwner, String entityName, List<RequestStatus> statuses) {
        AsyncResultPromise<String>  result = AsyncResults.create();
        if(searchRequestExpectedReturn) result.success("QueueName");
        else result.failure(new Throwable());
        this.isSearchRequestsCalled = true;
        return result;
    }

    public boolean isSearchRequestsCalled() {
        return isSearchRequestsCalled;
    }

    public void setSearchRequestExpectedReturn(boolean searchRequestExpectedReturn) {
        this.searchRequestExpectedReturn = searchRequestExpectedReturn;
    }

}
