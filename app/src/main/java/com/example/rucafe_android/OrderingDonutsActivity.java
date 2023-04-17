package com.example.rucafe_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderingDonutsActivity extends AppCompatActivity {

    private Button addToBasket;
    private TextView donutTotal;

    private ArrayList <DonutItem> donutItems = new ArrayList<>();

    //TODO update with images once uploaded
    private int[] itemImages = {R.drawable.blueberry_cake, R.drawable.coffee_cake, R.drawable.oldfashioned_cake
            , R.drawable.chocolate_hole, R.drawable.glazed_hole, R.drawable.jelly_hole
            , R.drawable.cinnamon_hole, R.drawable.chocolate_yeast, R.drawable.strawberry_yeast
            , R.drawable.vanilla_yeast, R.drawable.boston_yeast, R.drawable.powdered_yeast
            , R.drawable.double_yeast};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_donuts);
        RecyclerView recycler = findViewById(R.id.rcView);
        setupDonuts();
        DonutItemHolder donutHolder = new DonutItemHolder(this, donutItems);
        recycler.setAdapter(donutHolder);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        addToBasket = (Button) findViewById(R.id.add_to_BasketBT);
        donutTotal = findViewById(R.id.donut_total_Field);

    }

    //TODO update images + prices for items
    private void setupDonuts() {

        String[] cakeFlavors = getResources().getStringArray(R.array.cake_flavors);
        String[] holeFlavors = getResources().getStringArray(R.array.hole_flavors);
        String[] yeastFlavors = getResources().getStringArray(R.array.yeast_flavors);

        int current = 0; //will be used for setting images
        for(int i = 0; i < cakeFlavors.length; i++) {
            donutItems.add(new DonutItem(cakeFlavors[i], itemImages[current], DonutType.CAKE.price()));
            current++;
        }

        for(int i = 0; i < holeFlavors.length; i++) {
            donutItems.add(new DonutItem(holeFlavors[i], itemImages[current], DonutType.HOLE.price()));
            current++;
        }

        for(int i = 0; i < yeastFlavors.length; i++) {
            donutItems.add(new DonutItem(yeastFlavors[i], itemImages[current], DonutType.YEAST.price()));
            current++;
        }
    }
}