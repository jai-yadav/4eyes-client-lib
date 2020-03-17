package com.iontrading.cat.foureyes.mock.dataowner.modules;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.iontrading.cat.foureyes.mock.dataowner.annotation.DataOwner;
import com.iontrading.cat.foureyes.mock.dataowner.xrs.NotificationDispatcher;
import com.iontrading.cat.foureyes.mock.dataowner.xrs.NotificationDispatcherImpl;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.xrs.api.ContextInitInfoProvider;
import com.iontrading.xrs.api.IQueryModule;
import com.iontrading.xrs.api.IRealTimeModule;
import com.iontrading.xrs.api.ISnapshotModule;
import com.iontrading.xrs.api.IStructureModule;
import com.iontrading.xrs.api.lib.ContextInitInfo;
import com.iontrading.xrs.api.lib.ContextModuleCollection;
import com.iontrading.xrs.guice.XrsModule;

@ModuleDescriptor(requires = { XrsModule.class, FourEyesXrsModulesModule.class })
public class FourEyesXrsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(NotificationDispatcher.class).to(NotificationDispatcherImpl.class).in(Singleton.class);
        
        XrsModule.registerContext(binder(), FourEyesRequestsContextProvider.class);
    }

}

class FourEyesRequestsContextProvider implements ContextInitInfoProvider {

    private IStructureModule structureModule;
    private ISnapshotModule snapshotModule;
    private IQueryModule queryModule;
    private IRealTimeModule realtimeModule;

    @Inject
    public FourEyesRequestsContextProvider(@DataOwner IStructureModule structureModule,
            @DataOwner ISnapshotModule snapshotModule, @DataOwner IQueryModule queryModule,
            @DataOwner IRealTimeModule realtimeModule) {
        this.structureModule = structureModule;
        this.snapshotModule = snapshotModule;
        this.queryModule = queryModule;
        this.realtimeModule = realtimeModule;
    }

    @Override
    public ContextInitInfo getContext() {
        ContextInitInfo context = new ContextInitInfo();

        context.setContextName("4EyesRequestRecieved").setContextObjectName("4EyesRequestRecieved");

        context.exportService("4EyesRequestRecievedService");

        context.publishSearchOnBehalfFunction(false);
        context.setSupportFindLight(false);

        ContextModuleCollection modules = new ContextModuleCollection();
        modules.setStructureModule(structureModule);
        modules.setSnapshotModule(snapshotModule);
        modules.setQueryModule(queryModule);
        modules.setRealTimeModule(realtimeModule);
        context.setModuleCollection(modules);

        return context;
    }

}