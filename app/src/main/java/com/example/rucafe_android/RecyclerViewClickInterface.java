package com.example.rucafe_android;

/**
 * RecyclerView Interface
 * Implements increment and decrement actions
 *
 * @author Marco Pigna, Bryan Bezerra
 */
public interface RecyclerViewClickInterface {

    /**
     * Method to be implemented in any class that inhereits interface
     *
     * @param position
     */
    void onIncrementBTClick(int position);

    /**
     * Method to be implemented in any class that inhereits interface
     *
     * @param position
     */
    void onDecrementBTClick(int position);
}
