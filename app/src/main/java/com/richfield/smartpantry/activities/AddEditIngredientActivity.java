package com.richfield.smartpantry.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.richfield.smartpantry.R;
import com.richfield.smartpantry.database.DatabaseHelper;
import com.richfield.smartpantry.models.PantryItem;
import com.richfield.smartpantry.utils.PreferencesHelper;

public class AddEditIngredientActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM_ID = "extra_item_id";

    private DatabaseHelper databaseHelper;
    private EditText nameInput;
    private EditText quantityInput;
    private Spinner unitSpinner;
    private EditText expiryInput;
    private long itemId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        databaseHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        nameInput = findViewById(R.id.editName);
        quantityInput = findViewById(R.id.editQuantity);
        unitSpinner = findViewById(R.id.spinnerUnit);
        expiryInput = findViewById(R.id.editExpiry);
        Button saveButton = findViewById(R.id.buttonSave);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        PreferencesHelper prefs = new PreferencesHelper(this);
        String defaultUnit = prefs.getDefaultUnit();
        String[] units = getResources().getStringArray(R.array.units);
        for (int i = 0; i < units.length; i++) {
            if (units[i].equalsIgnoreCase(defaultUnit)) {
                unitSpinner.setSelection(i);
                break;
            }
        }

        if (getIntent().hasExtra(EXTRA_ITEM_ID)) {
            itemId = getIntent().getLongExtra(EXTRA_ITEM_ID, -1);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(R.string.edit_ingredient);
            }
            loadItem(itemId);
        } else if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.add_ingredient);
        }

        saveButton.setOnClickListener(v -> saveItem());
    }

    private void loadItem(long id) {
        PantryItem item = databaseHelper.getPantryItem(id);
        if (item == null) {
            return;
        }
        nameInput.setText(item.getName());
        quantityInput.setText(String.valueOf(item.getQuantity()));
        if (item.getExpiryDate() != null) {
            expiryInput.setText(item.getExpiryDate());
        }

        String[] units = getResources().getStringArray(R.array.units);
        for (int i = 0; i < units.length; i++) {
            if (units[i].equalsIgnoreCase(item.getUnit())) {
                unitSpinner.setSelection(i);
                break;
            }
        }
    }

    private void saveItem() {
        String name = nameInput.getText().toString().trim();
        String quantityText = quantityInput.getText().toString().trim();
        String unit = unitSpinner.getSelectedItem().toString();
        String expiry = expiryInput.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            nameInput.setError(getString(R.string.error_name_required));
            return;
        }

        if (TextUtils.isEmpty(quantityText)) {
            quantityInput.setError(getString(R.string.error_quantity_required));
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(quantityText);
            if (quantity <= 0) {
                quantityInput.setError(getString(R.string.error_quantity_positive));
                return;
            }
        } catch (NumberFormatException e) {
            quantityInput.setError(getString(R.string.error_quantity_invalid));
            return;
        }

        PantryItem item = new PantryItem();
        item.setName(name);
        item.setQuantity(quantity);
        item.setUnit(unit);
        item.setExpiryDate(expiry.isEmpty() ? null : expiry);

        if (itemId > 0) {
            item.setId(itemId);
            databaseHelper.updatePantryItem(item);
            Toast.makeText(this, R.string.ingredient_updated, Toast.LENGTH_SHORT).show();
        } else {
            databaseHelper.insertPantryItem(item);
            Toast.makeText(this, R.string.ingredient_added, Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
