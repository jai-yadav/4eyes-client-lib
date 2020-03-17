package com.iontrading.cat.foureyes.client_lib.mock;

import com.iontrading.talk.ionbus.mqsubscriber.spi.MQSubscription;

public class MockMQSubscription implements MQSubscription {

    @Override
    public String getQueueName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean cancel() {
        // TODO Auto-generated method stub
        return false;
    }

}
