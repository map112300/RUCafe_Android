package com.example.rucafe_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    ArrayAdapter<MenuItem> menuItemArrayAdapter;

    //TODO get rid of remove button make it so click can remove with alert
    private Button placeOrder;
    private TextView subTotal, salesTax, total;
    private final double NJ_TAX = .06625;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        listView = findViewById(R.id.items_listview);
        placeOrder = findViewById(R.id.place_order_button);
        subTotal = findViewById(R.id.subtotal_field);
        salesTax = findViewById(R.id.sales_tax_field);
        total = findViewById(R.id.total_field);


        menuItemArrayAdapter = new ArrayAdapter<MenuItem>(
                this, android.R.layout.simple_list_item_1, MainActivity.itemsInOrder);

        listView.setAdapter(menuItemArrayAdapter);
        listView.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AlertDialog.Builder alert = new AlertDialog.Builder(BasketActivity.this);
        alert.setTitle("Remove Item");
        alert.setMessage("Would you like to remove this item from your basket?");

        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                OrderingDonutsActivity.totalDonuts -= Double.valueOf(MainActivity.itemsInOrder.get(position).itemPrice()); //update totalDonuts to be passed
                setPriceValues();
                MainActivity.itemsInOrder.remove(position);
                menuItemArrayAdapter.notifyDataSetChanged();

                Toast.makeText(BasketActivity.this,
                        "Item has been removed from basket!", Toast.LENGTH_LONG).show();

            }
            //handle the "NO" click
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(BasketActivity.this,
                        "Item has not been removed!", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}