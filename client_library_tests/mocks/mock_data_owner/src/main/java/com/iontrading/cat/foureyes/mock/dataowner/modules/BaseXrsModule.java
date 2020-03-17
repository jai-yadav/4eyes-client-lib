package com.iontrading.cat.foureyes.mock.dataowner.modules;

import com.iontrading.xrs.api.IContext;
import com.iontrading.xrs.api.IModule;
import com.iontrading.xrs.api.IModuleServiceLocator;
import com.iontrading.xrs.api.events.IEventFactory;
import com.iontrading.xrs.api.events.IEventMgr;

public abstract class BaseXrsModule implements IModule {

    private final String name;

    protected IContext context;
    protected IEventFactory eventFactory;
    protected IEventMgr eventManager;

    public BaseXrsModule(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDetails() {
        return "";
    }

    @Override
    public void init(IContext context, IModuleServiceLocator locator) {
        this.context = context;
        this.eventFactory = locator.getEventFactory();
        this.eventManager = locator.getEvtManager();
    }

    @Override
    public void shutDown() {
    }

}
