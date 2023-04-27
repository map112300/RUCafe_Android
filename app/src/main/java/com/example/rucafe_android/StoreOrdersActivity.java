package com.example.rucafe_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Collections;

public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final double NJ_TAX_MULTIPLIER = 1.06625;
    private Order currentOrder;
    private ListView listView;
    private Spinner orderNumSpinner;
    private TextView totalSum;
    private Button removeOrderButton;
    private ObservableArrayList<MenuItem> menuItemsInOrder;
    private ObservableArrayList<CharSequence> orderNums;
    private ArrayAdapter<CharSequence> orderNumAdapter;
    private ArrayAdapter<MenuItem> menuItemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        this.removeOrderButton = findViewById(R.id.order_history_remove_button);
        this.listView = findViewById(R.id.order_history_items);
        this.orderNumSpinner = findViewById(R.id.order_history_spinner);
        this.totalSum = findViewById(R.id.order_histroy_total);
        createRemoveOrderButton(removeOrderButton);
        if (MainActivity.placedOrders.size() > 0) {
            this.currentOrder = MainActivity.placedOrders.get(0);

            //Order number Spinner
            this.orderNums = new ObservableArrayList<>();
            for (Order order : MainActivity.placedOrders) {
                orderNums.add(Integer.toString(order.getOrderNumber()));
            }
            this.orderNumAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, orderNums);
            orderNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            orderNumSpinner.setAdapter(orderNumAdapter);
            orderNumSpinner.setOnItemSelectedListener(this);

            //ListView showing items in the order
            menuItemsInOrder = new ObservableArrayList<>();
            MenuItem[] itemArray = new MenuItem[currentOrder.getMenuItems().size()];
            itemArray = currentOrder.getMenuItems().toArray(itemArray);
            Collections.addAll(menuItemsInOrder, itemArray);
            menuItemArrayAdapter = new ArrayAdapter<>(this,
                    R.layout.basket_list, R.id.list_content, menuItemsInOrder
            );
            listView.setAdapter(menuItemArrayAdapter);
            updateTotalPrice();
        }
    }

    private void createRemoveOrderButton(Button button) {
        button.setOnClickListener(v -> {
            MainActivity.placedOrders.remove(this.currentOrder);
            this.currentOrder = null;
            if (MainActivity.placedOrders.size() > 0) {
                this.currentOrder = MainActivity.placedOrders.get(0);
                updateItemList();
            } else {
                menuItemsInOrder.clear();
                menuItemArrayAdapter.notifyDataSetChanged();
                updateTotalPrice();
            }
            updateOrderNumList();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int orderNum = Integer.parseInt(orderNums.get(position).toString());
        for (Order order : MainActivity.placedOrders) {
            if (order.getOrderNumber() == orderNum) {
                this.currentOrder = order;
                break;
            }
        }
        updateItemList();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){}

    private void updateItemList() {
        menuItemsInOrder.clear();
        MenuItem[] itemArray = new MenuItem[currentOrder.getMenuItems().size()];
        itemArray = currentOrder.getMenuItems().toArray(itemArray);
        Collections.addAll(menuItemsInOrder, itemArray);
        menuItemArrayAdapter.notifyDataSetChanged();
        updateTotalPrice();
    }

    private void updateOrderNumList() {
        this.orderNums.clear();
        for (Order order : MainActivity.placedOrders) {
            orderNums.add(Integer.toString(order.getOrderNumber()));
        }
        this.orderNumAdapter.notifyDataSetChanged();
    }

    private void updateTotalPrice() {
        Double total;
        if (this.currentOrder != null) {
            total = currentOrder.getOrderSubtotal() * NJ_TAX_MULTIPLIER;
        } else {
            total = 0.0;
        }
        String totalString = String.format("%.2f", total);
        totalSum.setText(totalString);
    }
}