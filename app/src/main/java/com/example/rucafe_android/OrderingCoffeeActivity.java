package com.example.rucafe_android;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.os.Bundle;

public class OrderingCoffeeActivity extends AppCompatActivity {
    private Spinner sizeSpinner;
    private Spinner quantitySpinner;
    private ArrayAdapter<CharSequence> sizeSpinnerAdapter;
    private ArrayAdapter<CharSequence> quantitySpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_coffee);

        this.sizeSpinner = findViewById(R.id.coffee_size_spinner);
        this.sizeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.coffee_sizes, android.R.layout.simple_spinner_dropdown_item
        );
        sizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeSpinnerAdapter);

        this.quantitySpinner = findViewById(R.id.coffee_quantity_spinner);
        this.quantitySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.quantities, android.R.layout.simple_spinner_dropdown_item
        );
        quantitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantitySpinnerAdapter);
    }
}