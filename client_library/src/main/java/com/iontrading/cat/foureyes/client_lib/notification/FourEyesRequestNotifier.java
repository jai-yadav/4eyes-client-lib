package com.iontrading.cat.foureyes.client_lib.notification;

import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;

public interface FourEyesRequestNotifier {

    void notifyRequest(FourEyesRequest data);

    void notifyPending(FourEyesRequest request);

    void notifyApproved(FourEyesRequest request);

    void notifyRejected(FourEyesRequest request);
    
    void notifyResyncStart();

    void notifyResyncEnd();

    void notifyDisconnected();

}
