package com.iontrading.cat.foureyes.client_lib.enums;

import com.iontrading.proguard.annotation.KeepAll;

/**
 * Type of the field.
 * 
 */
@KeepAll
public enum FieldType {
	
	/**
	 * String Field Type
	 */
	STR,
	
	/**
	 *Integer Field Type 
	 */
	INT,
	
	/**
	 * Real/Float Field Type
	 */
	REAL,
	
	/**
	 *Date(ISO Format) Field Type 
	 */
	DATE,
	
	/**
	 *Time(ISO Format) Field Type 
	 */
	TIME,
	
	/**
	 * DateTime(ISO Format) Field Type
	 */
	DATETIME

}
