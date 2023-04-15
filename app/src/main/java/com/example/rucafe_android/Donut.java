package com.example.rucafe_android;

/**
 * Donuts that are sold in the cafe.
 * Donuts can be of several types.
 *
 * @author Marco Pigna, Bryan Bezerra
 */
public class Donut extends MenuItem {
    private final DonutType type;
    private final String flavor;
    private final int quantity;

    /**
     * Creates a donut object of the given type and quantity.
     *
     * @param type     the type of the donut
     * @param quantity the number of donuts
     * @param flavor   the flavor of the donut
     */
    Donut(DonutType type, int quantity, String flavor) {
        this.type = type;
        this.quantity = quantity;
        this.flavor = flavor;
    }

    /**
     * Returns the price of the donut before tax.
     *
     * @return the price of the donut
     */
    public double itemPrice() {
        return this.type.price() * this.quantity;
    }

    /**
     * Returns the DonutFlavor of the Donut Object
     *
     * @return DonutFlavor
     */
    public String getFlavor() {
        return this.flavor;
    }

    /**
     * Returns the DonutType of the Donut Object
     *
     * @return DonutType
     */
    public DonutType getType() {
        return this.type;
    }

    /**
     * Returns the quantity of the Donut Object
     *
     * @return quantity of Donuts
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Returns a String showing the DonutFlavor and quantity
     *
     * @return String of DonutFlavor and Quantity
     */
    @Override
    public String toString() {
        return this.flavor + " Donut (" + this.quantity + ")";
    }

    /**
     * Checks to see if another obj is equal to this Donut obj.
     *
     * @param obj the obj you wish to compare to this obj
     * @return a boolean value: true if obj is equal to this obj, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Donut other) {
            return this.type.equals(other.type) &&
                    this.flavor.equals(other.flavor) &&
                    this.quantity == other.quantity;
        }
        return false;
    }
}
