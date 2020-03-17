package com.iontrading.cat.foureyes.mock.server.elements;

import javax.inject.Inject;

import com.iontrading.cat.foureyes.mock.server.workflows.BaseFourEyesContext;
import com.iontrading.isf.workflow_engine.spi.elements.ErrorHandlerElement;
import com.iontrading.isf.workflow_engine.spi.elements.ICompletionToken;
import com.iontrading.isf.workflow_engine.spi.elements.WorkflowException;

public class FourEyesErrorHandler extends ErrorHandlerElement {

    private BaseFourEyesContext context;

    @Inject
    public FourEyesErrorHandler(BaseFourEyesContext context) {
        this.context = context;
    }

    @Override
    public void process(ICompletionToken token, Throwable e) throws WorkflowException {
        context.setErrorMessage("Request rejected. Id: " + context.getRequestId() + "; Reason: " + e.getMessage());
        token.done();
    }
}
