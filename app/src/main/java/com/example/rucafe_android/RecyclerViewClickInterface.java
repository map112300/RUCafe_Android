package com.example.rucafe_android;

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
