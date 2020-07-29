package com.microservices.skeleton.utilities.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.microservices.skeleton.responses.ResponseBean;
import com.microservices.skeleton.responses.ResponseExceptionBean;
import com.microservices.skeleton.utilities.IResponseUtil;

/**
 * @author German Vazquez Renteria
 * @id GermanVR
 * @url https://github.com/GermanVR
 */
@Component
public class ResponseUtil implements IResponseUtil {

	public <T> ResponseExceptionBean<T> createExceptionResponse(Integer status, String appCode, List<String> messages) {
		return new ResponseExceptionBean<>(status, null, messages, appCode);
	}

	public <T> ResponseBean<T> createResponse(HttpStatus status, T payload, String... messages) {
		return new ResponseBean<>(status.value(), payload, createMessages(messages));
	}

	public List<String> createMessages(String... messages) {
		return messages != null ? Arrays.asList(messages) : new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	public <T> ResponseEntity<T> successResponseEntity(T response, String message) {
		ResponseBean<T> successResponse = createResponse(HttpStatus.OK, response, message);
		return new ResponseEntity<>((T) successResponse, HttpStatus.valueOf(successResponse.getStatus()));
	}

	@SuppressWarnings("unchecked")
	public <T> ResponseEntity<T> createResponseEntity(HttpStatus status, T response, String message) {
		ResponseBean<T> successResponse = createResponse(status, response, message);
		return new ResponseEntity<>((T) successResponse, HttpStatus.valueOf(successResponse.getStatus()));
	}

	@SuppressWarnings("unchecked")
	public <T> ResponseEntity<T> exceptionResponseEntity(List<String> message, Integer status, String appCode) {
		ResponseExceptionBean<T> exceptionResponse = createExceptionResponse(status, appCode + status, message);
		return new ResponseEntity<>((T) exceptionResponse, HttpStatus.valueOf(exceptionResponse.getStatus()));
	}

}