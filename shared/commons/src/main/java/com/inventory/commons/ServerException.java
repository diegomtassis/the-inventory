package com.inventory.commons;

/**
 * Server exception.
 * 
 * @author diegomtassis
 *
 */
public class ServerException extends RuntimeException {

	/** Serial version UID */
	private static final long serialVersionUID = -8467428359617223652L;

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public ServerException(String message) {
		super(message);
	}

}
