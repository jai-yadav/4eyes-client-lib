
package com.iontrading.cat.foureyes.client_lib.spi;

import com.iontrading.cat.foureyes.client_lib.api.FourEyesService;
import com.iontrading.proguard.annotation.KeepAll;

/**
 * The listener that receives and process the notification flow about the
 * {@link FourEyesRequest}s.
 * <p>
 * The notification flow allows the listener to have an eventually consistent
 * view of the 4Eyes server status. The notifications received by the listener
 * can therefore be used by the application to keep the internal status aligned
 * with the 4Eyes server status.
 * </p>
 * <p>
 * On component startup, and after each disconnection, the listener receives a
 * series of notifications that describe the current status of the 4Eyes server.
 * These notifications can be use to re-synchronize the state of the DataOwner
 * with the current state of the 4Eyes server. <br>
 * This phase is delimited by {@link #onResyncStart()} and
 * {@link #onResyncEnd()} notifications.<br>
 * During the re-synchronization, the application must be prepared to re-process
 * notifications which might have been already processed in the past (and yet
 * not successfully acknowledged to the server due to disconnections). In other
 * words, the implementation of {@link #onRequestPending(FourEyesRequest)},
 * {@link #onRequestApproved(FourEyesRequest)} and
 * {@link #onRequestRejected(FourEyesRequest)} must be idempotent.
 * </p>
 * <p>
 * All the methods of the listener are invoked sequentially, not necessarily by
 * the same thread.
 * </p>
 */
@KeepAll
public interface FourEyesNotificationListener {

    /**
     * The re-synchronization phase begins.
     */
    void onResyncStart();

    /**
     * The re-synchronization phase is completed.
     */
    void onResyncEnd();

    /**
     * The notification channel is currently broken. When the communication is
     * re-established, a new re-synchronization phase happens
     * 
     * @see {@link #onResyncStart()} and {@link #onResyncEnd()}
     */
    void onDisconnected();

    /**
     * Process a pending request.
     * <p>
     * During the re-synchronization phase, the application can use the notified
     * pending requests to make sure that its status and the 4Eyes server status
     * are aligned.
     * </p>
     * <p>
     * The application, based on its own logic, can decide to cancel a pending
     * request using the {@link FourEyesService#cancelRequest(FourEyesRequest)}
     * method.
     * </p>
     * 
     * @param request
     *            The request to process
     */
    void onRequestPending(FourEyesRequest request);

    /**
     * Process an approved request.
     * <p>
     * After a request has been approved, the application can apply or discard
     * the changes. When the processing is completed, the application is
     * expected to notify the 4Eyes server using either the
     * {@link FourEyesService#acknowledgeNotification(FourEyesRequest)} or the
     * {@link FourEyesService#rejectNotification(FourEyesRequest)} methods
     * respectively.
     * </p>
     * 
     * @param request
     *            The request to process
     */
    void onRequestApproved(FourEyesRequest request);

    /**
     * Process a rejected request.
     * <p>
     * After a request has been rejected, the application is expected to
     * acknowledge the the 4Eyes server using the
     * {@link FourEyesService#acknowledgeNotification(FourEyesRequest)} method.
     * </p>
     * 
     * @param request
     *            The request to process
     */
    void onRequestRejected(FourEyesRequest request);
    
    /**
     * Get the name of entity to which listener belongs
     * @return Entity Name 
     */
    String getEntityType();

}
