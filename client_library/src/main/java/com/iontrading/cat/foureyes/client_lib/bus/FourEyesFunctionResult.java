package com.iontrading.cat.foureyes.client_lib.bus;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iontrading.proguard.annotation.KeepAll;
import com.iontrading.talk.api.annotation.TalkProperty;
import com.iontrading.talk.api.annotation.TalkType;

/**
 *Format of result response received from server.
 */
@KeepAll
@TalkType(propertyOrder = { "Code", "Response"})
public class FourEyesFunctionResult{
	
	@TalkProperty(name="Code")
	private int code;
	
	@TalkProperty(name="Response")
	private String response;
	
	public FourEyesFunctionResult() {
	}
	
	public FourEyesFunctionResult(int code, String response) {
		this.code = code;
		this.response = response;
	}
	
	public void setCode(int code) {
		this.code = code;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * @return
	 * 	Integer Result Code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return
	 * 	String Response message
	 */
	public String getResponse() {
		return response;
	}
	
	/**
	 * If Response from server contains Request Id then it returns it.
	 * @return
	 * 	 String RequestId
	 */
	public String getRequestId() {
		ObjectNode node;
		try {
			node = new ObjectMapper().readValue(response, ObjectNode.class);
		} catch (IOException e) {
			return null;
		}
		if(node.has("RequestId")) {
			return node.get("RequestId").asText();
		}
		return null;
	}

}
