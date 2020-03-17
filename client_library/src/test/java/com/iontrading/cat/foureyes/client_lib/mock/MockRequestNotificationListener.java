package com.iontrading.cat.foureyes.client_lib.mock;

import com.iontrading.cat.foureyes.client_lib.spi.FourEyesNotificationListener;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;

public class MockRequestNotificationListener implements FourEyesNotificationListener {

    private FourEyesRequest requestApproved;
    private FourEyesRequest requestPending;
    private FourEyesRequest requestRejected;
    private boolean resyncStartCalled;
    private boolean resyncEndCalled;
    private boolean onDisconnedCalled;

    @Override
    public void onResyncStart() {
        this.resyncStartCalled = true;

    }

    @Override
    public void onResyncEnd() {
        this.resyncEndCalled = true;

    }

    @Override
    public void onDisconnected() {
        this.onDisconnedCalled = true;

    }

    @Override
    public void onRequestPending(FourEyesRequest request) {
        this.requestPending = request;

    }

    @Override
    public void onRequestApproved(FourEyesRequest request) {
        this.requestApproved = request;

    }

    @Override
    public void onRequestRejected(FourEyesRequest request) {
        this.requestRejected = request;

    }

    @Override
    public String getEntityType() {
        return "MockEntity";
    }

    public FourEyesRequest getRequestApproved() {
        return requestApproved;
    }

    public FourEyesRequest getRequestPending() {
        return requestPending;
    }

    public FourEyesRequest getRequestRejected() {
        return requestRejected;
    }

    public boolean isResyncStartCalled() {
        return resyncStartCalled;
    }

    public boolean isResyncEndCalled() {
        return resyncEndCalled;
    }

    public boolean isOnDisconnedCalled() {
        return onDisconnedCalled;
    }

}
