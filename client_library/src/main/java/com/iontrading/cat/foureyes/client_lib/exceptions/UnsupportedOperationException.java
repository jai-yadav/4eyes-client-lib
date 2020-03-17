package com.iontrading.cat.foureyes.client_lib.exceptions;

import com.iontrading.proguard.annotation.KeepAll;

@KeepAll
public class UnsupportedOperationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2596664093692650665L;

	public UnsupportedOperationException(String errorMsg) {
		super(errorMsg);
	}
}
