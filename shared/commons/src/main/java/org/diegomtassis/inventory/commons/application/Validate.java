package org.diegomtassis.inventory.commons.application;

import org.apache.commons.lang3.StringUtils;

/**
 * Validator for arguments and conditions.
 * 
 * @author diegomtassis
 *
 */
public class Validate {

	/**
	 * Assert <code>true</code>.
	 * 
	 * @param condition
	 * @param message
	 */
	public static void isTrue(Boolean condition, String message) {
		if (!condition) {
			throw new BadRequestException(message);
		}
	};

	/**
	 * Assert not <code>null</code>.
	 * 
	 * @param object
	 * @param message
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new BadRequestException(message);
		}
	};

	/**
	 * Assert string is not blank.
	 * 
	 * @param string
	 * @param message
	 */
	public static void isNotBlank(String string, String message) {
		if (StringUtils.isBlank(string)) {
			throw new BadRequestException(message);
		}
	};

	/**
	 * Assert authorization.
	 * 
	 * @param isAuthorized
	 * @param message
	 */
	public static void authorized(Boolean isAuthorized, String message) {
		if (!isAuthorized) {
			throw new UnauthorizedException(message);
		}
	};

	/**
	 * Assert permission.
	 * 
	 * @param isAllowed
	 * @param message
	 */
	public static void allowed(Boolean isAllowed, String message) {
		if (!isAllowed) {
			throw new ForbiddenException(message);
		}
	};
}
