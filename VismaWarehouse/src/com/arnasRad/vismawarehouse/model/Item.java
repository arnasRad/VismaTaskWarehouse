package com.arnasRad.vismawarehouse.model;

import com.arnasRad.vismawarehouse.utils.Utilities;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class Item implements Comparable<Item> {

    private String code;
    private String name;
    private Date expirationDate;

    /**
     * Constructor
     * @param code item code
     * @param name item name
     * @param expirationDate item expiration date
     */
    public Item(String code, String name, Date expirationDate) {
        this.code = code;
        this.name = name;
        this.expirationDate = expirationDate;
    }

    /**
     * Getter
     * @return item code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter
     * @return item name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     * @return item expiration date
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Get formatted expiration date string
     * @return item expiration date string
     */
    public String getExpirationDateString() {
        return Utilities.getDateString(expirationDate);
    }

    /**
     * Overrided method
     * @return Item object string representation
     */
    @Override
    public String toString() {
        return "Name='" + name + '\'' +
                ", Code='" + code + '\'' +
                ", ExpirationDate=" + getExpirationDateString();
    }

    /**
     * Overrided method
     * @param obj object to compare this Item object to
     * @return true if compared objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Item)) {
            return false;
        }

        Item item = (Item) obj;

        // items are considered equal only if their code, name and expiration dates are equal
        return this.code.equals(item.getCode()) &&
                this.name.equals(item.getName()) &&
                expirationDate.equals(item.getExpirationDate());
    }

    /**
     * Overrided method
     * @return object hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, name, expirationDate);
    }

    /**
     * Implemented method (Comparable interface)
     * @param o item to compare this Item object to
     * @return -1 if this item is less than o, 0 if equal; 1 if greater than o
     */
    @Override
    public int compareTo(Item o) {

        return Comparator.comparing(Item::getName)
                .thenComparing(Item::getCode)
                .thenComparing(Item::getExpirationDate)
                .compare(this, o);
    }
}
