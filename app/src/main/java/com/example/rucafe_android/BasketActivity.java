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

public class BasketActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    ArrayAdapter<MenuItem> menuItemArrayAdapter;

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
                this, R.layout.basket_list, R.id.list_content, MainActivity.itemsInOrder);

        listView.setAdapter(menuItemArrayAdapter);
        listView.setOnItemClickListener(this);
        setPriceValues();


        //TODO implement properly with order
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(emptySelection()) { //error checking case to make sure items exist in basket
                    return;
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(BasketActivity.this);
                alert.setTitle("Place Order");
                alert.setMessage("Would you like to place this order?");

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(BasketActivity.this,
                                "Order has been placed!", Toast.LENGTH_LONG).show();

                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(BasketActivity.this,
                                "Order has not been placed!", Toast.LENGTH_LONG).show();

                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

    }

    //TODO will need to adjust implementation upon completion of coffee
    /**
     * Helper method that sets all price values
     */
    private void setPriceValues() {

        double totalFromDonutActivity = OrderingDonutsActivity.totalPrice;
        double tax = NJ_TAX * totalFromDonutActivity;
        double totalOrder = tax + totalFromDonutActivity;

        String totalFromDonutActivityString = String.format("%.2f", totalFromDonutActivity);
        String taxString = String.format("%.2f", tax);
        String totalOrderString = String.format("%.2f", totalOrder);

        subTotal.setText(totalFromDonutActivityString);
        salesTax.setText(taxString);
        total.setText(totalOrderString);

    }

    /**
     * On item click for remove confirms if user wants to remove an item from the list or not
     * @param parent The AdapterView where the click happened.
     * @param view The view within the AdapterView that was clicked (this
     *            will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AlertDialog.Builder alert = new AlertDialog.Builder(BasketActivity.this);
        alert.setTitle("Remove Item");
        alert.setMessage("Would you like to remove this item from your basket?");

        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                OrderingDonutsActivity.totalPrice -= Double.valueOf(MainActivity.itemsInOrder.get(position).itemPrice()); //update totalDonuts to be passed
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

    /**
     * Helper method that makes sure selection is valid; menu items are in basket
     * @return true if no menu items are in basket, false otherwise
     */
    public boolean emptySelection() {

        //error checking case
        if (menuItemArrayAdapter.getCount() == 0) { //no selection has been made display error message
            AlertDialog.Builder alert = new AlertDialog.Builder(BasketActivity.this);
            alert.setTitle("RU Cafe Error");
            alert.setMessage("No items are in basket. Order can not be placed until items are added!");

            AlertDialog dialog = alert.create();
            dialog.show();

            return true;
        }
        return false;
    }
}