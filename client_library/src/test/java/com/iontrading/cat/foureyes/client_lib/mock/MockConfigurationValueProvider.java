package com.iontrading.cat.foureyes.client_lib.mock;

import java.util.Iterator;

import com.iontrading.isf.configuration.spi.ConfigurationValueProvider;

public class MockConfigurationValueProvider implements ConfigurationValueProvider {
    
    private Object expectedProperty;

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public Object getPropertyValue(String name) {
        return expectedProperty;
    }
    
    public void setExpectedProperty(Object property) {
        this.expectedProperty = property;
    }

}
