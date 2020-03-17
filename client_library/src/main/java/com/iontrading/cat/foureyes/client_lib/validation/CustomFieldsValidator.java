package com.iontrading.cat.foureyes.client_lib.validation;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.iontrading.cat.foureyes.client_lib.annotation.FourEyes;
import com.iontrading.cat.foureyes.client_lib.enums.FieldType;
import com.iontrading.cat.foureyes.client_lib.submitter.ExtendedPendingRequestSubmissionBuilder;
import com.iontrading.cat.foureyes.client_lib.submitter.customFields.CustomField;
import com.iontrading.isf.trace.ITracer;

public class CustomFieldsValidator implements Validator {

	private static final String LOG_KEY = "CustomFieldsValidator";
	private final ITracer tracer;
	private String errorMsg;

	@Inject
	public CustomFieldsValidator(@FourEyes ITracer tracer) {
		this.tracer = tracer;
		this.errorMsg = StringUtils.EMPTY;
	}

	@Override
	public boolean isValid(ExtendedPendingRequestSubmissionBuilder request) {
		List<CustomField> customFileds = request.getCustomFiledsList();
		for (CustomField customField : customFileds) {
			String name = customField.getName();
			String value = customField.getValue();
			if (name == null || name.equals(StringUtils.EMPTY)) {
				errorMsg = "Custom field's Name can not be null or empty";
				tracer.ERROR().key(LOG_KEY).action(errorMsg).token("Name", customField.getName())
						.token("Value", customField.getValue()).end();
				return false;
			}
			if (value == null) {
				errorMsg = "Value of Custom field can not be null";
				tracer.ERROR().key(LOG_KEY).action(errorMsg).token("Name", customField.getName())
						.token("Value", customField.getValue()).end();
				return false;
			}
			if (!validateValueAgainstType(customField)) {
				return false;
			}
		}
		return true;
	}

	private boolean validateValueAgainstType(CustomField customField) {
		FieldType type = customField.getType();
		String value = customField.getValue();
		if (type != null) {
			switch (type) {
			case INT:
				try {
					Integer.valueOf(value);
				} catch (NumberFormatException e) {
					errorMsg = "Value of Custom field is not a valid integer." + " Name: " + customField.getName()
							+ ", Value: " + value;
					return false;
				}
				break;
			case REAL:
				try {
					Double.valueOf(value);
				} catch (NumberFormatException e) {
					errorMsg = "Value of Custom field is not a valid Real Value." + " Name: " + customField.getName()
							+ ", Value: " + value;
					return false;
				}
				break;
			case DATE:
				try {
					DateTimeFormatter.ISO_DATE.parse(value);
				} catch (DateTimeParseException e) {
					errorMsg = "Value of Custom field is not a valid Date." + " Name: " + customField.getName()
							+ ", Value: " + value;
					return false;
				}
				break;
			case TIME:
				try {
					DateTimeFormatter.ISO_TIME.parse(value);
				} catch (DateTimeParseException e) {
					errorMsg = "Value of Custom field is not a valid Time." + " Name: " + customField.getName()
							+ ", Value: " + value;
					return false;
				}
				break;
			case DATETIME:
				try {
					DateTimeFormatter.ISO_DATE_TIME.parse(value);
				} catch (DateTimeParseException e) {
					errorMsg = "Value of Custom field is not a valid DateTime." + " Name: " + customField.getName()
							+ ", Value: " + value;
					return false;
				}
				break;
			default:
				break;
			}
		}
		return true;
	}

	@Override
	public String getErrorMessage() {
		return errorMsg;
	}

}
