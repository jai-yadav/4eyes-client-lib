# Four Eyes Client Library

This repository contains the client library side implementation of Four Eyes Approval Protocol. Using this a Data Owner component can submit the pending requests and upon Approval/Rejection of these requests Data Owner will be notified to process them.

## Using this library

To integrate with Four Eyes Client Library, Data Owner have to include FourEyesModule. This will make FourEyesService available to Data Owner which will expose the required API's to interact with Four Eyes Server.

###### Integrating using FourEyesModule

```java
@ModuleDescriptor(requires = { FourEyesModule.class, TalkIonBusModule.class})
public class ApplicationModule extends AbstractModule {
    @Override
	protected void configure() {
    }
}
```



## APIs Exposed

Following APIs are provided by FourEyesService:

##### 1. Submitting A Pending Request

​	To submit a pending request **createPendingRequest** is provided. This API will expect all the mandatory parameters and will return a Pending Request Submission Builder. This builder can be enriched with additional non mandatory parameter. Calling **submit** will send the request to Four Eyes Server.



###### **Adding Custom Field to request:**

​	Pending request can be enriched to include a custom field that can be displayed by UI as separate column. To add a custom field enrich the Pending Request Submission Builder using **withCustomField**. 

​	Adding a custom fields requires *Name* of the field and it's *Value* as mandatory params. By default type of value is **FieldType.STR** and it could be of type STR, INT, REAL, DATE, TIME and DATETIME. While adding a custom field it's *type*, *display name* (If other than name) and a *description* can also be added.

```java
public class PendingRequest {
    private FourEyesService foureEyesService;
    private CustomFieldListBuilder cfBuilder;
    
    @Inject
    public PendingRequest(FourEyesService foureEyesService, CustomFieldListBuilder cfBuilder){
        this.fourEyesService = foureEyesService;
        this.cfbuilder = cfBuilder;
    }
    
    public AsyncResult<FourEyesFunctionResult> submitPendingRequest(){
    	//With Mandatory Params
        PendingRequestSubmissionBuilder submissionBuilder =
            fourEyesService.createPendingRequest(entitlementFeature,
                                                 entitlementNamespace,
                                                 entitlementResource,
                                                 entityCategory,
                                                 entityType,
                                                 entityDescription,
                                                 entityName,
                                                 requestedBy);
        
        //Enriching with Optional Params
        submissionBuilder
            .withComment(comment)
            .withDetails(details)
            .withExternalId(externalId)
            .withRequestGroup(requestGroup)
            .withRequestNamespace(requestNamespace)
            .withRequestReason(requestReason)
            .withRequestReasonDescription(requestReasonDescription)
            .withUserData(userData);
        
        //Adding Custom field to request
        cfBuilder.createCustomField("c1", "1").withDescription("c1_des").withDisplayName("c1_dn").withType(FieldType.INT)
				.add()
            .createCustomField("c2", "v2").withDescription("c2_des").withDisplayName("c2_dn")
				.withType(FieldType.STR).add();
        
        submissionBuilder
        .withCustomFields(cfBuilder.build());
        
        //Submitting to server
        retrun submissionBuilder.submit();
        
    }
}
```



###### Generating Details JSON as per UI protocol:

The Client Library exposes some facility classes to help generating the content of the Details field. The JSON content these classes generate is compliant with the specification described in [4Eyes Details Field JSON Format](https://git.iontrading.com/cat-core/4eyes-server/blob/develop/docs/Four-Eyes%20Details%20Field%20JSON%20Format.md), and it is compatible with the syntax recognized by the UI. 

```java
public class DetailsGenerator {

	private final DetailsBuilder detailsBuilder;
    private final EntityFactory entityFactory;
    private final ValueListFactory valueListFactory;
    private final PropertiesFactory propertiesFactory;

    @Inject
	public DetailsGenerator(DetailsBuilder detailsBuilder
                            ,  EntityFactory entityFactory
                            ,  ValueListFactory valueListFactory
                            ,  PropertiesFactory propertiesFactory) {
        this.detailsBuilder = detailsBuilder;
        this.entityFactory = entityFactory;
        this.valueListFactory = valueListFactory;
        this.propertiesFactory = propertiesFactory;
	}

	public void generate() throws JsonProcessingException, ValidationException {
		Entity oldEntity = entityFactory.create()
                .field("Id", "T1234")
                .field("Type", "Bond")
            	.field("IsCallable", true)
            	.field("Callable", entityFactory.create()
                       .field("CallDate", 20190514, propertiesFactory.create().ofType(ValueType.ION_DATE))
                       .field("CallStyle", "European"));
        
        Entity newEntity = entityFactory.create()
                .field("Id", "T1234")
                .field("Type", "Bond")
            	.field("IsCallable", false);
            	    
        detailsBuilder.withEntity("Old", oldEntity)
                      .withEntity("New", newEntity);
                        
		String json = detailsBuilder.build();
	}
}
```



##### 2. Withdrawing a Pending Request

​	To withdraw an old pending request FourEyesService exposes **withdrawPendingRequest** API. This requires ID of the request to be withdrawn as a mandatory parameter and an additional comment can also be added.

```java
public class WithdrawRequest {
    private FourEyesService foureEyesService;
    
    @Inject
    public WithdrawRequest(FourEyesService foureEyesService){
        this.fourEyesService = foureEyesService;
    }
    
    public AsyncResult<FourEyesFunctionResult> withDrawOldRequest(){
        return foureEyesService.withdrawPendingRequest(requestId, comment);
    }
}
```



##### 3. Approval / Rejection Processed

​	After processing Approval / Rejection notification, Data Owner can use the API **acknowledgeUpdate** to acknowledge the updates received. This API also requires request to be approved as an mandatory parameter and an additional comment can be added.

```java
public class AckUpdate {
    private FourEyesService foureEyesService;
    
    @Inject
    public AckUpdate(FourEyesService foureEyesService){
        this.fourEyesService = foureEyesService;
    }
    
    public AsyncResult<FourEyesFunctionResult> acknowledge(){
        return foureEyesService.acknowledgeUpdate(request, comment);
    }
}
```



##### 4. Deny Approval

​	In case Data owner is not able to process the approval **denyUpdate** API is to be used to send negative acknowledgement to server.This also requires request as a mandatory parameter and an additional comment could also be added.

*Note: Rejections can't be denied.*

```java
public class DenyUpdate {
    private FourEyesService foureEyesService;
    
    @Inject
    public DenyUpdate(FourEyesService foureEyesService){
        this.fourEyesService = foureEyesService;
    }
    
    public AsyncResult<FourEyesFunctionResult> acknowledge(){
        return foureEyesService.denyUpdate(request, comment);
    }
}
```



#### Result Response

​	For all the API's exposed by Four Eyes Service, response result will be wrapped into **FourEyesFunctionResult**. This result response will contain:

- Code (Int)
- Response (JSON String with detailed message)
- Request Id (ID of request created - Only for submit API)



## Adding Dependency

Client library provides support to add a dependency to Submitter Service of server. Data Owner can monitor this services by adding **FourEyesDependency** interest.

```java
public class FourEyesStatusMonitor implements IService, DependencyObserver {
    
    private DependencyManager depManager;
    private IBusServiceManager serviceManager;

    @Inject
    public FourEyesStatusMonitor(DependencyManager depManager,
            IBusServiceManager serviceManager) {
        this.depManager = depManager;
        this.serviceManager = serviceManager;
    }

    @Override
    public String getName() {
        return "FourEyesStatusMonitor";
    }

    @Override
    public void start() throws Exception {
        depManager.addInterest(this, new FourEyesDependency());
    }

    @Override
    public void shutdown() {
        depManager.removeInterest(this);
    }

    @Override
    public void onChange(DependencyChangeEvent evt) {
        DependencySnapshot<FourEyesSubmitterDependency> dep = evt.getDependencySnapshot(
            FourEyesSubmitterDependency.ID,
            FourEyesSubmitterDependency.class);
        
        if (dep.isAvailable()) {
            IBusService service = dep.getUserdata(IBusService.class);
            serviceManager.activateService(ApplicationModule.DATAOWNER_SERVICE_ID);
        } else {
            serviceManager.deactivateService(ApplicationModule.DATAOWNER_SERVICE_ID);
        }
    }

}
```



## Attaching Notification Listener

Data Owner can add a listener for different entities which will receive notification whenever a new request is added or if request gets approved or rejected. Listeners can be created by implementing **FourEyesNotificationListener** and can be added for listening using **registerNotificationListener** API in **FourEyesModule**. This listener should supply an unique entity name to distinguish between different entities.

```java
public class MockRequestNotificationListener implements FourEyesNotificationListener {

    private final ITracer tracer;
    
	public MockRequestNotificationListener(ITracer tracer) {
        this.tracer = tracer;
	}

	@Override
	public void onResyncStart() {
		tracer.INFO().key(LOG_KEY).action("Download Started").end();
	}

	@Override
	public void onResyncEnd() {
		tracer.INFO().key(LOG_KEY).action("Download Ended").end();
	}

	@Override
	public void onDisconnected() {
		tracer.INFO().key(LOG_KEY).action("Disconnected").end();
	}

	@Override
	public void onRequestPending(FourEyesRequest request) {
		tracer.INFO().key(LOG_KEY).action("Notification recieved for pending request")
				.token("RequestId", request.getRequestId()).end();
	}

	@Override
	public void onRequestApproved(FourEyesRequest request) {
		tracer.INFO().key(LOG_KEY).action("Notification recieved for approved request")
				.token("RequestId", request.getRequestId()).end();
	}

	@Override
	public void onRequestRejected(FourEyesRequest request) {
		tracer.INFO().key(LOG_KEY).action("Notification recieved for rejected request")
				.token("RequestId", request.getRequestId()).end();
	}

	@Override
	public String getEntityName() {
		return "Unique Entity Name";
	}

}

```

```java
@ModuleDescriptor(requires = {FourEyesModule.class})
public class ApplicationModule extends AbstractModule {

	@Override
	protected void configure() {
		FourEyesModule.registerNotificationListener(binder(), MockRequestNotificationListener.class);
	}
}
```

