package com.microservices.skeleton.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.gson.Gson;
import com.microservices.skeleton.config.AppConfig;
import com.microservices.skeleton.responses.ResponseExceptionBean;
import com.microservices.skeleton.utilities.IResponseUtil;

/**
 * @author German Vazquez Renteria
 * @id GermanVR
 * @url https://github.com/GermanVR
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private IResponseUtil response;

	@Autowired
	private AppConfig appConfig;

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return response.exceptionResponseEntity(Arrays.asList(ex.toString()), status.value(), appConfig.getId());
	}

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String exceptionMessage = ex.toString();
		if (ex instanceof ResponseStatusException) {
			status = ((ResponseStatusException) ex).getStatus();
		} else if (ex instanceof GlobalApiException) {
			status = HttpStatus.valueOf(((GlobalApiException) ex).getHttpStatus());
		} else if (ex instanceof HttpStatusCodeException) {
			return clientException(ex);
		}
		return response.exceptionResponseEntity(response.createMessages(exceptionMessage), status.value(), appConfig.getId());

	}

	/**
	 * @param ex
	 * @return
	 */
	private ResponseEntity<Object> clientException(Exception ex) {
		HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) ex;
		HttpStatus statusCode = httpStatusCodeException.getStatusCode();
		String responseBodyAsString = httpStatusCodeException.getResponseBodyAsString();
		List<String> createMessages = new ArrayList<>();
		try {
			createMessages.add(ex.toString());
			@SuppressWarnings("unchecked")
			ResponseExceptionBean<Object> convertedObject = new Gson().fromJson(responseBodyAsString, ResponseExceptionBean.class);
			if (convertedObject.getAppCode() == null) {
				return response.exceptionResponseEntity(createMessages, statusCode.value(), appConfig.getId());
			}
			return new ResponseEntity<>(convertedObject, statusCode);
		} catch (Exception e) {
			createMessages.add("Convert Object Failed");
		}
		return response.exceptionResponseEntity(createMessages, statusCode.value(), appConfig.getId());

	}

}