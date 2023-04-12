package com.inventory.domain.api;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * Item entity.
 * 
 * @author diegomtassis
 *
 */
public interface Item {

	/**
	 * @return the item id
	 */
	String getId();

	/**
	 * @return the owner
	 */
	User owner();

	/**
	 * @return the acquisition date
	 */
	LocalDate getAcquisitionDate();

	/**
	 * Changes the acquisition date.
	 * 
	 * @param acquisitionDate
	 */
	void setAcquisitionDate(LocalDate acquisitionDate);

	/**
	 * @return the labels of the item
	 */
	Set<String> getLabels();

	/**
	 * @return the properties of the item
	 */
	Map<String, String> getProperties();

	/**
	 * Set the value for a property. If it exists already then its value is
	 * overridden.
	 * 
	 * @param name
	 * @param value
	 * @return the current item
	 */
	Item setProperty(String name, String value);

	/**
	 * Removes the value of a property if it exists, otherwise it does nothing.
	 * 
	 * @param name
	 */
	void removeProperty(String name);

	/**
	 * Add a new label.
	 * 
	 * @param label
	 * @return the current item
	 */
	Item addLabel(String label);

	/**
	 * Add some new labels.
	 * 
	 * @param labels
	 * @return the current item
	 */
	Item addLabel(String... labels);

	/**
	 * Removes a label if already exists.
	 * 
	 * @param label
	 */
	void removeLabel(String label);

	/**
	 * @return creation instant
	 */
	Instant getCreationInstant();

	/**
	 * @return last change instant
	 */
	Instant getLastUpdateInstant();
}
