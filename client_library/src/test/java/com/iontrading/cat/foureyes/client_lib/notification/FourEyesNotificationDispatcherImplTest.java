package com.iontrading.cat.foureyes.client_lib.notification;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.iontrading.cat.foureyes.client_lib.enums.RequestStatus;
import com.iontrading.cat.foureyes.client_lib.guice.FourEyesNotificationModule;
import com.iontrading.cat.foureyes.client_lib.mock.MockConfigurationValueProvider;
import com.iontrading.cat.foureyes.client_lib.mock.MockFourEyesNotificationFunctions;
import com.iontrading.cat.foureyes.client_lib.mock.MockImporter;
import com.iontrading.cat.foureyes.client_lib.mock.MockMQSubscriber;
import com.iontrading.cat.foureyes.client_lib.mock.MockProvider;
import com.iontrading.cat.foureyes.client_lib.mock.MockRequestNotificationListener;
import com.iontrading.cat.foureyes.client_lib.mock.MockSequentialExecutorService;
import com.iontrading.isf.dependency_manager.support.MockDependencyChangeEvent;
import com.iontrading.isf.dependency_manager.support.MockDependencyManager;
import com.iontrading.isf.dependency_manager.support.MockDependencySnapshot;
import com.iontrading.isf.executors.spi.ISequentialExecutorService;
import com.iontrading.isf.log.support.MockITracer;
import com.iontrading.isf.service_manager.support.MockIBusService;
import com.iontrading.talk.ionbus.mqsubscriber.spi.MQSubscription;
import com.iontrading.talk.ionbus.mqsubscriber.spi.event.MQSubscriptionDataEvent;
import com.iontrading.talk.ionbus.mqsubscriber.spi.event.MQSubscriptionErrorEvent;
import com.iontrading.talk.ionbus.mqsubscriber.spi.event.MQSubscriptionEvent;
import com.iontrading.talk.ionbus.mqsubscriber.spi.event.MQSubscriptionMessageEvent;
import com.iontrading.talk.ionbus.mqsubscriber.spi.listener.MQListenerAdapter;
import com.iontrading.xrs.support.MockXRSContextService;

public class FourEyesNotificationDispatcherImplTest {

    private FourEyesNotificationDispatcherImpl dispatcher;
    private MockRequestNotificationListener listener;
    private MockMQSubscriber<FourEyesRequestImpl> subscriber;
    private MockSequentialExecutorService executor;
    private MockConfigurationValueProvider configurationProvider;
    private MockFourEyesNotificationFunctions notificationFunctions;
    private MockDependencyManager dependencyManager;
    private MockDependencySnapshot dependencySnapshot;
    private MockDependencyChangeEvent dependencyEvent;
    private MockIBusService busService;
    private MockXRSContextService service;

    @Before
    public void setUp() {
        listener = new MockRequestNotificationListener();

        MockProvider<ISequentialExecutorService> executorsProvider = new MockProvider<>();
        executor = new MockSequentialExecutorService();
        executorsProvider.setExpectedGet(executor);
        
        MockImporter importer = new MockImporter();
        notificationFunctions = new MockFourEyesNotificationFunctions();
        importer.setImportFunctionsResult(notificationFunctions);
        
        subscriber = new MockMQSubscriber<>();
        
        configurationProvider = new MockConfigurationValueProvider();
        configurationProvider.setExpectedProperty("DataOwnerSource");
        
        dependencyManager = new MockDependencyManager();
        
        busService = new MockIBusService(FourEyesNotificationModule.SUBMITER_NOTIFICATION_SERVICE,"source","currency");
        service = new MockXRSContextService();
        service.setId(FourEyesNotificationModule.SUBMITER_NOTIFICATION_SERVICE);
        service.setBusService(busService);
        dependencyEvent = new MockDependencyChangeEvent();
        dependencySnapshot = new MockDependencySnapshot(FourEyesNotificationModule.SUBMITER_NOTIFICATION_SERVICE);
        dependencySnapshot.setUserdata(service);
        
        dependencySnapshot.setAvailable(true);
        dependencyEvent.addDependencySnapshot(dependencySnapshot);
        dependencyEvent.setChanged(dependencySnapshot);
        
        dispatcher = new FourEyesNotificationDispatcherImpl(listener, "MockEntity", executorsProvider,
                new MockITracer(), importer, subscriber, 1, configurationProvider, dependencyManager);
        
        dispatcher.start();

    }
    
    @Test
    public void testDataOwnerSourceNotfound() {
        configurationProvider.setExpectedProperty(null);
        
        dispatcher.onChange(dependencyEvent);
        
        Assert.assertFalse(notificationFunctions.isSearchRequestsCalled());
        Assert.assertNotNull(dependencyManager.getObserver());
    } 
    
    @Test
    public void testFunctionNotCalledIfDependecyUnAvailable() {
        dependencySnapshot.setAvailable(false);
        dependencyEvent.addDependencySnapshot(dependencySnapshot);
        dependencyEvent.setChanged(dependencySnapshot);
        
        dispatcher.onChange(dependencyEvent);
        Assert.assertFalse(notificationFunctions.isSearchRequestsCalled());
        Assert.assertNotNull(dependencyManager.getObserver());
    }
    
    @Test
    public void testFunctionCallFails() {
        notificationFunctions.setSearchRequestExpectedReturn(false);
        dispatcher.onChange(dependencyEvent);
        Assert.assertTrue(notificationFunctions.isSearchRequestsCalled());
        Assert.assertNotNull(dependencyManager.getObserver());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testDispatchOnAdd() {
        dispatcher.onChange(dependencyEvent);
        MQListenerAdapter<FourEyesRequestImpl> mqListener = (MQListenerAdapter<FourEyesRequestImpl>) subscriber.getBuilder().getMQListener();
        mqListener.onAdd(new MQSubscriptionDataEvent<FourEyesRequestImpl>() {
            
            @Override
            public MQSubscription getSubscription() {
                return null;
            }
            
            @Override
            public String getRecordName() {
                return null;
            }
            
            @Override
            public FourEyesRequestImpl getData() {
                FourEyesRequestImpl request = new FourEyesRequestImpl();
                request.setStatus(RequestStatus.Pending);
                return request;
            }
        });
        executor.runTask();
        Assert.assertEquals(RequestStatus.Pending, listener.getRequestPending().getStatus());
        Assert.assertNull(dependencyManager.getObserver());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testDispatchOnUpdate() {
        dispatcher.onChange(dependencyEvent);
        MQListenerAdapter<FourEyesRequestImpl> mqListener = (MQListenerAdapter<FourEyesRequestImpl>) subscriber.getBuilder().getMQListener();
        mqListener.onUpdate(new MQSubscriptionDataEvent<FourEyesRequestImpl>() {
            
            @Override
            public MQSubscription getSubscription() {
                return null;
            }
            
            @Override
            public String getRecordName() {
                return null;
            }
            
            @Override
            public FourEyesRequestImpl getData() {
                FourEyesRequestImpl request = new FourEyesRequestImpl();
                request.setStatus(RequestStatus.Approved);
                return request;
            }
        });
        executor.runTask();
        Assert.assertEquals(RequestStatus.Approved, listener.getRequestApproved().getStatus());
        Assert.assertNull(dependencyManager.getObserver());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testDispatchOnErrorCancelSubscription() {
        dispatcher.onChange(dependencyEvent);
        MQListenerAdapter<FourEyesRequestImpl> mqListener = (MQListenerAdapter<FourEyesRequestImpl>) subscriber.getBuilder().getMQListener();
        mqListener.onError(new MQSubscriptionErrorEvent() {
            
            @Override
            public MQSubscription getSubscription() {
                return null;
            }
            
            @Override
            public Throwable getCause() {
                return new Throwable("Cancel Subscription");
            }
        });
        executor.runTask();
        Assert.assertEquals(true, listener.isOnDisconnedCalled());
        Assert.assertNotNull(dependencyManager.getObserver());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testDispatchOnUserActionDownloadStart() {
        dispatcher.onChange(dependencyEvent);
        MQListenerAdapter<FourEyesRequestImpl> mqListener = (MQListenerAdapter<FourEyesRequestImpl>) subscriber.getBuilder().getMQListener();
        mqListener.onUserAction(new MQSubscriptionMessageEvent() {
            
            @Override
            public MQSubscription getSubscription() {
                return null;
            }
            
            @Override
            public String getMessage() {
                return "14:DOWNLOAD_START";
            }
        });
        executor.runTask();
        Assert.assertEquals(true, listener.isResyncStartCalled());
        Assert.assertNull(dependencyManager.getObserver());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testDispatchOnUserActionDownloadEnd() {
        dispatcher.onChange(dependencyEvent);
        MQListenerAdapter<FourEyesRequestImpl> mqListener = (MQListenerAdapter<FourEyesRequestImpl>) subscriber.getBuilder().getMQListener();
        mqListener.onUserAction(new MQSubscriptionMessageEvent() {
            
            @Override
            public MQSubscription getSubscription() {
                return null;
            }
            
            @Override
            public String getMessage() {
                return "15:DOWNLOAD_END";
            }
        });
        executor.runTask();
        Assert.assertEquals(true, listener.isResyncEndCalled());
        Assert.assertNull(dependencyManager.getObserver());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testDispatchOnUserActionQueueStale() {
        dispatcher.onChange(dependencyEvent);
        MQListenerAdapter<FourEyesRequestImpl> mqListener = (MQListenerAdapter<FourEyesRequestImpl>) subscriber.getBuilder().getMQListener();
        mqListener.onUserAction(new MQSubscriptionMessageEvent() {
            
            @Override
            public MQSubscription getSubscription() {
                return null;
            }
            
            @Override
            public String getMessage() {
                return "2:Stale|Stale";
            }
        });
        executor.runTask();
        Assert.assertEquals(true, listener.isOnDisconnedCalled());
        Assert.assertNotNull(dependencyManager.getObserver());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testDispatchOnDisconnected() {
        dispatcher.onChange(dependencyEvent);
        MQListenerAdapter<FourEyesRequestImpl> mqListener = (MQListenerAdapter<FourEyesRequestImpl>) subscriber.getBuilder().getMQListener();
        mqListener.onDisconnected(new MQSubscriptionEvent() {
            
            @Override
            public MQSubscription getSubscription() {
                return null;
            }
        });
        executor.runTask();
        Assert.assertEquals(true, listener.isOnDisconnedCalled());
        Assert.assertNotNull(dependencyManager.getObserver());
    }

}
