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

/**
 * Screen that allows the user to order coffee.
 *
 * @author Marco Pigna, Bryan Bezerra
 */
public class OrderingCoffeeActivity extends AppCompatActivity {
    private CheckBox sweetCreamCheck, frenchVanillaCheck, irishCreamCheck;
    private CheckBox caramelCheck, mochaCheck;
    private Spinner sizeSpinner;
    private Spinner quantitySpinner;
    private TextInputEditText subtotalText;
    private Button addtoOrderButton;
    Integer[] quantities = {1, 2, 3, 4, 5, 6, 7, 8};
    CoffeeSize[] sizes = {CoffeeSize.SHORT, CoffeeSize.TALL, CoffeeSize.GRANDE, CoffeeSize.VENTI};
    CoffeeSize currentSize = CoffeeSize.SHORT;
    int currentQty = 1;
    Coffee currentOrder;

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
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

    /**
     * Finds the necessary layout elements and sets them to their proper variables.
     */
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

    /**
     * Sets the onClick behavior for the size Spinner.
     */
    private void createSizeSpinner() {
        this.sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSize(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Sets the onclick behavior for the quantity Spinner.
     */
    private void createQtySpinner() {
        this.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateQuantity(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Sets the onClick behavior for the Add to Order button.
     */
    private void createAddToOrderButton() {
        this.addtoOrderButton.setOnClickListener(v -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Add to order");
            alert.setMessage("Would you like to add this coffee to your order?");

            alert.setPositiveButton("yes", (dialog, which) -> {
                placeCoffeeOrderInBasket();
                Toast.makeText(this,
                        "Coffee has been added to basket!", Toast.LENGTH_LONG).show();

            }).setNegativeButton("no", (dialog, which) -> Toast.makeText(this,
                    "Coffee has not been added to basket!", Toast.LENGTH_LONG).show());
            AlertDialog dialog = alert.create();
            dialog.show();
        });
    }

    /**
     * Places the current Coffee order in the basket.
     */
    private void placeCoffeeOrderInBasket() {
        MainActivity.currentOrder.add(this.currentOrder);
        resetFields();
    }

    /**
     * Resets all fields to their default values.
     */
    private void resetFields() {
        sweetCreamCheck.setChecked(false);
        frenchVanillaCheck.setChecked(false);
        irishCreamCheck.setChecked(false);
        caramelCheck.setChecked(false);
        mochaCheck.setChecked(false);
        currentQty = quantities[0];
        currentSize = sizes[0];
        sizeSpinner.setSelection(0);
        quantitySpinner.setSelection(0);
        readCurrentOrder();
    }

    /**
     * Sets the onClick behavior for all checkboxes in the activity.
     */
    private void createAddonCheckBoxes() {
        createAddonCheckbox(sweetCreamCheck);
        createAddonCheckbox(frenchVanillaCheck);
        createAddonCheckbox(irishCreamCheck);
        createAddonCheckbox(caramelCheck);
        createAddonCheckbox(mochaCheck);
    }

    /**
     * Sets the onClick behavior for one checkbox.
     *
     * @param box the Checkbox whose behavior will be modified
     */
    private void createAddonCheckbox(CheckBox box) {
        box.setOnCheckedChangeListener((buttonView, isChecked) -> readCurrentOrder());
    }

    /**
     * Updates the current order's size.
     *
     * @param sizeIndex the index of the new size
     */
    private void updateSize(int sizeIndex) {
        this.currentSize = sizes[sizeIndex];
        readCurrentOrder();
    }

    /**
     * Updates the current order's quantity.
     *
     * @param quantityIndex the index of the new quantity
     */
    private void updateQuantity(int quantityIndex) {
        this.currentQty = quantities[quantityIndex];
        readCurrentOrder();
    }

    /**
     * Reads the user's input and updates the current order.
     */
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

    /**
     * Updates the subtotal to reflect the current order.
     */
    private void updateSubtotal() {
        double subtotal = this.currentOrder.itemPrice();
        String subtotalString = String.format("$%.2f", subtotal);
        this.subtotalText.setText(subtotalString);
    }
}