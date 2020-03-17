package com.iontrading.cat.foureyes.mock.server.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.cat.foureyes.mock.server.FourEyesException;
import com.iontrading.cat.foureyes.mock.server.annotation.FourEyes;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.commons.async.AsyncResultPromise;
import com.iontrading.isf.commons.async.AsyncResults;
import com.iontrading.isf.trace.ITracer;

public class StorageManagerImpl implements StorageManager {

    private Map<String, FourEyesRequestImpl> db = new HashMap<String, FourEyesRequestImpl>();
    private Set<FourEyesRequest> archive = new HashSet<>();
    private ITracer tracer;

    @Inject
    public StorageManagerImpl(@FourEyes ITracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public AsyncResult<FourEyesRequest> saveNewRequest(int protocolVersion, String id, String comment,
            String customFields, String details, String entitlementFeature, String entitlementNamespace,
            String entitlementResource, String entityCategory, String entityDescription, String entityName,
            String externalId, String requestedBy, String requestGroup, String requestNamespace, String requestReason,
            String requestReasonDescription, String entityType, String userData, String dataOwner) {
        AsyncResultPromise<FourEyesRequest> result = AsyncResults.create();

        if (db.containsKey(id)) {
            result.failure(new FourEyesException("Invalid request id. Aleady in use: " + id));
        } else {
            FourEyesRequestImpl request = new FourEyesRequestImpl(protocolVersion, id, comment, customFields, details,
                    entitlementFeature, entitlementNamespace, entitlementResource, entityCategory, entityDescription,
                    entityName, externalId, requestedBy, requestGroup, requestNamespace, requestReason,
                    requestReasonDescription, entityType, userData, dataOwner);

            tracer.INFO().key("Storage").action("Save Request").token("Id", id).end();

            db.put(id, request);
            result.success(request);
        }

        return result;
    }

    @Override
    public AsyncResult<FourEyesRequest> updateRequest(FourEyesRequestImpl request) {
        AsyncResultPromise<FourEyesRequest> result = AsyncResults.create();

        if (request.getStatus() == RequestStatus.Done) {
            db.remove(request.getRequestId());
            archive.add(request);
        } else {
            db.put(request.getRequestId(), request);
        }
        result.success(request);

        return result;
    }

    @Override
    public Collection<FourEyesRequest> getAll() {
        List<FourEyesRequest> all = new ArrayList<>(db.size());

        for (FourEyesRequest el : Collections.unmodifiableCollection(db.values())) {
            all.add(el);
        }

        return all;
    }

    @Override
    public void deleteAll(NotificationDispatcher notifier) {
        for (FourEyesRequest request : db.values()) {
            notifier.notifyAddOrUpdate(request);
        }
        db.clear();
    }

    @Override
    public void approve(NotificationDispatcher notifier, String id, String comment) {
        FourEyesRequestImpl request = db.get(id);
        request.setPreviousStatus(request.getStatus());
        request.setStatus(RequestStatus.Approved);
        request.setActionedBy("MockApprover");
        request.setActionComment(comment);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        request.setActionTime(dateFormat.format(new Date()));
        notifier.notifyAddOrUpdate(request);
    }

    @Override
    public void reject(NotificationDispatcher notifier, String id, String comment) {
        FourEyesRequestImpl request = db.get(id);
        request.setStatus(RequestStatus.Rejected);        
        request.setActionedBy("MockRejector");
        request.setActionComment(comment);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        request.setActionTime(dateFormat.format(new Date()));
        notifier.notifyAddOrUpdate(request);
    }

    @Override
    public void done(NotificationDispatcher notifier, String requestId, String comment, boolean isAccepted) {
    	FourEyesRequestImpl request = db.get(requestId);
        request.setPreviousStatus(request.getStatus());
        request.setStatus(RequestStatus.Done);
        request.setActionComment(comment);
        if(isAccepted) {
        	request.setAppliedAfterApproval(1);
        } else {
        	request.setAppliedAfterApproval(0);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        request.setCompletionTime(dateFormat.format(new Date()));
        notifier.notifyAddOrUpdate(request);
    }

	@Override
	public void cancel(NotificationDispatcher notifier, String id, String comment) {
		this.done(notifier, id, comment, false);
	}

}
