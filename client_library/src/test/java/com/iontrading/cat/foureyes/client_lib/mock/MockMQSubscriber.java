package com.iontrading.cat.foureyes.client_lib.mock;

import com.iontrading.talk.ionbus.mqsubscriber.spi.MQSubscriber;
import com.iontrading.talk.ionbus.mqsubscriber.spi.MQSubscriptionBuilder;

public class MockMQSubscriber<T> implements MQSubscriber {
    
    private MockMQSubscriptionBuilder<T> builder;
    
    @SuppressWarnings("unchecked")
    @Override
    public <BEAN> MQSubscriptionBuilder<BEAN> subscribe(String queueName, Class<BEAN> talkTypeBean) {
        this.builder = new MockMQSubscriptionBuilder<>(); 
        return (MockMQSubscriptionBuilder<BEAN>) builder;
    }
    
    public MockMQSubscriptionBuilder<T> getBuilder(){
        return builder;
    }

}
