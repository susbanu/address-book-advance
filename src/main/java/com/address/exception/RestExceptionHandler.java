package com.address.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(AddressNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(AddressNotFoundException ex, WebRequest request) {
		LOG.error("AddressNotFound Exception : {0}", ex);
		var apiDetail = new ApiError("Address not found", ex.getMessage(), HttpStatus.NOT_FOUND);
		return buildResponseEntity(apiDetail);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	protected ResponseEntity<Object> invalidParamsExceptionHandler(ConstraintViolationException ex, WebRequest request) {
		LOG.error("ConstraintViolation Exception : {0}", ex);
		List<ApiSubError> apiSubErrors = new ArrayList<>();
		ex.getConstraintViolations().forEach(violation -> apiSubErrors.add(new ApiValidationError(violation.getPropertyPath().toString(), violation.getInvalidValue(), violation.getMessage())));
		
		var apiError = new ApiError("Constraint violation error", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, apiSubErrors);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(MongoDBException.class)
	protected ResponseEntity<Object> handleEntityNotFound(MongoDBException ex, WebRequest request) {
		LOG.error("Monogo Database Exception : {0}", ex);
		var errorDetail = new ApiError("Monogo Database Exception", ex.getLocalizedMessage(), HttpStatus.CONFLICT);
		return buildResponseEntity(errorDetail);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> unknownException(Exception ex, WebRequest request) {
		LOG.info("Internal server error : {0}", ex);
		var errorDetail = new ApiError("Internal server error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return buildResponseEntity(errorDetail);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		LOG.error("BAD Request : {0} ", ex);
		String error = ex.getParameterName() + " parameter is missing";
		var errorDetail = new ApiError(error, ex.getMessage(), HttpStatus.BAD_REQUEST);
		return buildResponseEntity(errorDetail);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		LOG.error("Request handler not found : {0}", ex);
		var errorDetail = new ApiError("Requested resource "+ex.getRequestURL()+" wasn't found on the server", ex.getLocalizedMessage(), status);
		return buildResponseEntity(errorDetail);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.error("Method argument not valid Exception : {0}", ex);
		List<ApiSubError> apiSubErrors = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(fieldErrors -> apiSubErrors.add(new ApiValidationError(fieldErrors.getField().toString(), fieldErrors.getRejectedValue(), fieldErrors.getDefaultMessage())));
		
		var apiError = new ApiError("Method argument not valid", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, apiSubErrors);
		return buildResponseEntity(apiError);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError errorDetail) {
		return new ResponseEntity<>(errorDetail, errorDetail.getStatus());
	}
}
