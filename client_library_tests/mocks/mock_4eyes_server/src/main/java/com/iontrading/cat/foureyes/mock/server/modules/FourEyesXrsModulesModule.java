package com.iontrading.cat.foureyes.mock.server.modules;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.iontrading.cat.foureyes.mock.server.annotation.FourEyes;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.xrs.api.IQueryModule;
import com.iontrading.xrs.api.IRealTimeModule;
import com.iontrading.xrs.api.ISnapshotModule;
import com.iontrading.xrs.api.IStructureModule;

@ModuleDescriptor
public class FourEyesXrsModulesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IStructureModule.class).annotatedWith(FourEyes.class).to(FourEyesStructureModule.class).in(
                Singleton.class);
        bind(ISnapshotModule.class).annotatedWith(FourEyes.class).to(FourEyesSnapshotModule.class).in(Singleton.class);
        bind(IQueryModule.class).annotatedWith(FourEyes.class).to(FourEyesQueryModule.class).in(Singleton.class);
        bind(IRealTimeModule.class).annotatedWith(FourEyes.class).to(FourEyesRealtimeModule.class).in(Singleton.class);
    }

}
