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

import com.richfield.smartpantry.R;
import com.richfield.smartpantry.adapters.RecipeAdapter;
import com.richfield.smartpantry.database.DatabaseHelper;
import com.richfield.smartpantry.models.PantryItem;
import com.richfield.smartpantry.models.Recipe;
import com.richfield.smartpantry.utils.IngredientMatcher;

import java.util.List;

public class SuggestedRecipesFragment extends Fragment implements RecipeAdapter.RecipeListener {

    private DatabaseHelper databaseHelper;
    private RecipeAdapter adapter;
    private TextView emptyText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suggested_recipes, container, false);

        databaseHelper = new DatabaseHelper(requireContext());
        emptyText = view.findViewById(R.id.textEmptyRecipes);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerRecipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new RecipeAdapter(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {
        List<PantryItem> pantryItems = databaseHelper.getAllPantryItems();
        List<Recipe> allRecipes = databaseHelper.getAllRecipes();
        List<Recipe> suggested = IngredientMatcher.getSuggestedRecipes(allRecipes, pantryItems);

        adapter.setRecipes(suggested);
        emptyText.setVisibility(suggested.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        Intent intent = new Intent(requireContext(), RecipeDetailActivity.class);
        intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE_ID, recipe.getId());
        startActivity(intent);
    }
}
