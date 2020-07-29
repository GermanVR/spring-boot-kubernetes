package com.microservices.skeleton.utilities;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.microservices.skeleton.responses.ResponseBean;
import com.microservices.skeleton.responses.ResponseExceptionBean;

/**
 * @author German Vazquez Renteria
 * @id GermanVR
 * @url https://github.com/GermanVR
 */
public interface IResponseUtil {

	public <T> ResponseExceptionBean<T> createExceptionResponse(Integer status, String appCode, List<String> messages);

	public <T> ResponseBean<T> createResponse(HttpStatus status, T payload, String... messages);

	public List<String> createMessages(String... messages);

	public <T> ResponseEntity<T> successResponseEntity(T response, String message);

	public <T> ResponseEntity<T> createResponseEntity(HttpStatus status, T response, String message);

	public <T> ResponseEntity<T> exceptionResponseEntity(List<String> message, Integer status, String appCode);

}
