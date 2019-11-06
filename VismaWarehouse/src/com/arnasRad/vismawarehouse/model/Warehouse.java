package com.arnasRad.vismawarehouse.model;

import com.arnasRad.vismawarehouse.utils.Utilities;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Warehouse class
 * Contains item data and provides operations concerning that data
 *
 */
public class Warehouse {

    // contains distinct items;
    // sums their quantities if equal items are being inserted
    private LinkedHashMap<Item, Long> items;

    /**
     * Default class constructor
     * Creates a new empty LinkedHashMap instance
     */
    public Warehouse() {

        items = new LinkedHashMap<>();
    }

    /**
     * Add item to items list.
     * Appends map value if duplicate key is being inserted into map.
     * Keys (items) are considered duplicates only if their code,
     * name and expiration dates are equal.
     * @param item item to be added to map
     */
    public void addItem(Item item, long quantity) {

        if (items.containsKey(item)) {

            long oldValue = items.get(item);
            items.replace(item, oldValue + quantity);
            return;
        }

        items.put(item, quantity);
    }

    /**
     * Removes an item from items list
     * @param item item to remove from warehouse
     */
    public void removeItem(Item item) {

        items.remove(item);
    }

    /**
     * Prints out a sorted list of all items in the warehouse to the console
     * Items are sorted firstly by name, then code and lastly by expiration date
     */
    public void printItems() {

        System.out.println(Utilities.getItemMapString(
                items, "Items in Warehouse"));
    }

    /**
     * Gets a new filtered LinkedHashMap object containing items
     * that have insufficient quantity.
     * Item has insufficient quantity if its quantity is less than
     * value specified by parameter quantity
     * @param quantity value compared to items' quantity
     * @return LinkedHashMap of items that has insufficient quantities
     */
    private LinkedHashMap<Item, Long> getInsufficientQuantityItems(long quantity) {
        return new LinkedHashMap<>(items.entrySet()
                .stream()
                .filter(x -> (x.getValue() < quantity))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    /**
     * Prints out a sorted list of items with insufficient quantities
     * @param quantity value that must be lower that items' quantity for it to be printed
     */
    public void printInsufficientQuantityItems(long quantity) {

        System.out.println(
                Utilities.getItemMapString(
                        getInsufficientQuantityItems(quantity),
                        "Items with insufficient quantities"));
    }

    /**
     * Gets a new filtered LinkedHashMap object containing items
     * that have expiration dates before specified due date
     * @param dueDate date value compared to items' expiration date
     * @return LinkedHashMap of items that are not expired
     */
    private LinkedHashMap<Item, Long> getExpiredItems(Date dueDate) {

        return new LinkedHashMap<>(items.entrySet()
                .stream()
                .filter(p -> (p.getKey().getExpirationDate().equals(dueDate) ||
                        p.getKey().getExpirationDate().before(dueDate)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    /**
     *
     * Prints out a sorted list of items that are not expired
     * @param dueDate value compared to items' expiration date
     */
    public void printExpiredItems(Date dueDate) {

        System.out.println(Utilities
                .getItemMapString(getExpiredItems(dueDate),
                        "Items that are not expired by " + Utilities.getDateString(dueDate)));
    }

    /**
     * Gets a new filtered LinkedHashMap object containing items
     * that have expiration dates after due date
     * @param dueDate date value compared to items' expiration date
     * @return LinkedHashMap of items that are not expired
     */
    private LinkedHashMap<Item, Long> getSoonToExpireItems(Date dueDate) {

        return new LinkedHashMap<>(items.entrySet()
                .stream()
                .filter(p -> (p.getKey().getExpirationDate().after(dueDate)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    /**
     *
     * Prints out a sorted list of items that are not expired
     * @param dueDate value compared to items' expiration date
     */
    public void printSoonToExpireItems(Date dueDate) {

        System.out.println(Utilities
                .getItemMapString(getSoonToExpireItems(dueDate),
                        "Items that are not expired by " + Utilities.getDateString(dueDate)));
    }
}
