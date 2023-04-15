package com.example.rucafe_android;

/**
 * Types of donuts and their corresponding prices.
 *
 * @author Marco Pigna, Bryan Bezerra
 */
public enum DonutType {
    YEAST(1.59, "Yeast"),
    CAKE(1.79, "Cake"),
    HOLE(0.39, "Hole");

    private final double price;
    private final String type;

    /**
     * Creates a donut type and stores its price.
     *
     * @param price the price of the donut type
     */
    DonutType(double price, String type) {
        this.price = price;
        this.type = type;
    }


    /**
     * Fetches the price of this donut type.
     *
     * @return the price of the donut type
     */
    public double price() {
        return this.price;
    }

    /**
     * Fetches the String representation of the type
     *
     * @return String representation of type of Donut
     */
    public String getType() {
        return this.type;
    }
}
