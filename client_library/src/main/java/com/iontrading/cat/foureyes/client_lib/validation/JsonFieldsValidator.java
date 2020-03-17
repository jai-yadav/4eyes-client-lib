package com.iontrading.cat.foureyes.client_lib.validation;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.iontrading.cat.foureyes.client_lib.annotation.FourEyes;
import com.iontrading.cat.foureyes.client_lib.enums.Fields;
import com.iontrading.cat.foureyes.client_lib.exceptions.ValidationException;
import com.iontrading.cat.foureyes.client_lib.submitter.ExtendedPendingRequestSubmissionBuilder;
import com.iontrading.cat.foureyes.client_lib.utils.ValidationHelper;
import com.iontrading.isf.trace.ITracer;

public class JsonFieldsValidator implements Validator {

	private static final String LOG_KEY = "JsonFieldsValidator";
	private final ITracer tracer;
	private String errorMsg;

	@Inject
	public JsonFieldsValidator(@FourEyes ITracer tracer) {
		this.tracer = tracer;
		this.errorMsg = StringUtils.EMPTY;
	}

	@Override
	public boolean isValid(ExtendedPendingRequestSubmissionBuilder request) {
		try {
			if (request.getDetails() != null) {
				ValidationHelper.checkJson(request.getDetails(), Fields.DETAILS);
			}
		} catch (ValidationException e) {

			tracer.ERROR().key(LOG_KEY).action("Validating JSON Content")
					.token(e.getField().getServerString(), e.getValue()).end();

			errorMsg = e.getMessage();

			return false;
		}
		return true;
	}

	@Override
	public String getErrorMessage() {
		return errorMsg;
	}

}
