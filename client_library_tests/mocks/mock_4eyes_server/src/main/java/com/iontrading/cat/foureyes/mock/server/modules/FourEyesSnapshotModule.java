package com.iontrading.cat.foureyes.mock.server.modules;

import java.util.Collection;

import javax.inject.Inject;

import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.cat.foureyes.mock.server.annotation.FourEyes;
import com.iontrading.cat.foureyes.mock.server.entities.StorageManager;
import com.iontrading.cat.foureyes.mock.server.xrs.XrsFourEyesRequest;
import com.iontrading.isf.trace.ITracer;
import com.iontrading.xrs.api.IQuery;
import com.iontrading.xrs.api.ISnapshotModule;
import com.iontrading.xrs.api.ModuleStatus;
import com.iontrading.xrs.api.XRSGenericResult;
import com.iontrading.xrs.api.XRSStatus;
import com.iontrading.xrs.api.events.XRSEventException;
import com.iontrading.xrs.api.helper.SnapshotModuleHelper;

public class FourEyesSnapshotModule extends BaseXrsModule implements ISnapshotModule {

    private SnapshotModuleHelper helper;
    private StorageManager storage;
    private ITracer tracer;

    @Inject
    public FourEyesSnapshotModule(@FourEyes ITracer tracer, SnapshotModuleHelper helper, StorageManager storage) {
        super("4EyesSnapshot");
        this.tracer = tracer;
        this.helper = helper;
        this.storage = storage;
    }

    @Override
    public ModuleStatus getModuleStatus() {
        return new ModuleStatus(XRSStatus.RUNNING);
    }

    @Override
    public XRSGenericResult prepareQuery(IQuery query) {
        return XRSGenericResult.ok();
    }

    @Override
    public void onQueryReadyForSnapshot(IQuery query) {
        helper.onQueryReadyForSnapshot(query);

        // TODO we need a proper implementation here
        Collection<FourEyesRequest> requests = storage.getAll();

        try {
            eventManager.pushEvent(eventFactory.createSnapshotStartEvent(context, query));
            for (FourEyesRequest req : requests) {
                if (helper.canPlayQuery(query)) {
                    XrsFourEyesRequest xrsReq = new XrsFourEyesRequest(req);
                    eventManager.pushEvent(eventFactory.createSnapshotObjectEvent(context, query, xrsReq));
                }
            }
            eventManager.pushEvent(eventFactory.createSnapshotEndEvent(context, query));
        } catch (XRSEventException e) {
            tracer.WARN()
                .key("SnapshotModule")
                .action("XRSEventException thrown during snapshot")
                .token("QueryId", query.getId())
                .token("ExceptionMessage", e.getMessage())
                .end();
        } catch (InterruptedException e) {
            tracer.ERROR()
                .key("SnapshotModule")
                .action("InterruptedException thrown during snapshot")
                .token("QueryId", query.getId())
                .token("ExceptionMessage", e.getMessage())
                .throwable(e)
                .end();
        }
    }

    @Override
    public void pauseQuery(IQuery query) {
        helper.pauseQuery(query);
    }

    @Override
    public void resumeQuery(IQuery query) {
        helper.resumeQuery(query);
    }

    @Override
    public void closeQuery(IQuery query) {
        helper.closeQuery(query);
    }

}
