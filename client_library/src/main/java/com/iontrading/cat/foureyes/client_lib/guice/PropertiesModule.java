package com.iontrading.cat.foureyes.client_lib.guice;

import java.io.IOException;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.iontrading.cat.foureyes.client_lib.annotation.ProtocolVersion;

public class PropertiesModule extends AbstractModule {
	
	@Override
	protected void configure() {
	}
	
    @Provides
    @Singleton
    @ProtocolVersion
	public Integer getProtocolVersion() throws IOException {
        return new Integer(1);
	}

}
