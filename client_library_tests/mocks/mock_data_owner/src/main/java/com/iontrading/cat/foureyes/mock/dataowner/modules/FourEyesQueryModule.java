package com.iontrading.cat.foureyes.mock.dataowner.modules;

import javax.inject.Inject;

import com.iontrading.xrs.api.IModifiableQueryRequest;
import com.iontrading.xrs.api.IQueryModule;
import com.iontrading.xrs.api.ModuleStatus;
import com.iontrading.xrs.api.XRSGenericResult;
import com.iontrading.xrs.api.XRSStatus;

public class FourEyesQueryModule extends BaseXrsModule implements IQueryModule {

    @Inject
    public FourEyesQueryModule() {
        super("4EyesQuery");
    }

    @Override
    public ModuleStatus getModuleStatus() {
        return new ModuleStatus(XRSStatus.RUNNING);
    }

    @Override
    public XRSGenericResult validate(IModifiableQueryRequest request) {
    	return XRSGenericResult.ok();
    }

}
