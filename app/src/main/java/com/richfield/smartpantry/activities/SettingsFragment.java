package com.richfield.smartpantry.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.richfield.smartpantry.R;
import com.richfield.smartpantry.utils.PreferencesHelper;

public class SettingsFragment extends Fragment {

    private PreferencesHelper preferencesHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        preferencesHelper = new PreferencesHelper(requireContext());

        SwitchMaterial expirySwitch = view.findViewById(R.id.switchExpiryAlerts);
        Spinner unitSpinner = view.findViewById(R.id.spinnerDefaultUnit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(), R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        expirySwitch.setChecked(preferencesHelper.isExpiryAlertsEnabled());
        expirySwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
                preferencesHelper.setExpiryAlertsEnabled(isChecked));

        String defaultUnit = preferencesHelper.getDefaultUnit();
        String[] units = getResources().getStringArray(R.array.units);
        for (int i = 0; i < units.length; i++) {
            if (units[i].equalsIgnoreCase(defaultUnit)) {
                unitSpinner.setSelection(i);
                break;
            }
        }

        unitSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                preferencesHelper.setDefaultUnit(units[position]);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });

        return view;
    }
}
