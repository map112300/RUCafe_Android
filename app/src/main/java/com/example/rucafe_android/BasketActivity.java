package com.example.rucafe_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class BasketActivity extends AppCompatActivity {

    private ListView listView;

    private Button remove, placeOrder;

    private TextView subTotal, salesTax, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        listView = findViewById(R.id.items_listview);
        remove = findViewById(R.id.remove_button);
        placeOrder = findViewById(R.id.place_order_button);
        subTotal = findViewById(R.id.subtotal_field);
        salesTax = findViewById(R.id.sales_tax_field);
        total = findViewById(R.id.total_field);


    }
}