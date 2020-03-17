package com.iontrading.cat.foureyes.client_lib.api;

import com.iontrading.cat.foureyes.client_lib.bus.FourEyesFunctionResult;
import com.iontrading.cat.foureyes.client_lib.exceptions.UnsupportedOperationException;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.cat.foureyes.client_lib.submitter.PendingRequestSubmissionBuilder;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.proguard.annotation.KeepAll;

/**
 *This provides all the API's to interact with server.
 */
@KeepAll
public interface FourEyesService {

    /**
     * Create a new pending Request.
     * 
     * @param entitlementFeature
     *            Feature used for the entitlement check of the approver
     * @param entitlementNamespace
     *            Namespace used for the entitlement check of the approver
     * @param entitlementResource
     *            Resource used for the entitlement check of the approver
     * @param entityCategory
     *            The type of the Entity
     * @param entityType
     *            The type of the request
     * @param entityDescription
     *            Entity description
     * @param entityName
     *            The entity ID or Name
     * @param requestedBy
     *            User who originates the change
     * @return Returns a builder with all the mandatory information and this can
     *         be enriched with additional info before submission
     */
    public PendingRequestSubmissionBuilder createPendingRequest(String entitlementFeature, String entitlementNamespace,
            String entitlementResource, String entityCategory, String entityType, String entityDescription, String entityName,
            String requestedBy);
    
    /**
     * Withdraw an existing pending request.
     * 
     * @param requestId
     * 			Id of the pending request.
     * @return
     * 			Returns the result received from four eyes server. 
     */
    public AsyncResult<FourEyesFunctionResult> withdrawPendingRequest(String requestId);
    
    /**
     * Withdraw an existing pending request.
     * 
     * @param requestId
     * 			Id of the pending request.
     * @param comment
     * 			Optional comment.
     * @return
     * 			Returns the result received from four eyes server. 
     */
    public AsyncResult<FourEyesFunctionResult> withdrawPendingRequest(String requestId, String comment);
    
    /**
     * Notify that Approved/Rejected request has been processed.
     * 
     * @param request
     * 			Request that has been approved or rejected.
     * @return
     * 			Returns the result received from four eyes server. 
     */
    public AsyncResult<FourEyesFunctionResult> acknowledgeUpdate(FourEyesRequest request);

    /**
     * Notify that Approved/Rejected request has been processed.
     * 
     * @param request
     * 			Request that has been approved or rejected.
     * @param comment
     * 			Optional comment.
     * @return
     * 			Returns the result received from four eyes server. 
     */
    public AsyncResult<FourEyesFunctionResult> acknowledgeUpdate(FourEyesRequest request, String comment);

    /**
     * Notify that Approved request has been denied.
     * 
     * @param request
     * 			Request that has been approved.
     * @return
     * 			Returns the result received from four eyes server. 
     * @throws UnsupportedOperationException
     * 			Exception is thrown if request status is other than Approved.
     */
    public AsyncResult<FourEyesFunctionResult> denyUpdate(FourEyesRequest request) throws UnsupportedOperationException;
    
    /**
     * Notify that Approved request has been denied.
     * 
     * @param request
     * 			Request that has been approved.
     * @param comment
     * 			Optional comment.
     * @return
     * 			Returns the result received from four eyes server. 
     * @throws UnsupportedOperationException
     * 			Exception is thrown if request status is other than Approved.
     */
    public AsyncResult<FourEyesFunctionResult> denyUpdate(FourEyesRequest request, String comment) throws UnsupportedOperationException;

}
