package com.example.rucafe_android;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Keeps track of customers' orders. Collects menu items and holds a unique order number.
 *
 * @author Marco Pigna, Bryan Bezerra
 */
public class Order {
    private final int orderNumber;
    private final ArrayList<MenuItem> orderItems = new ArrayList<>();
    private double orderTotal = 0.0;

    /**
     * Creates an order object of the given type
     *
     * @param orderNumber of current order
     */
    Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Returns the order number of this order
     *
     * @return an ordernumber for the associated order
     */
    public int getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * Returns a copy of the menu items included in this order.
     *
     * @return an ArrayList of menu items
     */
    public ArrayList<MenuItem> getMenuItems() {
        return new ArrayList<>(this.orderItems);
    }

    /**
     * Returns the subtotal cost of the order.
     *
     * @return the subtotal of the order
     */
    public double getOrderSubtotal() {
        return this.orderTotal;
    }

    /**
     * Adds an item to the order.
     *
     * @param obj MenuItem to be added to the order
     * @return true if MenuItem is successfully added, otherwise false
     */
    public boolean add(Object obj) {
        if (obj instanceof MenuItem item) {
            this.orderItems.add(item);
            this.orderTotal += item.itemPrice();
            return true;
        }
        return false;
    }

    /**
     * Removes an item from the order.
     *
     * @param obj MenuItem to be removed from list
     * @return true if MenuItem is successfully removed, otherwise false
     */
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem item) {
            this.orderItems.remove(item);
            this.orderTotal -= item.itemPrice();
            return true;
        }
        return false;
    }

    /**
     * Checks to see if another obj is equal to this Order obj.
     * Orders are equal if they share an order number.
     *
     * @param obj the obj you wish to compare to this obj
     * @return a boolean value: true if obj is equal to this obj, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order other) {
            return this.getOrderNumber() == other.getOrderNumber();
        }
        return false;
    }

    /**
     * Generates a string representation of the order
     *
     * @return a string representation of the order
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Order #").append(this.orderNumber).append("\n");
        for (MenuItem m : this.orderItems) {
            result.append(m).append("\n");
        }
        return result.toString();
    }
}
