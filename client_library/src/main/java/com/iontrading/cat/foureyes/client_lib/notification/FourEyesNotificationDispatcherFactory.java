package com.iontrading.cat.foureyes.client_lib.notification;

import com.google.inject.assistedinject.Assisted;
import com.iontrading.cat.foureyes.client_lib.spi.FourEyesNotificationListener;

public interface FourEyesNotificationDispatcherFactory {

    FourEyesNotificationDispatcher create(FourEyesNotificationListener listener, @Assisted("Entity_Type") String entityName);
}
