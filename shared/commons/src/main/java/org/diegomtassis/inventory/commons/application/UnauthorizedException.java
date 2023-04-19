package org.diegomtassis.inventory.commons.application;

/**
 * Analogous to HTTP-401.
 * 
 * @author diegomtassis
 *
 */
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 5002486411585506556L;

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public UnauthorizedException(String message) {
		super(message);
	}
}
