package org.diegomtassis.inventory.item.persistence.jpa;

import org.diegomtassis.inventory.item.persistence.api.ItemDTO;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * JPA {@link ItemDTO} implementation using JPA.
 * 
 * @author diegomtassis
 */
public class JpaItemDTO implements ItemDTO {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public String ownerId() {
        return null;
    }

    @Override
    public LocalDate getAcquisitionDate() {
        return null;
    }

    @Override
    public Set<String> getLabels() {
        return null;
    }

    @Override
    public Map<String, String> getProperties() {
        return null;
    }

    @Override
    public Instant getCreationInstant() {
        return null;
    }
}
