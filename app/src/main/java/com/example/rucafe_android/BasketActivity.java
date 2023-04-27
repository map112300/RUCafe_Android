package com.example.rucafe_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

/**
 * Basket Activity
 * Holds a list of all items added to basket
 *
 * @author Marco Pigna, Bryan Bezerra
 */
public class BasketActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ObservableArrayList<MenuItem> menuItemsInOrder;
    ArrayAdapter<MenuItem> menuItemArrayAdapter;

    private TextView subTotal, salesTax, total;
    private final double NJ_TAX = .06625;

    /**
     * Initializes the view upon creation.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        ListView listView = findViewById(R.id.order_history_items);
        Button placeOrderButton = findViewById(R.id.place_order_button);
        subTotal = findViewById(R.id.subtotal_field);
        salesTax = findViewById(R.id.sales_tax_field);
        total = findViewById(R.id.order_histroy_total);

        menuItemsInOrder = new ObservableArrayList<>();
        MenuItem[] itemArray = new MenuItem[MainActivity.currentOrder.getMenuItems().size()];
        itemArray = MainActivity.currentOrder.getMenuItems().toArray(itemArray);
        Collections.addAll(menuItemsInOrder, itemArray);

        menuItemArrayAdapter = new ArrayAdapter<>(
                this, R.layout.basket_list, R.id.list_content, menuItemsInOrder);

        listView.setAdapter(menuItemArrayAdapter);
        listView.setOnItemClickListener(this);
        setPriceValues();

        createPlaceOrderButton(placeOrderButton);
    }

    /**
     * Sets the onClick behavior for the
     * @param button
     */
    private void createPlaceOrderButton(Button button) {
        button.setOnClickListener(v -> {
            if (emptySelection()) { //error checking case to make sure items exist in basket
                return;
            }

            AlertDialog.Builder alert = new AlertDialog.Builder(BasketActivity.this);
            alert.setTitle("Place Order");
            alert.setMessage("Would you like to place this order?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    placeOrder();
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
        });
    }

    /**
     * Places current order that is in basket
     */
    private void placeOrder() {
        MainActivity.placedOrders.add(MainActivity.currentOrder);
        MainActivity.orderNumber += 1;
        MainActivity.currentOrder = new Order(MainActivity.orderNumber);
        this.menuItemsInOrder.clear();
        menuItemArrayAdapter.notifyDataSetChanged();
        setPriceValues();
    }

    /**
     * Helper method that sets all price values
     */
    private void setPriceValues() {
        double orderSubtotal = MainActivity.currentOrder.getOrderSubtotal();
        double tax = NJ_TAX * orderSubtotal;
        double totalOrder = tax + orderSubtotal;

        String subtotalString = String.format("%.2f", orderSubtotal);
        String taxString = String.format("%.2f", tax);
        String totalOrderString = String.format("%.2f", totalOrder);

        subTotal.setText(subtotalString);
        salesTax.setText(taxString);
        total.setText(totalOrderString);

    }

    /**
     * On item click for remove confirms if user wants to remove an item from the list or not
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder alert = new AlertDialog.Builder(BasketActivity.this);
        alert.setTitle("Remove Item");
        alert.setMessage("Would you like to remove this item from your basket?");

        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MenuItem menuItem = menuItemsInOrder.get(position);
                MainActivity.currentOrder.remove(menuItem);
                menuItemsInOrder.remove(position);
                setPriceValues();
                menuItemArrayAdapter.notifyDataSetChanged();
                System.out.println(MainActivity.currentOrder.getOrderSubtotal());

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
     *
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