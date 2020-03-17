package com.iontrading.cat.foureyes.client_lib.mock;

import com.iontrading.talk.functions.api.ImportDescriptor;
import com.iontrading.talk.functions.spi.Importer;

public class MockImporter implements Importer {

    private Object functions;

    @Override
    public <X> ImportBuilder<X> use(Class<X> serviceClass) {
        return null;
    }

    @Override
    public <X> X importFunctions(Class<X> serviceClass) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> X importFunctions(Class<X> serviceClass, String source) {
        return (X) functions;
    }

    @Override
    public <X> X importFunctions(ImportDescriptor descriptor) {
        return null;
    }
    
    public void setImportFunctionsResult(Object expected) {
        this.functions = expected;
    }

}
