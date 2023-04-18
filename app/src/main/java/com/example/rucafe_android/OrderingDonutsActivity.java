package com.example.rucafe_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderingDonutsActivity extends AppCompatActivity implements RecyclerViewClickInterface {

    //TODO FIX FIX this is value that will get passed
    public static double totalDonuts = 0.00;
    private RecyclerView recycler;
    private Button addToBasket;
    private TextView donutTotal;
    private ArrayList <DonutItem> donutItems = new ArrayList<>();



    private int[] itemImages = {R.drawable.blueberry_cake, R.drawable.coffee_cake, R.drawable.oldfashioned_cake
            , R.drawable.chocolate_hole, R.drawable.glazed_hole, R.drawable.jelly_hole
            , R.drawable.cinnamon_hole, R.drawable.chocolate_yeast, R.drawable.strawberry_yeast
            , R.drawable.vanilla_yeast, R.drawable.boston_yeast, R.drawable.powdered_yeast
            , R.drawable.double_yeast};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_donuts);
        recycler = findViewById(R.id.rcView);
        setupDonuts();
        DonutItemAdapter donutHolder = new DonutItemAdapter(this, donutItems, this);
        recycler.setAdapter(donutHolder);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        addToBasket = (Button) findViewById(R.id.add_to_BasketBT);
        donutTotal = findViewById(R.id.donut_total_Field);


        //TODO NEED TO PROPERLY IMPLEMENT
        addToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(OrderingDonutsActivity.this);
                alert.setTitle("Add to order");
                alert.setMessage("Would you like to add these donuts to your order?");


                //handle the "YES" click
                //TODO transfer data on yes click and clear all fields
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        totalDonuts = Double.valueOf(donutTotal.getText().toString()); //update totalDonuts to be passed


                        Toast.makeText(OrderingDonutsActivity.this,
                                "Donuts have been added to basket!", Toast.LENGTH_LONG).show();
                    }
                    //handle the "NO" click
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(OrderingDonutsActivity.this,
                                "Donuts have not been added to basket!", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
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
        for(int i = 0; i < cakeFlavors.length; i++) {
            donutItems.add(new DonutItem(cakeFlavors[i], itemImages[current], DonutType.CAKE.price(), 0));
            current++;
        }

        for(int i = 0; i < holeFlavors.length; i++) {
            donutItems.add(new DonutItem(holeFlavors[i], itemImages[current], DonutType.HOLE.price(), 0));
            current++;
        }

        for(int i = 0; i < yeastFlavors.length; i++) {
            donutItems.add(new DonutItem(yeastFlavors[i], itemImages[current], DonutType.YEAST.price(), 0));
            current++;
        }
    }

    /**
     * Interface method that updates total when donut quantity incremented
     * @param position
     * @param quantity
     */
    @Override
    public void onIncrementBTClick(int position, int quantity) {

        double price = donutItems.get(position).getDonutPrice();
        double total = Double.valueOf(donutTotal.getText().toString());
        donutItems.get(position).setQuantity(quantity);
        total += price;
        String totalString = String.format("%.2f", total);
        donutTotal.setText(totalString);
    }

    /**
     * Interface method that updates total price when donut quantity decremented
     * @param position
     * @param quantity
     */
    @Override
    public void onDecrementBTClick(int position, int quantity) {

        double price = donutItems.get(position).getDonutPrice();
        double total = Double.valueOf(donutTotal.getText().toString());
        donutItems.get(position).setQuantity(quantity);

        total -= price;
        String totalString = String.format("%.2f", total);
        donutTotal.setText(totalString);

    }
}