package com.iontrading.cat.foureyes.client_lib.notification;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.mock.MockFourEyesNotificationDispatcher;
import com.iontrading.cat.foureyes.client_lib.mock.MockFourEyesNotificationDispatcherFactory;
import com.iontrading.cat.foureyes.client_lib.mock.MockInjector;
import com.iontrading.cat.foureyes.client_lib.mock.MockRequestNotificationListener;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesNotificationListener;

public class FourEyesNotificationServiceTest {

    private FourEyesNotificationService notificationService;
    private Set<Class<? extends FourEyesNotificationListener>> listeners;
    private MockFourEyesNotificationDispatcherFactory dispatcherFactory;
    private MockInjector mockInjector;
    private MockFourEyesNotificationDispatcher dispatcher;
    private MockRequestNotificationListener listener;

    @Before
    public void setUp() {
        listeners = new HashSet<>();
        listeners.add(FourEyesNotificationListener.class);
        listener = new MockRequestNotificationListener();
        mockInjector = new MockInjector();
        mockInjector.setGetInstanceResult(FourEyesNotificationListener.class, listener);
        dispatcherFactory = new MockFourEyesNotificationDispatcherFactory();
        dispatcher = new MockFourEyesNotificationDispatcher();   
        dispatcherFactory.setDispatcher(dispatcher);

        notificationService = new FourEyesNotificationService(listeners, dispatcherFactory,
                mockInjector);
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("FourEyesNotificationService", notificationService.getName());
    }

    @Test
    public void testStart() throws Exception {
        notificationService.start();
        Assert.assertEquals(true, dispatcher.isStarted());
    }

    @Test
    public void testStop() throws Exception {
        notificationService.shutdown();
        Assert.assertEquals(true, dispatcher.isStopped());
    }

}
