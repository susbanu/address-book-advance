package com.address.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ApiError {

	// The date-time instance of when the error happened
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	// User friendly message about the error
	private String message;
	// A system message describing the error in more detail
	private String debugMessage;
	// The operation call status
	private HttpStatus status;
	// Used for representing multiple errors in a single call. Ex. - Validation Error
	@JsonInclude(value = Include.NON_NULL)
	private List<ApiSubError> subErrors;

	private ApiError() {
		timestamp = LocalDateTime.now();
	}

	public ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

	public ApiError(String message, String debugMessage, HttpStatus status) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = debugMessage;
	}
	
	public ApiError(String message, String debugMessage, HttpStatus status, List<ApiSubError> subErrors) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = debugMessage;
		this.subErrors = subErrors;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public List<ApiSubError> getSubErrors() {
		return subErrors;
	}

	public void setSubErrors(List<ApiSubError> subErrors) {
		this.subErrors = subErrors;
	}
}
