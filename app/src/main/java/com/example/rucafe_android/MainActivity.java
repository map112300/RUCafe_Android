package com.example.rucafe_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main_view);
        setContentView(R.layout.ordering_donuts_view);
        //setContentView(R.layout.ordering_basket_view);
    }
}