package com.iontrading.cat.foureyes.client_lib.notification;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesNotificationListener;
import com.iontrading.isf.boot.spi.IService;

public class FourEyesNotificationService implements IService {

    private final Set<FourEyesNotificationDispatcher> dispatchers;

    @Inject
    public FourEyesNotificationService(Set<Class<? extends FourEyesNotificationListener>> listeners,
            FourEyesNotificationDispatcherFactory dispatcherFactory, Injector injector) {
        dispatchers = new HashSet<>();
        
        for (Class<? extends FourEyesNotificationListener> listenerClass : listeners) {
            FourEyesNotificationListener listener = injector.getInstance(listenerClass);
            String entity = listener.getEntityType();
            FourEyesNotificationDispatcher dispatcher = dispatcherFactory.create(listener, entity);
            dispatchers.add(dispatcher);
        }

    }

    @Override
    public String getName() {
        return "FourEyesNotificationService";
    }

    @Override
    public void start() throws Exception {
        for (FourEyesNotificationDispatcher dispatcher : dispatchers) {
            dispatcher.start();
        }
    }

    @Override
    public void shutdown() {
        for (FourEyesNotificationDispatcher dispatcher : dispatchers) {
            dispatcher.stop();
        }
    }

}
