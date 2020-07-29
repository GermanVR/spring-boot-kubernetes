package com.microservices.skeleton.exceptions;

/**
 * @author German Vazquez Renteria
 * @id GermanVR
 * @url https://github.com/GermanVR
 */
public class AgeMinorException extends BusinessRuleException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgeMinorException(String exception) {
		super(exception);
	}

}
