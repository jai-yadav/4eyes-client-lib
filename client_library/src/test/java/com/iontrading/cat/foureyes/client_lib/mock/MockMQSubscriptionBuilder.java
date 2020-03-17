package com.iontrading.cat.foureyes.client_lib.mock;

import java.util.concurrent.TimeUnit;

import com.iontrading.isf.log.adapters.TracePriority;
import com.iontrading.talk.api.annotation.PrimitiveType;
import com.iontrading.talk.ionbus.mqsubscriber.spi.MQSubscription;
import com.iontrading.talk.ionbus.mqsubscriber.spi.MQSubscriptionBuilder;
import com.iontrading.talk.ionbus.mqsubscriber.spi.listener.MQActionListener;
import com.iontrading.talk.ionbus.mqsubscriber.spi.listener.MQDataListener;
import com.iontrading.talk.ionbus.mqsubscriber.spi.listener.MQLifecycleListener;
import com.iontrading.talk.ionbus.mqsubscriber.spi.listener.MQListener;

public class MockMQSubscriptionBuilder<T> implements MQSubscriptionBuilder<T> {

    private MQListener mqListener;

    @Override
    public MQSubscriptionBuilder<T> withListener(MQListener mqListener) {
        this.mqListener = mqListener;
        return this;
    }

    @Override
    public MQSubscriptionBuilder<T> withLifecycleListener(MQLifecycleListener lifecycleListener) {

        return this;
    }

    @Override
    public MQSubscriptionBuilder<T> withDataListener(MQDataListener<T> dataListener) {

        return this;
    }

    @Override
    public MQSubscriptionBuilder<T> withActionListener(MQActionListener actionListener) {

        return this;
    }

    @Override
    public MQSubscriptionBuilder<T> withExecutor(String threadPoolName) {

        return this;
    }

    @Override
    public MQSubscriptionBuilder<T> withSupplyLogLevel(TracePriority level) {

        return this;
    }

    @Override
    public MQSubscriptionBuilder<T> withNullEquivalent(PrimitiveType type, Object nullEquivalentValue) {

        return this;
    }

    @Override
    public MQSubscriptionBuilder<T> withFlowControlThresholds(int suspensionThreshold, int resumeThreshold) {

        return this;
    }

    @Override
    public MQSubscriptionBuilder<T> withTimeout(long time, TimeUnit unit) {

        return this;
    }

    @Override
    public MQSubscriptionBuilder<T> ignoreDataOnDeleteNotification() {

        return this;
    }

    @Override
    public MQSubscription start() {

        return new MockMQSubscription();
    }

    public MQListener getMQListener() {
        return mqListener;
    }

}
