/**
 * Copyright (c) 2014 ION Trading ltd.
 * All Rights reserved.
 * 
 * This software is proprietary and confidential to ION Trading ltd.
 * and is protected by copyright law as an unpublished work.
 * Unauthorized access and disclosure strictly forbidden.
 */
package com.iontrading.cat.foureyes.mock.dataowner;

import com.iontrading.cat.foureyes.mock.dataowner.modules.ApplicationModule;
import com.iontrading.isf.applicationserver.spi.AS;
import com.iontrading.jmix.logging.LoggerEnvironment;
import com.iontrading.proguard.annotation.Keep;

/**
 * The good old application launcher.
 */
@Keep
public class Main {

    @Keep
    public static void main(String[] args) {
        System.setProperty("mkv.currency", "ANY");
        System.setProperty("mkv.source", "SIMPLE_DATA_OWNER");

        LoggerEnvironment.setCustomProperty("LOW.TraceThread", "false");

        AS.createLaunchConfiguration()
            .withArgs(args)
            .withModules(ApplicationModule.class)
            .withComponentInfo("SIMPLE_DATA_OWNER", "Simple DataOwner of 4Eyes protocol", "0.0.1", "na")
            .launch();
    }
}
