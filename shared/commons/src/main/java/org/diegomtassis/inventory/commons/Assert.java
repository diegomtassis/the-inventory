package org.diegomtassis.inventory.commons;

/**
 * Assertion utils. Throws {@link ServerException}.
 * 
 * @author diegomtassis
 *
 */
public class Assert {

	/**
	 * Assert state.
	 * 
	 * @param condition
	 * @param message
	 */
	public static void state(Boolean condition, String message) {
		if (!condition) {
			throw new ServerException(message);
		}
	};

}
