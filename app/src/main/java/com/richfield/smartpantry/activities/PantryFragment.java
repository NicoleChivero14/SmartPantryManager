package com.richfield.smartpantry.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.richfield.smartpantry.R;
import com.richfield.smartpantry.adapters.PantryAdapter;
import com.richfield.smartpantry.database.DatabaseHelper;
import com.richfield.smartpantry.models.PantryItem;

import java.util.List;

public class PantryFragment extends Fragment implements PantryAdapter.PantryListener {

    private DatabaseHelper databaseHelper;
    private PantryAdapter adapter;
    private TextView emptyText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantry, container, false);

        databaseHelper = new DatabaseHelper(requireContext());
        emptyText = view.findViewById(R.id.textEmptyPantry);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerPantry);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new PantryAdapter(this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fabAddIngredient);
        fab.setOnClickListener(v -> openAddEditScreen(-1));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPantryItems();
    }

    private void loadPantryItems() {
        List<PantryItem> items = databaseHelper.getAllPantryItems();
        adapter.setItems(items);
        emptyText.setVisibility(items.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private void openAddEditScreen(long itemId) {
        Intent intent = new Intent(requireContext(), AddEditIngredientActivity.class);
        if (itemId > 0) {
            intent.putExtra(AddEditIngredientActivity.EXTRA_ITEM_ID, itemId);
        }
        startActivity(intent);
    }

    @Override
    public void onEdit(PantryItem item) {
        openAddEditScreen(item.getId());
    }

    @Override
    public void onDelete(PantryItem item) {
        databaseHelper.deletePantryItem(item.getId());
        loadPantryItems();
    }
}
