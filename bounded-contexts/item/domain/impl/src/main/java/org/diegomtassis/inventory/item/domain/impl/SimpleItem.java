package org.diegomtassis.inventory.item.domain.impl;

import org.diegomtassis.inventory.commons.domain.Validate;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.SetUtils;
import org.diegomtassis.inventory.item.domain.api.Item;
import org.diegomtassis.inventory.item.domain.api.User;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Simple {@link Item} implementation.
 * 
 * @author diegomtassis
 *
 */
public class SimpleItem implements Item {

	private String id;

	private SimpleUser owner;

	private LocalDate acquisitionDate;

	private Set<String> labels = new HashSet<>();

	private Map<String, String> properties = new HashMap<>();

	private Instant creationInstant;

	private Instant lastUpdateInstant;

	/**
	 * Constructor.
	 * 
	 * @param owner
	 */
	public SimpleItem(User owner) {

		this();
		this.creationInstant = Instant.now();
		this.lastUpdateInstant = this.creationInstant;
		this.owner = (SimpleUser) owner;
	}

	/**
	 * Constructor for persistence.
	 */
	private SimpleItem() {
	}

	@Override
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public User owner() {
		return this.owner;
	}

	@Override
	public void setAcquisitionDate(LocalDate acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
		this.notifyChange();
	}

	@Override
	public LocalDate getAcquisitionDate() {
		return this.acquisitionDate;
	}

	@Override
	public Set<String> getLabels() {
		return SetUtils.unmodifiableSet(new HashSet<>(this.labels));
	}

	@Override
	public Map<String, String> getProperties() {
		return MapUtils.unmodifiableMap(new HashMap<>(this.properties));
	}

	@Override
	public Item setProperty(String name, String value) {
		Validate.isNotBlank(name, "Name cannot be null");
		Validate.isNotBlank(value, "Value cannot be null");
		this.properties.put(name, value);
		this.notifyChange();
		return this;
	}

	@Override
	public void removeProperty(String name) {
		Validate.isNotBlank(name, "Name cannot be null");
		this.notifyChange();
		this.properties.remove(name);
	}

	@Override
	public Item addLabel(String label) {
		Validate.isNotBlank(label, "Label cannot be null");
		this.labels.add(label);
		this.notifyChange();
		return this;
	}

	@Override
	public Item addLabel(String... labels) {

		Stream.of(labels).forEach(this::addLabel);
		return this;
	}

	@Override
	public void removeLabel(String label) {
		Validate.isNotBlank(label, "Label cannot be null");
		this.labels.remove(label);
		this.notifyChange();
	}

	public Instant getCreationInstant() {
		return this.creationInstant;
	}

	public Instant getLastUpdateInstant() {
		return this.lastUpdateInstant;
	}

	private void notifyChange() {
		this.lastUpdateInstant = Instant.now();
	}
}
