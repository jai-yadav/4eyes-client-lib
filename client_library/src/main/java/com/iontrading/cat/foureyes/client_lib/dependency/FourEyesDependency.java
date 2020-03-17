package com.iontrading.cat.foureyes.client_lib.dependency;

import com.iontrading.isf.dependency_manager.api.AbstractDependency;
import com.iontrading.proguard.annotation.KeepAll;

/**
 * A dependency that can be used to monitor the status of the 4Eyes server and
 * it's availability.
 * <p>
 * The dependency is not available if
 * <ul>
 * <li>The 4Eyes server is not visible on the ION Platform
 * <li>The 4Eyes server is on the ION Platform but it's not operational
 * </ul>
 * </p>
 */
@KeepAll
public class FourEyesDependency extends AbstractDependency {

    public final static String ID = "FourEyesDependency";

    public FourEyesDependency() {
        super(ID);
    }

}
