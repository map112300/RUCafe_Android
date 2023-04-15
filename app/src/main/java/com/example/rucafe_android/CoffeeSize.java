package com.example.rucafe_android;

/**
 * The sizes of the coffee sold at the cafe and their corresponding prices before addons.
 *
 * @author Marco Pigna, Bryan Bezerra
 */
public enum CoffeeSize {
    SHORT(1.89),
    TALL(2.29),
    GRANDE(2.69),
    VENTI(3.09);

    private final double price;

    /**
     * Creates a coffee size and stores its price.
     *
     * @param price the price of the coffee size
     */
    CoffeeSize(double price) {
        this.price = price;
    }

    /**
     * Fetches the price of this coffee size.
     *
     * @return the price of the coffee size
     */
    public double price() {
        return this.price;
    }
}
