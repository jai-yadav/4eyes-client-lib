/**
 * Copyright (c) 2014 ION Trading ltd.
 * All Rights reserved.
 * 
 * This software is proprietary and confidential to ION Trading ltd.
 * and is protected by copyright law as an unpublished work.
 * Unauthorized access and disclosure strictly forbidden.
 */
package com.iontrading.cat.foureyes.mock.server;

import com.iontrading.isf.applicationserver.spi.AS;
import com.iontrading.jmix.logging.LoggerEnvironment;
import com.iontrading.proguard.annotation.Keep;

/**
 * Application launcher.
 */
@Keep
public class Main {

    @Keep
    public static void main(String[] args) {
        System.setProperty("mkv.currency", "ANY");
        System.setProperty("mkv.source", "ION20_COMPONENT");

        LoggerEnvironment.setCustomProperty("LOW.TraceThread", "false");

        AS.createLaunchConfiguration()
            .withArgs(args)
            .withModules(ApplicationModule.class)
            .withComponentInfo("Dummy4Eyes", "Dummy 4-eyes component", "0.0.1", "na")
            .launch();
    }
}
