/**
 * Copyright (c) 2014 ION Trading ltd.
 * All Rights reserved.
 *
 * This software is proprietary and confidential to ION Trading ltd.
 * and is protected by copyright law as an unpublished work.
 * Unauthorized access and disclosure strictly forbidden.
 */
package com.iontrading.cat.foureyes.mock.dataowner.modules;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.iontrading.cat.foureyes.client_lib.api.FourEyesService;
import com.iontrading.cat.foureyes.client_lib.guice.FourEyesModule;
import com.iontrading.cat.foureyes.mock.dataowner.DummyDataOwnerFunctions;
import com.iontrading.cat.foureyes.mock.dataowner.FourEyesStatusMonitor;
import com.iontrading.cat.foureyes.mock.dataowner.annotation.DataOwner;
import com.iontrading.cat.foureyes.mock.dataowner.requestListener.MockRequestNotificationListener;
import com.iontrading.cat.foureyes.mock.dataowner.storage.Storage;
import com.iontrading.isf.boot.guice.BootModule;
import com.iontrading.isf.boot.spi.IBootService.RunPhase;
import com.iontrading.isf.configuration.guice.ConfigurationCoreModule;
import com.iontrading.isf.executors.spi.ISequentialExecutorService;
import com.iontrading.isf.executors.spi.ISequentialExecutorServiceFactory;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.isf.service_manager.guice.ServiceManagerModule;
import com.iontrading.isf.trace.ITracer;
import com.iontrading.isf.trace.ITracerFactory;
import com.iontrading.isf.trace.guice.TracerModule;
import com.iontrading.talk.api.guice.TalkModule;
import com.iontrading.talk.ionbus.guice.TalkIonBusModule;

/**
 * The requirement in the {@link FourEyesModule} defines all the required
 * bindings. In particular it makes it possible to inject an instance of
 * {@link FourEyesService}
 */
@ModuleDescriptor(requires = { BootModule.class, TracerModule.class, ConfigurationCoreModule.class,
		FourEyesModule.class, TalkIonBusModule.class, ServiceManagerModule.class, FourEyesXrsModule.class })
public class ApplicationModule extends AbstractModule {

	public final static String DATAOWNER_SERVICE_ID = "DataOwnerService";

	@Override
	protected void configure() {
		// The DataOwner manages the persistent storage of the entities.
		// To manage the 4Eyes requests, the application may also need to store
		// additional info.
		bind(Storage.class).in(Singleton.class);

		// Application functions. They submit new 4E requests to the server and
		// reply accordingly
		TalkModule.exportFunctions(binder(), DummyDataOwnerFunctions.class);

		// Manage the application status according to the 4E service
		// availability
		ServiceManagerModule.addService(binder(), DATAOWNER_SERVICE_ID);
		BootModule.registerBootService(binder(), FourEyesStatusMonitor.class, RunPhase.RUNNING);

		FourEyesModule.registerNotificationListener(binder(), MockRequestNotificationListener.class);
	}

	@Provides
	@DataOwner
	public ITracer getTracer(ITracerFactory logFactory) {
		return logFactory.createTracer("DataOwner", "N/A");
	}

	@Provides
	@DataOwner
	public ISequentialExecutorService getSequentialExecutorService(ISequentialExecutorServiceFactory factory) {
		return factory.create("DataOwner");
	}
}
