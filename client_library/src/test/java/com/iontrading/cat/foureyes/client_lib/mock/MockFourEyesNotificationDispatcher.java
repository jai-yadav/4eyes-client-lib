package com.iontrading.cat.foureyes.client_lib.mock;

import com.iontrading.cat.foureyes.client_lib.notification.FourEyesNotificationDispatcher;

public class MockFourEyesNotificationDispatcher implements FourEyesNotificationDispatcher {

    private boolean started;
    private boolean stopped;

    @Override
    public void start() {
         started = true;

    }
    
    public boolean isStarted() {
        return started;
    }

    @Override
    public void stop() {
         this.stopped = true;
        
    }
    
    public boolean isStopped() {
        return stopped;
    }

}
