package com.inventory.domain.api;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Searh criteria for items.
 * 
 * @author diegomtassis
 *
 */
public class ItemSearchCriteria {

	private User owner;

	private LocalDate acquisitionDateLowerBound;

	private LocalDate acquisitionDateUpperBound;

	private Set<String> labels = new HashSet<>();

	private Map<String, String> properties = new HashMap<>();

	public User getOwner() {
		return owner;
	}

	public ItemSearchCriteria setOwner(User owner) {
		this.owner = owner;
		return this;
	}

	public LocalDate getAcquisitionLowerBound() {
		return acquisitionDateLowerBound;
	}

	public ItemSearchCriteria setAcquisitionDateLowerBound(LocalDate acquisitionDateLowerBound) {
		this.acquisitionDateLowerBound = acquisitionDateLowerBound;
		return this;
	}

	public LocalDate getAcquisitionUpperBound() {
		return acquisitionDateUpperBound;
	}

	public ItemSearchCriteria setAcquisitionDateUpperBound(LocalDate acquisitionDateUpperBound) {
		this.acquisitionDateUpperBound = acquisitionDateUpperBound;
		return this;
	}

	public Set<String> getLabels() {
		return new HashSet<>(this.labels);
	}

	public ItemSearchCriteria setLabels(String... labels) {
		this.labels.clear();
		this.labels.addAll(Arrays.asList(labels));
		return this;
	}

	public Map<String, String> getProperties() {
		return new HashMap<>(this.properties);
	}

	public ItemSearchCriteria setProperties(Map<String, String> properties) {
		this.properties.clear();
		this.properties.putAll(properties);
		return this;
	}

}
