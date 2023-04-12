package com.inventory.commons.application;

/**
 * Analogous to HTTP-400.
 * 
 * @author diegomtassis
 *
 */
public class BadRequestException extends RuntimeException {

	/** Serial version UID */
	private static final long serialVersionUID = 7596078467657475412L;

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public BadRequestException(String message) {
		super(message);
	}
}
