package com.iontrading.cat.foureyes.client_lib.mock;

import com.google.inject.Provider;

public class MockProvider<T> implements Provider<T>{

    private T getResult;

    @Override
    public T get() {
        return getResult;
    }
    
    public void setExpectedGet(T expected) {
        this.getResult = expected;
    }

}
