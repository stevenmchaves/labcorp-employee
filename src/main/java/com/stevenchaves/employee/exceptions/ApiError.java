package com.stevenchaves.employee.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public
class ApiError {
	private String object;
	private String field;
	private Object rejectedValue;
	private HttpStatus status;
	private String message;
	private String debugMessage;
	private List<ApiError> errors;

	private ApiError() {
		// do nothing
	}

	ApiError(String object, String message) {
		this.object = object;
		this.message = message;
	}

	ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	ApiError(HttpStatus status, Throwable ex) {
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

	public HttpStatus getStatus() {
		return this.status;
	}
}