package com.iontrading.cat.foureyes.mock.dataowner.modules;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.iontrading.cat.foureyes.mock.dataowner.annotation.DataOwner;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.xrs.api.IQueryModule;
import com.iontrading.xrs.api.IRealTimeModule;
import com.iontrading.xrs.api.ISnapshotModule;
import com.iontrading.xrs.api.IStructureModule;

@ModuleDescriptor
public class FourEyesXrsModulesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IStructureModule.class).annotatedWith(DataOwner.class).to(FourEyesStructureModule.class).in(
                Singleton.class);
        bind(ISnapshotModule.class).annotatedWith(DataOwner.class).to(FourEyesSnapshotModule.class).in(Singleton.class);
        bind(IQueryModule.class).annotatedWith(DataOwner.class).to(FourEyesQueryModule.class).in(Singleton.class);
        bind(IRealTimeModule.class).annotatedWith(DataOwner.class).to(FourEyesRealtimeModule.class).in(Singleton.class);
        
    }

}
