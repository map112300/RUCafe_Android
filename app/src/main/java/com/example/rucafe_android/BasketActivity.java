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

    private final double NJ_TAX = .06625;

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

        setPriceValues();

    }


    //TODO will need to adjust implementation upon completion of coffee
    /**
     * Helper method that sets all price views
     */
    private void setPriceValues() {

        double totalFromDonutActivity = OrderingDonutsActivity.totalDonuts;
        double tax = NJ_TAX * totalFromDonutActivity;
        double totalOrder = tax + totalFromDonutActivity;

        String totalFromDonutActivityString = String.format("%.2f", totalFromDonutActivity);
        String taxString = String.format("%.2f", tax);
        String totalOrderString = String.format("%.2f", totalOrder);

        subTotal.setText(totalFromDonutActivityString);
        salesTax.setText(taxString);
        total.setText(totalOrderString);

    }
}


