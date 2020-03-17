package com.iontrading.cat.foureyes.mock.server.entities;

import java.util.Collection;

import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.isf.commons.async.AsyncResult;

public interface StorageManager {

    AsyncResult<FourEyesRequest> saveNewRequest(int protocolVersion, String id, String comment, String customFields,
            String details, String entitlementFeature, String entitlementNamespace, String entitlementResource,
            String entityCategory, String entityDescription, String entityName, String externalId, String requestedBy,
            String requestGroup, String requestNamespace, String requestReason, String requestReasonDescription,
            String entityType, String userData, String dataOwner);

    Collection<FourEyesRequest> getAll();

    AsyncResult<FourEyesRequest> updateRequest(FourEyesRequestImpl request);

    void deleteAll(NotificationDispatcher notifier);

    void approve(NotificationDispatcher notifier, String id, String comment);

    void reject(NotificationDispatcher notifier, String id, String comment);

    void done(NotificationDispatcher notifier, String requestId, String comment, boolean isAccepted);

	void cancel(NotificationDispatcher notifier, String id, String comment);

}
