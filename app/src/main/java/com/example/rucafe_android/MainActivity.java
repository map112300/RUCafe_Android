package com.example.rucafe_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton basketBT;
    private ImageButton orderBT;
    private ImageButton donutBT;
    private ImageButton coffeeBT;

    private Button toastBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        //set all imageButton variables accordingly
        basketBT = (ImageButton) findViewById(R.id.basket_Button);
        orderBT = (ImageButton) findViewById(R.id.order_Button);
        donutBT = (ImageButton) findViewById(R.id.donut_Button);
        coffeeBT = (ImageButton) findViewById(R.id.coffee_Button);

        basketBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBasket = new Intent(MainActivity.this, BasketActivity.class);
                startActivity(intentBasket);
            }
        });

        orderBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOrder = new Intent(MainActivity.this, StoreOrdersActivity.class);
                startActivity(intentOrder);
            }
        });

        donutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDonut = new Intent(MainActivity.this, OrderingDonutsActivity.class);
                startActivity(intentDonut);
            }
        });

        coffeeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCoffee = new Intent(MainActivity.this, OrderingCoffeeActivity.class);
                startActivity(intentCoffee);
            }
        });
    }
}