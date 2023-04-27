package com.example.rucafe_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Donut Ordering Activity
 * Allows user to select quantity, and type of donut to add to basket
 *
 * @author Marco Pigna, Bryan Bezerra
 */
public class OrderingDonutsActivity extends AppCompatActivity implements RecyclerViewClickInterface {
    private TextView donutTotal;
    private final ArrayList<Donut> donutItems = new ArrayList<>();
    private DonutAdapter donutHolder;

    private final int[] itemImages = {R.drawable.blueberry_cake, R.drawable.coffee_cake, R.drawable.oldfashioned_cake
            , R.drawable.chocolate_hole, R.drawable.glazed_hole, R.drawable.jelly_hole
            , R.drawable.cinnamon_hole, R.drawable.chocolate_yeast, R.drawable.strawberry_yeast
            , R.drawable.vanilla_yeast, R.drawable.boston_yeast, R.drawable.powdered_yeast
            , R.drawable.double_yeast};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_donuts);
        RecyclerView recycler = (RecyclerView) findViewById(R.id.rcView);
        Button addToBasket = (Button) findViewById(R.id.add_to_BasketBT);
        donutTotal = (TextView) findViewById(R.id.donut_total_Field);

        setupDonuts();
        createAddToBasketOnClick(addToBasket);
        donutHolder = new DonutAdapter(this, donutItems, this);
        recycler.setAdapter(donutHolder);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Adds Donut order to basket
     *
     * @param button the add to basket button
     */
    private void createAddToBasketOnClick(Button button) {
        button.setOnClickListener(view -> {
            if (emptySelection()) { //error checking case to make sure donuts have been selected
                return;
            }

            AlertDialog.Builder alert = new AlertDialog.Builder(OrderingDonutsActivity.this);
            alert.setTitle("Add to order");
            alert.setMessage("Would you like to add these donuts to your order?");

            alert.setPositiveButton("yes", (dialog, which) -> {
                placeDonutOrderInBasket();
                Toast.makeText(OrderingDonutsActivity.this,
                        "Donuts have been added to basket!", Toast.LENGTH_LONG).show();

            }).setNegativeButton("no", (dialog, which) -> Toast.makeText(OrderingDonutsActivity.this,
                    "Donuts have not been added to basket!", Toast.LENGTH_LONG).show());
            AlertDialog dialog = alert.create();
            dialog.show();
        });
    }

    /**
     * Adds DonutItems to arraylist of donut items, populates default values
     */
    private void setupDonuts() {
        String[] cakeFlavors = getResources().getStringArray(R.array.cake_flavors);
        String[] holeFlavors = getResources().getStringArray(R.array.hole_flavors);
        String[] yeastFlavors = getResources().getStringArray(R.array.yeast_flavors);

        int current = 0; //will be used for setting images
        for (String cakeFlavor : cakeFlavors) {
            donutItems.add(new Donut(DonutType.CAKE, 0, cakeFlavor, itemImages[current]));
            current++;
        }

        for (String holeFlavor : holeFlavors) {
            donutItems.add(new Donut(DonutType.HOLE, 0, holeFlavor, itemImages[current]));
            current++;
        }

        for (String yeastFlavor : yeastFlavors) {
            donutItems.add(new Donut(DonutType.YEAST, 0, yeastFlavor, itemImages[current]));
            current++;
        }
    }

    /**
     * Interface method that updates total when donut quantity incremented
     *
     * @param position
     */
    @Override
    public void onIncrementBTClick(int position) {
        Donut donut = donutItems.get(position);
        double price = donut.getDonutPrice();
        double total = Double.parseDouble(donutTotal.getText().toString());
        int quantity = donut.getQuantity();
        quantity += 1;
        donutHolder.notifyDataSetChanged();
        donut.setQuantity(quantity);
        total += price;
        String totalString = String.format("%.2f", total);
        donutTotal.setText(totalString);
    }

    /**
     * Interface method that updates total price when donut quantity decremented
     *
     * @param position the index
     */
    @Override
    public void onDecrementBTClick(int position) {
        double price = donutItems.get(position).getDonutPrice();
        double total = Double.parseDouble(donutTotal.getText().toString());
        int quantity = donutItems.get(position).getQuantity();
        quantity -= 1;
        donutHolder.notifyDataSetChanged();
        donutItems.get(position).setQuantity(quantity);
        total -= price;
        String totalString = String.format("%.2f", total);
        donutTotal.setText(totalString);
    }

    /**
     * Helper that resets default values upon successful order placement
     */
    public void clearAllFields() {
        donutItems.clear();
        donutHolder.notifyDataSetChanged();
        setupDonuts();
        donutTotal.setText("0.00");
    }


    /**
     * Method that iterates through recycler view to create an arraylist of Donuts to add to basket
     *
     * @return
     */
    public void placeDonutOrderInBasket() {
        for (int i = 0; i < donutItems.size(); i++) {
            Donut donut = donutItems.get(i);
            if (donut.getQuantity() > 0) {
                MainActivity.currentOrder.add(donut);
            }
        }
        clearAllFields();
    }

    /**
     * Helper method that makes sure selection is valid; customer has selected donuts to add to basket
     *
     * @return true if no donuts have been selected, false otherwise
     */
    public boolean emptySelection() {

        int selectionCheck = 0;
        for (int i = 0; i < donutItems.size(); i++) {
            if (donutItems.get(i).getQuantity() > 0) {
                selectionCheck++;
            }
        }

        //error checking case
        if (selectionCheck == 0) { //no selection has been made display error message
            AlertDialog.Builder alert = new AlertDialog.Builder(OrderingDonutsActivity.this);
            alert.setTitle("RU Cafe Error");
            alert.setMessage("Please select donuts before adding to basket!");

            AlertDialog dialog = alert.create();
            dialog.show();

            return true;
        }
        return false;
    }

}