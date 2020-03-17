package com.iontrading.cat.foureyes.mock.server.submitter;

import static com.iontrading.isf.workflow_engine.spi.builder.WorkflowModuleBuilderFlowElements.userTask;

import javax.inject.Named;
import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.iontrading.cat.foureyes.mock.server.FourEyesException;
import com.iontrading.cat.foureyes.mock.server.elements.FourEyesErrorHandler;
import com.iontrading.cat.foureyes.mock.server.elements.NotifyRequest;
import com.iontrading.cat.foureyes.mock.server.elements.SaveNewRequest;
import com.iontrading.cat.foureyes.mock.server.entities.FourEyesEntitiesModule;
import com.iontrading.cat.foureyes.mock.server.workflows.SubmitWorkflowContext;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.isf.workflow_engine.guice.WorkflowEngineModule;
import com.iontrading.isf.workflow_engine.impl.common.AbstractWorkflowModule;
import com.iontrading.isf.workflow_engine.spi.builder.IWorkflowModuleBuilder;
import com.iontrading.talk.api.guice.TalkModule;
import com.iontrading.talk.ionbus.guice.TalkIonBusModule;

@ModuleDescriptor(requires = { TalkIonBusModule.class, FourEyesEntitiesModule.class, WorkflowEngineModule.class })
public class FourEyesSubmitterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SubmitterFunctions.class).to(SubmitterFunctionsImpl.class).in(Singleton.class);
        TalkModule.exportFunctions(binder(), SubmitterFunctions.class);
    }

    @Named("4EyesSubmitWorkflow")
    @Singleton
    @Provides
    private Class<? extends AbstractWorkflowModule> getSubmitWorkflow(IWorkflowModuleBuilder builder) {
        Class<? extends AbstractWorkflowModule> workflow = builder.withContextType(SubmitWorkflowContext.class)
            .start("4EyesSubmit",
                    userTask(SaveNewRequest.class).userTask(NotifyRequest.class))
            .errorHandler(FourEyesException.class, FourEyesErrorHandler.class)
            .end();
        return workflow;

    }
}
