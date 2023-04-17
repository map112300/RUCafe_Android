package com.example.rucafe_android;


/**
 * This class defines the data structure of an item to be displayed in the RecyclerView
 * @author Marco Pigna, Bryan Bezerra
 */
public class DonutItem {

    private String flavor;
    private int image;
    private double donutPrice;

    /**
     * Parameterized constructor.
     * @param flavor
     * @param image
     * @param donutPrice
     */
    public DonutItem(String flavor, int image, double donutPrice) {
        this.flavor = flavor;
        this.image = image;
        this.donutPrice = donutPrice;
    }

    /**
     * Getter method that returns the flavor of any donut item
     * @return the flavor of any donut item.
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Getter method that returns the image of an item.
     * @return the image of an item.
     */
    public int getImage() {
        return image;
    }

    /**
     * Getter method that returns the donut price.
     * @return the donut price of the item.
     */
    public double getDonutPrice() {
        return donutPrice;
    }





}
