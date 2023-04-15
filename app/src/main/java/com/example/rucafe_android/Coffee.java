package com.example.rucafe_android;

import java.util.ArrayList;

/**
 * Coffee that is sold in the cafe.
 * Coffee comes in several sizes and add-ons can be provided.
 *
 * @author Marco Pigna, Bryan Bezerra
 */
public class Coffee extends MenuItem {
    private final CoffeeSize size;
    private final ArrayList<CoffeeAddon> addons;
    private final int quantity;

    private static final double ADDON_PRICE = 0.30;

    /**
     * Creates a coffee object of the given size with no add-ons.
     *
     * @param size the size of the coffee
     */
    Coffee(CoffeeSize size) {
        this.size = size;
        this.addons = null;
        this.quantity = 1;
    }

    /**
     * Creates a coffee object of the given size with add-ons.
     *
     * @param size   the size of the coffee
     * @param addons the add-ons to the coffee
     */
    Coffee(CoffeeSize size, ArrayList<CoffeeAddon> addons) {
        this.size = size;
        this.addons = addons;
        this.quantity = 1;
    }

    /**
     * Creates a coffee object of the given size and quantity with add-ons.
     *
     * @param size     the size of the coffee
     * @param quantity the number of coffee cups
     * @param addons   the add-ons to the coffee
     */
    Coffee(CoffeeSize size, int quantity, ArrayList<CoffeeAddon> addons) {
        this.size = size;
        this.addons = addons;
        this.quantity = quantity;
    }

    /**
     * Returns the price of the coffee before tax.
     *
     * @return the price of the coffee
     */
    public double itemPrice() {
        double result = this.size.price();
        if (this.addons != null) {
            result += addons.size() * ADDON_PRICE;
        }
        return result * this.quantity;
    }

    /**
     * Returns a string showing the size, addons, and quantity
     *
     * @return string containing size, addons, and quantity
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Coffee ");
        result.append(this.size).append(" ");
        if (this.addons != null && this.addons.size() > 0) {
            result.append("[");
            for (CoffeeAddon addon : this.addons) {
                result.append(addon.getName()).append(", ");
            }
            result.deleteCharAt(result.length() - 1);
            result.deleteCharAt(result.length() - 1);
            result.append("] ");
        }
        result.append("(").append(this.quantity).append(")");
        return result.toString();
    }
}
