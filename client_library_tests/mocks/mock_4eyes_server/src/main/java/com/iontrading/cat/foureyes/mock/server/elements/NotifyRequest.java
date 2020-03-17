package com.iontrading.cat.foureyes.mock.server.elements;

import javax.inject.Inject;

import com.iontrading.cat.foureyes.client_lib.spi.FourEyesRequest;
import com.iontrading.cat.foureyes.mock.server.entities.NotificationDispatcher;
import com.iontrading.cat.foureyes.mock.server.workflows.BaseFourEyesContext;
import com.iontrading.isf.workflow_engine.spi.elements.ICompletionToken;
import com.iontrading.isf.workflow_engine.spi.elements.UserTaskElement;
import com.iontrading.isf.workflow_engine.spi.elements.WorkflowException;

public class NotifyRequest extends UserTaskElement {

    private BaseFourEyesContext context;
    private NotificationDispatcher notification;

    @Inject
    public NotifyRequest(BaseFourEyesContext context, NotificationDispatcher notification) {
        this.context = context;
        this.notification = notification;
    }

    @Override
    public void process(ICompletionToken token) throws WorkflowException {
        FourEyesRequest request = context.getRequest();
        try {
            notification.notifyAddOrUpdate(request);
            token.done();
        } catch (RuntimeException e) {
            token.doneWithError(e);
        }
    }
}
