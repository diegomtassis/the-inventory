package com.inventory.commons.domain;

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
			throw new ValidationException(message);
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
			throw new ValidationException(message);
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
			throw new ValidationException(message);
		}
	};
}
