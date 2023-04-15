package com.example.rucafe_android;

/**
 * Types of add-ons that can be added to customize a coffee order and their string representations.
 *
 * @author Marco Pigna, Bryan Bezerra
 */
public enum CoffeeAddon {
    SWEET_CREAM("Sweet Cream"),
    F_VANILLA("French Vanilla"),
    IRISH_CREAM("Irish Cream"),
    CARAMEL("Caramel"),
    MOCHA("Mocha");

    private final String name;

    /**
     * Creates a coffee add-on and stores its name.
     *
     * @param name the name of the add-on
     */
    CoffeeAddon(String name) {
        this.name = name;
    }

    /**
     * Fetches the name of this add-on.
     *
     * @return the name of this add-on
     */
    public String getName() {
        return this.name;
    }
}
