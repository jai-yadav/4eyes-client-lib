package com.iontrading.cat.foureyes.mock.dataowner.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;

public class Storage {
    private Map<String, FourEyesRequest> allRequests;
    
    public Storage() {
         this.allRequests = new HashMap<>();
    }
    
    public void addOrUpdateRequest(FourEyesRequest request) {
        allRequests.put(request.getRequestId(), request);
    }

    public Collection<FourEyesRequest> getAll() {
        return allRequests.values();
    }

    public void clear() {
         allRequests.clear();
    }

	public FourEyesRequest getRequest(String requestId) {
		return allRequests.get(requestId);
	}
    
}
