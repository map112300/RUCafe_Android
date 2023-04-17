package com.example.rucafe_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class OrderingDonutsActivity extends AppCompatActivity {

    private ArrayList <DonutItem> donutItems = new ArrayList<>();
    private int[] itemImages = {R.drawable.donut_appicon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_donuts);
        RecyclerView recycler = findViewById(R.id.rcView);
        setupDonuts();
        DonutItemHolder donutHolder = new DonutItemHolder(this, donutItems);
        recycler.setAdapter(donutHolder);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupDonuts() {

        String[] donutFlavors = getResources().getStringArray(R.array.donut_flavors);

        for(int i = 0; i < donutFlavors.length; i++) {
            donutItems.add(new DonutItem(donutFlavors[i], itemImages[0], 9.99));
        }
    }
}