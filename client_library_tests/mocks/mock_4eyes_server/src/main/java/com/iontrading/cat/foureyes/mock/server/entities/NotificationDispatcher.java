package com.iontrading.cat.foureyes.mock.server.entities;

import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;

public interface NotificationDispatcher {

    void notifyAddOrUpdate(FourEyesRequest request);

    void addListener(FourEyesRequestListener lner);

    void notifyDelete(FourEyesRequest request);

}