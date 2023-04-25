package com.example.rucafe_android;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.os.Bundle;

public class OrderingCoffeeActivity extends AppCompatActivity {
    private Spinner sizeSpinner;
    private ArrayAdapter<CharSequence> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_coffee);
        this.sizeSpinner = findViewById(R.id.coffee_size_spinner);
        this.spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.coffee_sizes, android.R.layout.simple_spinner_dropdown_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(spinnerAdapter);
    }
}