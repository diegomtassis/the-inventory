package org.diegomtassis.inventory.commons.pagination;

import org.diegomtassis.inventory.commons.application.Validate;

/**
 * @author diegomtassis
 *
 */
public class SortingCriteria {

	private String property;

	private SortDirection direction;

	/**
	 * Constructor.
	 */
	public SortingCriteria() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param property
	 * @param direction
	 */
	private SortingCriteria(String property, SortDirection direction) {
		super();
		Validate.isNotBlank(property, "Sort property can not be blank");
		this.property = property;
		this.direction = direction != null ? direction : SortDirection.ASC;
	}

	/**
	 * Factory method.
	 * 
	 * @param property
	 * @param direction
	 * @return {@link SortingCriteria}
	 */
	public static SortingCriteria of(String property, SortDirection direction) {
		return new SortingCriteria(property, direction);
	}

	public String getProperty() {
		return this.property;
	}

	public SortDirection getDirection() {
		return this.direction;
	}

	/**
	 * Sort direction.
	 * 
	 * @author diegomtassis
	 *
	 */
	public static enum SortDirection {
		ASC, DESC;
	}
}
