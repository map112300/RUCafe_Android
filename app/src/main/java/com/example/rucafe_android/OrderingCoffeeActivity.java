package com.example.rucafe_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class OrderingCoffeeActivity extends AppCompatActivity {
    private CheckBox sweetCreamCheck, frenchVanillaCheck, irishCreamCheck;
    private CheckBox caramelCheck, mochaCheck;
    private Spinner sizeSpinner;
    private Spinner quantitySpinner;
    private TextInputEditText subtotalText;
    private Button addtoOrderButton;
    Integer[] quantities = {1,2,3,4,5,6,7,8};
    CoffeeSize[] sizes = {CoffeeSize.SHORT, CoffeeSize.TALL, CoffeeSize.GRANDE, CoffeeSize.VENTI};
    CoffeeSize currentSize = CoffeeSize.SHORT;
    int currentQty = 1;
    Coffee currentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_coffee);
        initializeLayoutElements();
        createAddonCheckBoxes();

        ArrayAdapter<CoffeeSize> sizeSpinnerAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sizes);
        sizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeSpinnerAdapter);
        createSizeSpinner();

        ArrayAdapter<Integer> quantitySpinnerAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantities);
        quantitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantitySpinnerAdapter);
        createQtySpinner();
        createAddToOrderButton();

        readCurrentOrder();
    }

    private void initializeLayoutElements() {
        this.sweetCreamCheck = findViewById(R.id.sweet_cream_check);
        this.frenchVanillaCheck = findViewById(R.id.french_vanilla_check);
        this.irishCreamCheck = findViewById(R.id.irish_cream_check);
        this.caramelCheck = findViewById(R.id.caramel_check);
        this.mochaCheck = findViewById(R.id.mocha_check);
        this.sizeSpinner = findViewById(R.id.coffee_size_spinner);
        this.quantitySpinner = findViewById(R.id.coffee_quantity_spinner);
        this.subtotalText = findViewById(R.id.coffee_subtotal);
        this.addtoOrderButton = findViewById(R.id.coffee_add_to_order_button);
    }

    private void createSizeSpinner() {
        this.sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSize(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void createQtySpinner() {
        this.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateQuantity(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void createAddToOrderButton() {
        this.addtoOrderButton.setOnClickListener(v -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Add to order");
            alert.setMessage("Would you like to add this coffee to your order?");

            alert.setPositiveButton("yes", (dialog, which) -> {
                placeDonutOrderInBasket();
                Toast.makeText(this,
                        "Coffee has been added to basket!", Toast.LENGTH_LONG).show();

            }).setNegativeButton("no", (dialog, which) -> Toast.makeText(this,
                    "Coffee has not been added to basket!", Toast.LENGTH_LONG).show());
            AlertDialog dialog = alert.create();
            dialog.show();
        });
    }

    private void placeDonutOrderInBasket() {
        MainActivity.currentOrder.add(this.currentOrder);
        resetFields();
    }

    private void resetFields() {
        sweetCreamCheck.setChecked(false);
        frenchVanillaCheck.setChecked(false);
        irishCreamCheck.setChecked(false);
        caramelCheck.setChecked(false);
        mochaCheck.setChecked(false);
        currentQty = quantities[0];
        currentSize = sizes[0];
        readCurrentOrder();
    }

    private void createAddonCheckBoxes() {
        createAddonCheckbox(sweetCreamCheck);
        createAddonCheckbox(frenchVanillaCheck);
        createAddonCheckbox(irishCreamCheck);
        createAddonCheckbox(caramelCheck);
        createAddonCheckbox(mochaCheck);
    }

    private void createAddonCheckbox(CheckBox box) {
        box.setOnCheckedChangeListener((buttonView, isChecked) -> readCurrentOrder());
    }

    private void updateSize(int sizeIndex) {
        this.currentSize = sizes[sizeIndex];
        readCurrentOrder();
    }

    private void updateQuantity(int quantityIndex) {
        this.currentQty = quantities[quantityIndex];
        readCurrentOrder();
    }

    private void readCurrentOrder() {
        ArrayList<CoffeeAddon> addons = new ArrayList<>();
        if (sweetCreamCheck.isChecked()) addons.add(CoffeeAddon.SWEET_CREAM);
        if (frenchVanillaCheck.isChecked()) addons.add(CoffeeAddon.F_VANILLA);
        if (irishCreamCheck.isChecked()) addons.add(CoffeeAddon.IRISH_CREAM);
        if (mochaCheck.isChecked()) addons.add(CoffeeAddon.MOCHA);
        if (caramelCheck.isChecked()) addons.add(CoffeeAddon.CARAMEL);
        this.currentOrder = new Coffee(this.currentSize, this.currentQty, addons);
        updateSubtotal();
    }

    private void updateSubtotal() {
        double subtotal = this.currentOrder.itemPrice();
        String subtotalString =  String.format("$%.2f", subtotal);
        this.subtotalText.setText(subtotalString);
    }
}