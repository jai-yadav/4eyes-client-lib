package com.iontrading.cat.foureyes.mock.dataowner.xrs;

import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;

public interface FourEyesRequestListener {

    void onUpdate(FourEyesRequest request);
    
    void onDelete(FourEyesRequest request);
}
