package com.iontrading.cat.foureyes.client_lib.mock;

import com.iontrading.cat.foureyes.client_lib.notification.FourEyesNotificationDispatcher;
import com.iontrading.cat.foureyes.client_lib.notification.FourEyesNotificationDispatcherFactory;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesNotificationListener;

public class MockFourEyesNotificationDispatcherFactory implements FourEyesNotificationDispatcherFactory {

    private FourEyesNotificationDispatcher dispatcher;

    @Override
    public FourEyesNotificationDispatcher create(FourEyesNotificationListener listener, String entityName) {
        return getDispatcher();
    }

    public FourEyesNotificationDispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(FourEyesNotificationDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

}
