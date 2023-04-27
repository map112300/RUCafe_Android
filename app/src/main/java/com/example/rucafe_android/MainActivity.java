package com.example.rucafe_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Main Activity of RU Cafe
 * Creates intent for necessary activities
 *
 * @author Marco Pigna, Bryan Bezerra
 */
public class MainActivity extends AppCompatActivity {
    public static final ArrayList<Order> placedOrders = new ArrayList<>();
    public static int orderNumber = 1;
    public static Order currentOrder = new Order(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        for (Order o : placedOrders) {
            System.out.println(o);
        }
        //xml ID variables
        ImageButton basketBT = (ImageButton) findViewById(R.id.basket_Button);
        ImageButton orderBT = (ImageButton) findViewById(R.id.order_Button);
        ImageButton donutBT = (ImageButton) findViewById(R.id.donut_Button);
        ImageButton coffeeBT = (ImageButton) findViewById(R.id.coffee_Button);

        createButton(BasketActivity.class, basketBT);
        createButton(StoreOrdersActivity.class, orderBT);
        createButton(OrderingDonutsActivity.class, donutBT);
        createButton(OrderingCoffeeActivity.class, coffeeBT);
    }


    private void createButton(Class targetClass, ImageButton button) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, targetClass);
            startActivity(intent);
        });
    }
}