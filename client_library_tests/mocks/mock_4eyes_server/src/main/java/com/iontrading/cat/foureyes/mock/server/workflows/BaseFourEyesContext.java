package com.iontrading.cat.foureyes.mock.server.workflows;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.isf.workflow_engine.spi.IWorkflowContext;

public abstract class BaseFourEyesContext implements IWorkflowContext {

    private String errorMessage;
    private String requestId;
    private FourEyesRequest request;

    protected BaseFourEyesContext(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public boolean isError() {
        return this.errorMessage != null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setRequest(FourEyesRequest request) {
        this.request = request;
    }

    public FourEyesRequest getRequest() {
        return request;
    }

    public abstract RequestStatus[] getValidStatuses();
}
