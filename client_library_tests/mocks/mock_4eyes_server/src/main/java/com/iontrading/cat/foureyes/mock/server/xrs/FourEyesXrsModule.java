package com.iontrading.cat.foureyes.mock.server.xrs;

import javax.inject.Inject;

import com.google.inject.AbstractModule;
import com.iontrading.cat.foureyes.mock.server.annotation.FourEyes;
import com.iontrading.cat.foureyes.mock.server.modules.FourEyesXrsModulesModule;
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
        XrsModule.registerContext(binder(), FourEyesRequestsContextProvider.class);
    }

}

class FourEyesRequestsContextProvider implements ContextInitInfoProvider {

    private IStructureModule structureModule;
    private ISnapshotModule snapshotModule;
    private IQueryModule queryModule;
    private IRealTimeModule realtimeModule;

    @Inject
    public FourEyesRequestsContextProvider(@FourEyes IStructureModule structureModule,
            @FourEyes ISnapshotModule snapshotModule, @FourEyes IQueryModule queryModule,
            @FourEyes IRealTimeModule realtimeModule) {
        this.structureModule = structureModule;
        this.snapshotModule = snapshotModule;
        this.queryModule = queryModule;
        this.realtimeModule = realtimeModule;
    }

    @Override
    public ContextInitInfo getContext() {
        ContextInitInfo context = new ContextInitInfo();

        context.setContextName("4EyesRequestForSubmitter").setContextObjectName("4EyesRequestForSubmitter");

        context.exportService("4EyesSubmitterNotification");

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