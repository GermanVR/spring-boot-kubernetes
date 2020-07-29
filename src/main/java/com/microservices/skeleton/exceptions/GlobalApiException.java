package com.microservices.skeleton.exceptions;

/**
 * @author German Vazquez Renteria
 * @id GermanVR
 * @url https://github.com/GermanVR
 */
public abstract class GlobalApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract Integer getHttpStatus();

	public GlobalApiException(String exception) {
		super(exception);
	}

}
