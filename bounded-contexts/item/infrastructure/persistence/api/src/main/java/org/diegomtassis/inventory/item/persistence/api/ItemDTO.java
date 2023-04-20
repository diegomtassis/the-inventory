package org.diegomtassis.inventory.item.persistence.api;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * Item DTO.
 * 
 * @author diegomtassis
 *
 */
public interface ItemDTO {

	/**
	 * @return the item id
	 */
	String getId();

	/**
	 * @return the owner
	 */
	String ownerId();

	/**
	 * @return the acquisition date
	 */
	LocalDate getAcquisitionDate();

	/**
	 * @return the labels of the item
	 */
	Set<String> getLabels();

	/**
	 * @return the properties of the item
	 */
	Map<String, String> getProperties();

	/**
	 * @return creation instant
	 */
	Instant getCreationInstant();

}
