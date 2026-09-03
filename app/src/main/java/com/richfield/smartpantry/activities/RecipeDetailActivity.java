package com.richfield.smartpantry.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.richfield.smartpantry.R;
import com.richfield.smartpantry.database.DatabaseHelper;
import com.richfield.smartpantry.models.Recipe;
import com.richfield.smartpantry.models.RecipeIngredient;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_ID = "extra_recipe_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        long recipeId = getIntent().getLongExtra(EXTRA_RECIPE_ID, -1);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Recipe recipe = databaseHelper.getRecipe(recipeId);

        if (recipe == null) {
            finish();
            return;
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(recipe.getName());
        }

        TextView ingredientsText = findViewById(R.id.textIngredients);
        TextView stepsText = findViewById(R.id.textSteps);

        StringBuilder ingredientsBuilder = new StringBuilder();
        for (RecipeIngredient ingredient : recipe.getIngredients()) {
            ingredientsBuilder.append("• ")
                    .append(ingredient.getQuantity())
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append(" ")
                    .append(ingredient.getName())
                    .append("\n");
        }
        ingredientsText.setText(ingredientsBuilder.toString().trim());
        stepsText.setText(recipe.getSteps());
    }
}
