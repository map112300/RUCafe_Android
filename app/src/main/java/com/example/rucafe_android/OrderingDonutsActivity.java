package com.example.rucafe_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class OrderingDonutsActivity extends AppCompatActivity implements RecyclerViewClickInterface {

    public static double totalDonuts = 0.00;
    private RecyclerView recycler;
    private Button addToBasket;
    private TextView donutTotal;
    private ArrayList <Donut> donutItems = new ArrayList<>();
    private DonutAdapter donutHolder;

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
        donutHolder = new DonutAdapter(this, donutItems, this);
        recycler.setAdapter(donutHolder);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        addToBasket = (Button) findViewById(R.id.add_to_BasketBT);
        donutTotal = findViewById(R.id.donut_total_Field);

        addToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(OrderingDonutsActivity.this);
                alert.setTitle("Add to order");
                alert.setMessage("Would you like to add these donuts to your order?");

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        totalDonuts += Double.valueOf(donutTotal.getText().toString()); //update totalDonuts to be passed
                        placeDonutOrder();

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
            donutItems.add(new Donut(DonutType.CAKE, 0, cakeFlavors[i],  itemImages[current]));
            current++;
        }

        for(int i = 0; i < holeFlavors.length; i++) {
            donutItems.add(new Donut(DonutType.HOLE,  0, holeFlavors[i],  itemImages[current]));
            current++;
        }

        for(int i = 0; i < yeastFlavors.length; i++) {
            donutItems.add(new Donut(DonutType.YEAST,  0, yeastFlavors[i], itemImages[current]));
            current++;
        }
    }

    /**
     * Interface method that updates total when donut quantity incremented
     * @param position
     *
     */
    @Override
    public void onIncrementBTClick(int position) {

        double price = donutItems.get(position).getDonutPrice();
        double total = Double.valueOf(donutTotal.getText().toString());
        int quantity = donutItems.get(position).getQuantity();
        quantity += 1;
        donutHolder.notifyDataSetChanged();
        donutItems.get(position).setQuantity(quantity);
        total += price;
        String totalString = String.format("%.2f", total);
        donutTotal.setText(totalString);
    }

    /**
     * Interface method that updates total price when donut quantity decremented
     * @param position
     *
     */
    @Override
    public void onDecrementBTClick(int position) {

        double price = donutItems.get(position).getDonutPrice();
        double total = Double.valueOf(donutTotal.getText().toString());
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


    //TODO NEED TO IMPLEMENT BETTER TO INCORPORATE ORDER
    /**
     * Method that iterates through recycler view to create an arraylist of Donuts to add to basket
     * @return
     */
    public void placeDonutOrder() {

        for(int i = 0; i < donutItems.size(); i++) {
            if(donutItems.get(i).getQuantity() > 0) {
                Collections.addAll(MainActivity.itemsInOrder, donutItems.get(i));
            }
        }
        clearAllFields();
    }
}