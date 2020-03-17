package com.iontrading.cat.foureyes.mock.server.entities;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;

@ModuleDescriptor
public class FourEyesEntitiesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StorageManager.class).to(StorageManagerImpl.class).in(Singleton.class);
        bind(NotificationDispatcher.class).to(NotificationDispatcherImpl.class).in(Singleton.class);
    }

}
