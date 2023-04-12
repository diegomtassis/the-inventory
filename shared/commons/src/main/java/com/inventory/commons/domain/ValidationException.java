package com.inventory.commons.domain;

/**
 * Validation exception.
 * 
 * @author diegomtassis
 *
 */
public class ValidationException extends RuntimeException {

	/** Serial version UID */
	private static final long serialVersionUID = -4288568902946624185L;

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
	}
}
