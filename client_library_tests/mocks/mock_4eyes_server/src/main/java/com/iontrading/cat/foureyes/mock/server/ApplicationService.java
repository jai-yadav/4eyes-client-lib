/**
 * Copyright (c) 2014 ION Trading ltd.
 * All Rights reserved.
 *
 * This software is proprietary and confidential to ION Trading ltd.
 * and is protected by copyright law as an unpublished work.
 * Unauthorized access and disclosure strictly forbidden.
 */
package com.iontrading.cat.foureyes.mock.server;

import javax.inject.Inject;

import com.iontrading.cat.foureyes.mock.server.annotation.FourEyes;
import com.iontrading.isf.boot.spi.IService;
import com.iontrading.isf.service_manager.spi.IBusServiceManager;
import com.iontrading.isf.trace.ITracer;

public class ApplicationService implements IService {

    private ITracer tracer;
    private IBusServiceManager serviceManager;

    @Inject
    private ApplicationService(@FourEyes ITracer tracer, IBusServiceManager serviceManager) {
        this.tracer = tracer;
        this.serviceManager = serviceManager;
    }

    public String getName() {
        return "Applicationservice";
    }

    public void start() throws Exception {
        serviceManager.activateService(ApplicationModule.FOUR_EYES_SUBMITTER_SERVICE);
    }

    public void shutdown() {
        tracer.INFO().key("Service").action("Shutting down").end();
    }

}
