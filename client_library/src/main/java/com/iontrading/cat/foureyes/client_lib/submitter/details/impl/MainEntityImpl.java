package com.iontrading.cat.foureyes.client_lib.submitter.details.impl;

import com.iontrading.cat.foureyes.client_lib.submitter.details.Entity;
import com.iontrading.cat.foureyes.client_lib.submitter.details.MainEntity;

public class MainEntityImpl implements MainEntity {

	private final String id;
	private final Entity entity;

	public MainEntityImpl(String id, Entity entity) {
		this.id = id;
		this.entity = entity;
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public Entity getEntity() {
		return entity;
	}

}
