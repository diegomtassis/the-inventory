package org.diegomtassis.inventory.commons.application;

/**
 * Analogous to HTTP-403.
 * 
 * @author diegomtassis
 *
 */
public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = -410574275676526791L;

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public ForbiddenException(String message) {
		super(message);
	}
}
