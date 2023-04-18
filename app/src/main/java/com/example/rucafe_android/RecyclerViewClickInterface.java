package com.example.rucafe_android;

public interface RecyclerViewClickInterface {

    /**
     * Method to be implemented in any class that inhereits interface
     * @param position
     * @param quantity
     */
    void onIncrementBTClick(int position, int quantity);

    /**
     * Method to be implemented in any class that inhereits interface
     * @param position
     * @param quantity
     */
    void onDecrementBTClick(int position, int quantity);
}
