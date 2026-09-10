package com.richfield.smartpantry.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Pre-loads the recipe collection the first time the SQLite database is created.
 * Provides 18 recipes so Suggested Recipes can match against the user's pantry.
 */
public class RecipeSeeder {

    private RecipeSeeder() {
    }

    /** Insert all starter recipes and their required ingredients. */
    public static void seedRecipes(SQLiteDatabase db) {
        seedRecipe(db, "Scrambled Eggs",
                "1. Beat eggs in a bowl.\n2. Melt butter in a pan.\n3. Pour eggs and stir gently until set.\n4. Season and serve.",
                new String[][]{
                        {"egg", "3", "piece"},
                        {"butter", "1", "tablespoon"},
                        {"salt", "1", "pinch"}
                });

        seedRecipe(db, "Tomato Pasta",
                "1. Boil pasta until al dente.\n2. Sauté garlic in olive oil.\n3. Add tomatoes and simmer.\n4. Toss with pasta and serve.",
                new String[][]{
                        {"pasta", "200", "gram"},
                        {"tomato", "3", "piece"},
                        {"garlic", "2", "clove"},
                        {"olive oil", "2", "tablespoon"}
                });

        seedRecipe(db, "Grilled Cheese Sandwich",
                "1. Butter bread slices.\n2. Add cheese between slices.\n3. Grill until golden.\n4. Serve hot.",
                new String[][]{
                        {"bread", "2", "piece"},
                        {"cheese", "2", "piece"},
                        {"butter", "1", "tablespoon"}
                });

        seedRecipe(db, "Vegetable Stir Fry",
                "1. Heat oil in a wok.\n2. Add vegetables and stir fry.\n3. Season with soy sauce.\n4. Serve with rice.",
                new String[][]{
                        {"rice", "1", "cup"},
                        {"carrot", "1", "piece"},
                        {"onion", "1", "piece"},
                        {"soy sauce", "2", "tablespoon"},
                        {"vegetable oil", "1", "tablespoon"}
                });

        seedRecipe(db, "Chicken Curry",
                "1. Brown chicken pieces.\n2. Add onion and curry powder.\n3. Simmer with coconut milk.\n4. Serve with rice.",
                new String[][]{
                        {"chicken", "300", "gram"},
                        {"onion", "1", "piece"},
                        {"curry powder", "2", "tablespoon"},
                        {"coconut milk", "200", "millilitre"},
                        {"rice", "1", "cup"}
                });

        seedRecipe(db, "Tuna Salad",
                "1. Drain tuna.\n2. Mix with mayonnaise and chopped celery.\n3. Season and chill.\n4. Serve on lettuce.",
                new String[][]{
                        {"tuna", "1", "piece"},
                        {"mayonnaise", "2", "tablespoon"},
                        {"celery", "2", "piece"},
                        {"lettuce", "4", "piece"}
                });

        seedRecipe(db, "Pancakes",
                "1. Mix flour, milk, egg, and sugar.\n2. Heat a non-stick pan.\n3. Pour batter and flip when bubbles form.\n4. Serve with syrup.",
                new String[][]{
                        {"flour", "1", "cup"},
                        {"milk", "1", "cup"},
                        {"egg", "1", "piece"},
                        {"sugar", "2", "tablespoon"},
                        {"butter", "2", "tablespoon"}
                });

        seedRecipe(db, "Beef Mince Tacos",
                "1. Brown mince with taco seasoning.\n2. Warm tortillas.\n3. Fill with mince, lettuce, and cheese.\n4. Serve.",
                new String[][]{
                        {"beef mince", "250", "gram"},
                        {"tortilla", "4", "piece"},
                        {"lettuce", "2", "piece"},
                        {"cheese", "1", "cup"},
                        {"taco seasoning", "1", "tablespoon"}
                });

        seedRecipe(db, "Mushroom Omelette",
                "1. Sauté mushrooms.\n2. Beat eggs and pour into pan.\n3. Add mushrooms and fold.\n4. Serve.",
                new String[][]{
                        {"egg", "2", "piece"},
                        {"mushroom", "100", "gram"},
                        {"butter", "1", "tablespoon"},
                        {"salt", "1", "pinch"}
                });

        seedRecipe(db, "Tomato Soup",
                "1. Sauté onion and garlic.\n2. Add tomatoes and stock.\n3. Simmer and blend.\n4. Season and serve.",
                new String[][]{
                        {"tomato", "4", "piece"},
                        {"onion", "1", "piece"},
                        {"garlic", "2", "clove"},
                        {"vegetable stock", "500", "millilitre"},
                        {"cream", "2", "tablespoon"}
                });

        seedRecipe(db, "Avocado Toast",
                "1. Toast bread.\n2. Mash avocado with lemon juice.\n3. Spread on toast.\n4. Season with salt and pepper.",
                new String[][]{
                        {"bread", "2", "piece"},
                        {"avocado", "1", "piece"},
                        {"lemon", "0.5", "piece"},
                        {"salt", "1", "pinch"}
                });

        seedRecipe(db, "Fried Rice",
                "1. Cook rice and cool.\n2. Scramble eggs in a wok.\n3. Add rice, soy sauce, and peas.\n4. Stir fry and serve.",
                new String[][]{
                        {"rice", "2", "cup"},
                        {"egg", "2", "piece"},
                        {"soy sauce", "2", "tablespoon"},
                        {"peas", "0.5", "cup"},
                        {"vegetable oil", "1", "tablespoon"}
                });

        seedRecipe(db, "Baked Potato",
                "1. Pierce potato and bake until soft.\n2. Split open and add butter.\n3. Top with cheese.\n4. Serve.",
                new String[][]{
                        {"potato", "2", "piece"},
                        {"butter", "2", "tablespoon"},
                        {"cheese", "0.5", "cup"},
                        {"salt", "1", "pinch"}
                });

        seedRecipe(db, "Chicken Salad Wrap",
                "1. Mix cooked chicken with mayonnaise.\n2. Add lettuce and tomato.\n3. Wrap in tortilla.\n4. Serve.",
                new String[][]{
                        {"chicken", "200", "gram"},
                        {"tortilla", "2", "piece"},
                        {"lettuce", "2", "piece"},
                        {"tomato", "1", "piece"},
                        {"mayonnaise", "2", "tablespoon"}
                });

        seedRecipe(db, "Banana Smoothie",
                "1. Blend banana, milk, and honey.\n2. Add ice if desired.\n3. Pour into a glass.\n4. Serve immediately.",
                new String[][]{
                        {"banana", "2", "piece"},
                        {"milk", "1", "cup"},
                        {"honey", "1", "tablespoon"}
                });

        seedRecipe(db, "Garlic Bread",
                "1. Mix butter with minced garlic.\n2. Spread on bread halves.\n3. Bake until golden.\n4. Serve warm.",
                new String[][]{
                        {"bread", "1", "piece"},
                        {"butter", "3", "tablespoon"},
                        {"garlic", "3", "clove"}
                });

        seedRecipe(db, "Vegetable Soup",
                "1. Sauté onion, carrot, and celery.\n2. Add stock and potatoes.\n3. Simmer until tender.\n4. Season and serve.",
                new String[][]{
                        {"onion", "1", "piece"},
                        {"carrot", "2", "piece"},
                        {"celery", "2", "piece"},
                        {"potato", "2", "piece"},
                        {"vegetable stock", "1", "litre"}
                });

        seedRecipe(db, "Cheese Quesadilla",
                "1. Place cheese on a tortilla.\n2. Top with second tortilla.\n3. Cook in a dry pan until melted.\n4. Cut and serve.",
                new String[][]{
                        {"tortilla", "2", "piece"},
                        {"cheese", "1", "cup"},
                        {"butter", "1", "tablespoon"}
                });

        seedRecipe(db, "Peanut Butter Sandwich",
                "1. Spread peanut butter on bread.\n2. Add jam if desired.\n3. Close sandwich.\n4. Serve.",
                new String[][]{
                        {"bread", "2", "piece"},
                        {"peanut butter", "2", "tablespoon"},
                        {"jam", "1", "tablespoon"}
                });
    }

    /** Helper: insert one recipe row, then insert each of its ingredients. */
    private static void seedRecipe(SQLiteDatabase db, String name, String steps, String[][] ingredients) {
        ContentValues recipeValues = new ContentValues();
        recipeValues.put("name", name);
        recipeValues.put("steps", steps);
        long recipeId = db.insert(DatabaseHelper.TABLE_RECIPES, null, recipeValues);

        for (String[] ingredient : ingredients) {
            ContentValues ingredientValues = new ContentValues();
            ingredientValues.put("recipe_id", recipeId);
            ingredientValues.put("name", ingredient[0]);
            ingredientValues.put("quantity", Double.parseDouble(ingredient[1]));
            ingredientValues.put("unit", ingredient[2]);
            db.insert(DatabaseHelper.TABLE_RECIPE_INGREDIENTS, null, ingredientValues);
        }
    }
}
