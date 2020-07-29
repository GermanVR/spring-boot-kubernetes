package com.microservices.skeleton.exceptions;

public abstract class ServiceUnavailableException extends GlobalApiException {
	/**
	 * @author German Vazquez Renteria
	 * @id GermanVR
	 * @url https://github.com/GermanVR
	 */
	public ServiceUnavailableException(String exception) {
		super(exception);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Integer getHttpStatus() {
		return 503;
	}

}
