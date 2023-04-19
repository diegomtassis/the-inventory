package org.diegomtassis.inventory.item.domain.impl;


import org.diegomtassis.inventory.commons.domain.Validate;
import org.diegomtassis.inventory.commons.pagination.PagedList;
import org.diegomtassis.inventory.commons.pagination.PagingCriteria;
import org.diegomtassis.inventory.commons.pagination.SortingCriteria;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.diegomtassis.inventory.item.domain.api.ItemRepository;
import org.diegomtassis.inventory.item.domain.api.ItemSearchCriteria;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * {@link ItemRepository} implementation in memory.
 *
 * @author diegomtassis
 */
public class InMemoryItemRepository implements ItemRepository<SimpleItem> {

    private Map<String, SimpleItem> items = new HashMap<>();

    @Override
    public SimpleItem save(SimpleItem itemToBeSaved) {

        Validate.notNull(itemToBeSaved, "null item");

        if (!(itemToBeSaved instanceof SimpleItem)) {
            throw new IllegalArgumentException(InMemoryItemRepository.class.getSimpleName() + " can only handle "
                    + SimpleItem.class.getSimpleName() + " instances");
        }

        SimpleItem savedItem = new SimpleItem(itemToBeSaved.owner());
        itemToBeSaved.getProperties().forEach((k, v) -> {
            savedItem.setProperty(k, v);
        });
        itemToBeSaved.getLabels().forEach(l -> savedItem.addLabel(l));
        savedItem.setAcquisitionDate(itemToBeSaved.getAcquisitionDate());

        if (StringUtils.isBlank(itemToBeSaved.getId())) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            savedItem.setId(Long.toString(new Date().getTime()));
        } else {
            Validate.isTrue(this.items.containsKey(itemToBeSaved.getId()),
                    "The item has an id but it does not exist in the system");
            savedItem.setId(itemToBeSaved.getId());
        }

        this.items.put(savedItem.getId(), savedItem);

        return savedItem;
    }

    @Override
    public Optional<SimpleItem> retrieve(String id) {

        Validate.isTrue(StringUtils.isNotBlank(id), "Id can not be empty");
        return Optional.ofNullable(this.items.get(id));
    }

    @Override
    public PagedList<SimpleItem> list(PagingCriteria pagingCriteria, SortingCriteria sortingCriteria) {

        List<SimpleItem> elements = ListUtils.unmodifiableList(new ArrayList<>(this.items.values()));

        return new PagedList<SimpleItem>() //
                .setPage(0) //
                .setSize(1) //
                .setElements(elements) //
                .setNumberOfElements(elements.size()) //
                .setTotalElements(elements.size()) //
                ;
    }

    @Override
    public void delete(SimpleItem item) {

        Validate.notNull(item, "null item");
        Validate.isTrue(StringUtils.isNotBlank(item.getId()), "Id can not be empty");

        this.items.remove(item.getId());
    }

    @Override
    public PagedList<SimpleItem> list(ItemSearchCriteria searchCriteria, PagingCriteria pagingCriteria,
                                      SortingCriteria sortingCriteria) {

        List<SimpleItem> elements = this.items.values().stream() //
                .filter(i -> this.safeFilter(i.owner(), searchCriteria.getOwner(),
                        () -> i.owner().equals(searchCriteria.getOwner()))) //
                .filter(i -> this.safeFilter(i.getAcquisitionDate(), searchCriteria.getAcquisitionLowerBound(),
                        () -> i.getAcquisitionDate().isAfter(searchCriteria.getAcquisitionLowerBound())))
                .filter(i -> this.safeFilter(i.getAcquisitionDate(), searchCriteria.getAcquisitionUpperBound(),
                        () -> i.getAcquisitionDate().isBefore(searchCriteria.getAcquisitionUpperBound())))
                .filter(i -> i.getLabels().containsAll(SetUtils.emptyIfNull(searchCriteria.getLabels())))
                .filter(i -> this.containsProperties(i.getProperties(), searchCriteria.getProperties())) //
                .collect(Collectors.toList());

        return new PagedList().setElements(elements);
    }

    private Boolean safeFilter(Object value, Object property, Supplier<Boolean> supplier) {

        if (property == null) {
            return true;
        }

        if (value == null) {
            return false;
        }

        return supplier.get();
    }

    ;

    /**
     * Checks if a dictionary contains all the entries of an other dictionary.
     *
     * @param container
     * @param contained
     * @return a boolean indicating if the dictionary contains all the keys
     */
    private Boolean containsProperties(Map<String, String> container, Map<String, String> contained) {
        return container.entrySet().containsAll(contained.entrySet());
    }

}
