package com.iontrading.cat.foureyes.mock.dataowner.modules;

import javax.inject.Inject;

import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.cat.foureyes.mock.dataowner.annotation.DataOwner;
import com.iontrading.cat.foureyes.mock.dataowner.xrs.FourEyesRequestListener;
import com.iontrading.cat.foureyes.mock.dataowner.xrs.NotificationDispatcher;
import com.iontrading.cat.foureyes.mock.dataowner.xrs.XrsFourEyesRequest;
import com.iontrading.isf.trace.ITracer;
import com.iontrading.xrs.api.IContext;
import com.iontrading.xrs.api.IModuleServiceLocator;
import com.iontrading.xrs.api.IRealTimeModule;
import com.iontrading.xrs.api.ModuleStatus;
import com.iontrading.xrs.api.XRSStatus;
import com.iontrading.xrs.api.events.XRSEventException;

public class FourEyesRealtimeModule extends BaseXrsModule implements IRealTimeModule, FourEyesRequestListener {

    private NotificationDispatcher notificationDispatcher;
    private ITracer tracer;

    @Inject
    public FourEyesRealtimeModule(@DataOwner ITracer tracer, NotificationDispatcher notificationDispatcher) {
        super("4EyesRealtime");
        this.tracer = tracer;
        this.notificationDispatcher = notificationDispatcher;
    }

    @Override
    public void init(IContext context, IModuleServiceLocator locator) {
        super.init(context, locator);
        notificationDispatcher.addListener(this);
    }

    @Override
    public ModuleStatus getModuleStatus() {
        return new ModuleStatus(XRSStatus.RUNNING);
    }

    @Override
    public void onUpdate(FourEyesRequest request) {
        try {
            XrsFourEyesRequest object = new XrsFourEyesRequest(request);
            eventManager.pushEvent(eventFactory.createRealtimeObjectUpdateEvent(context, object));
        } catch (XRSEventException e) {
            tracer.ERROR()
                .key("RealtimeModule")
                .action("XRSEventException thrown during snapshot")
                .token("ExceptionMessage", e.getMessage())
                .throwable(e)
                .end();
        }
    }

    @Override
    public void onDelete(FourEyesRequest request) {
        try {
            XrsFourEyesRequest object = new XrsFourEyesRequest(request);
            eventManager.pushEvent(eventFactory.createRealtimeObjectDeleteEvent(context, object));
        } catch (XRSEventException e) {
            tracer.ERROR()
                .key("RealtimeModule")
                .action("XRSEventException thrown during deletion")
                .token("ExceptionMessage", e.getMessage())
                .throwable(e)
                .end();
        }
    }

}
