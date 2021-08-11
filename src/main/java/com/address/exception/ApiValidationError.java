package com.address.exception;

public class ApiValidationError implements ApiSubError {

	private String field;
	private Object rejectedValue;
	private String message;

	public ApiValidationError(String field, Object rejectedValue, String message) {
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.message = message;
	}

	public ApiValidationError(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ApiValidationError [field=" + field + ", rejectedValue=" + rejectedValue
				+ ", message=" + message + "]";
	}
}
