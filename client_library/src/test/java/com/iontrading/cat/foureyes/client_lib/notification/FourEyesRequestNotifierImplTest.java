package com.iontrading.cat.foureyes.client_lib.notification;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.cat.foureyes.client_lib.mock.MockRequestNotificationListener;
import com.iontrading.cat.foureyes.client_lib.mock.MockSequentialExecutorService;
import com.iontrading.isf.log.support.MockITracer;

public class FourEyesRequestNotifierImplTest {
    
    private FourEyesRequestNotifier notifier;
    private MockRequestNotificationListener listener;
    private ModifiableFourEyesRequest request;
    private MockSequentialExecutorService executor;
    
    @Before
    public void setUp() {
        listener = new MockRequestNotificationListener();
        executor = new MockSequentialExecutorService();
        notifier = new FourEyesRequestNotifierImpl(listener, executor , new MockITracer());
        request = new FourEyesRequestImpl();
    }
    
    @Test
    public void testNotifyRequestWhenApproved() {
        request.setStatus(RequestStatus.Approved);
        notifier.notifyRequest(request);
        executor.runTask();
        Assert.assertEquals(request, listener.getRequestApproved());
    }
    
    @Test
    public void testNotifyRequestWhenPending() {
        request.setStatus(RequestStatus.Pending);
        notifier.notifyRequest(request);
        executor.runTask();
        Assert.assertEquals(request, listener.getRequestPending());
    }
    
    @Test
    public void testNotifyRequestWhenRejected() {
        request.setStatus(RequestStatus.Rejected);
        notifier.notifyRequest(request);
        executor.runTask();
        Assert.assertEquals(request, listener.getRequestRejected());
    }
    
    @Test
    public void testNotifyRequestWhenInvalidStatus() {
        request.setStatus(RequestStatus.Done);
        notifier.notifyRequest(request);
        Assert.assertNull(executor.getTask());
    }
    
    @Test
    public void testNotifyResyncStart() {
        notifier.notifyResyncStart();
        executor.runTask();
        Assert.assertEquals(true, listener.isResyncStartCalled());
    }
    
    @Test
    public void testNotifyResyncEnd() {
        notifier.notifyResyncEnd();
        executor.runTask();
        Assert.assertEquals(true, listener.isResyncEndCalled());
    }
    
    @Test
    public void testNotifyDisconnected() {
        notifier.notifyDisconnected();
        Assert.assertEquals(true, listener.isOnDisconnedCalled());
    }

}
