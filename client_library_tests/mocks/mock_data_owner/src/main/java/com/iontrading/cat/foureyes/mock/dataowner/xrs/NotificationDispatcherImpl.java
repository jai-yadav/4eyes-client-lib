package com.iontrading.cat.foureyes.mock.dataowner.xrs;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.cat.foureyes.mock.dataowner.annotation.DataOwner;
import com.iontrading.isf.trace.ITracer;

public class NotificationDispatcherImpl implements NotificationDispatcher {

    private ITracer tracer;

    private List<FourEyesRequestListener> listeners = new LinkedList<FourEyesRequestListener>();

    @Inject
    public NotificationDispatcherImpl(@DataOwner ITracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void addListener(FourEyesRequestListener lner) {
        synchronized (listeners) {
            listeners.add(lner);
        }
    }

    @Override
    public void notifyAddOrUpdate(FourEyesRequest request) {
        synchronized (listeners) {
            tracer.INFO().key("Notification").action("Notify").token("Id", request.getRequestId()).end();
            for (FourEyesRequestListener lner : listeners) {
                lner.onUpdate(request);
            }
        }
    }

    @Override
    public void notifyDelete(FourEyesRequest request) {
        synchronized (listeners) {
            tracer.INFO().key("Notification").action("Notify").token("Id", request.getRequestId()).end();
            for (FourEyesRequestListener lner : listeners) {
                lner.onDelete(request);
            }
        }
    }
}
