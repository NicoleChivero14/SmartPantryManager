package com.richfield.smartpantry.utils;

import com.richfield.smartpantry.models.PantryItem;
import com.richfield.smartpantry.models.Recipe;
import com.richfield.smartpantry.models.RecipeIngredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Strict-matching engine: a recipe is suggested only when every required ingredient
 * is present in the pantry in at least the required quantity.
 */
public class IngredientMatcher {

    private static final Map<String, String> UNIT_ALIASES = new HashMap<>();

    static {
        UNIT_ALIASES.put("g", "gram");
        UNIT_ALIASES.put("grams", "gram");
        UNIT_ALIASES.put("gram", "gram");
        UNIT_ALIASES.put("kg", "kilogram");
        UNIT_ALIASES.put("kilograms", "kilogram");
        UNIT_ALIASES.put("kilogram", "kilogram");
        UNIT_ALIASES.put("ml", "millilitre");
        UNIT_ALIASES.put("millilitres", "millilitre");
        UNIT_ALIASES.put("millilitre", "millilitre");
        UNIT_ALIASES.put("l", "litre");
        UNIT_ALIASES.put("litres", "litre");
        UNIT_ALIASES.put("litre", "litre");
        UNIT_ALIASES.put("cup", "cup");
        UNIT_ALIASES.put("cups", "cup");
        UNIT_ALIASES.put("tbsp", "tablespoon");
        UNIT_ALIASES.put("tablespoon", "tablespoon");
        UNIT_ALIASES.put("tablespoons", "tablespoon");
        UNIT_ALIASES.put("tsp", "teaspoon");
        UNIT_ALIASES.put("teaspoon", "teaspoon");
        UNIT_ALIASES.put("teaspoons", "teaspoon");
        UNIT_ALIASES.put("piece", "piece");
        UNIT_ALIASES.put("pieces", "piece");
        UNIT_ALIASES.put("pcs", "piece");
        UNIT_ALIASES.put("clove", "clove");
        UNIT_ALIASES.put("cloves", "clove");
        UNIT_ALIASES.put("pinch", "pinch");
        UNIT_ALIASES.put("pinches", "pinch");
    }

    public static String normalizeName(String name) {
        if (name == null) {
            return "";
        }
        String normalized = name.trim().toLowerCase(Locale.ROOT);
        if (normalized.endsWith("ies") && normalized.length() > 4) {
            normalized = normalized.substring(0, normalized.length() - 3) + "y";
        } else if (normalized.endsWith("es") && normalized.length() > 3) {
            normalized = normalized.substring(0, normalized.length() - 2);
        } else if (normalized.endsWith("s") && normalized.length() > 2) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }

    public static String normalizeUnit(String unit) {
        if (unit == null || unit.trim().isEmpty()) {
            return "piece";
        }
        String key = unit.trim().toLowerCase(Locale.ROOT);
        String alias = UNIT_ALIASES.get(key);
        return alias != null ? alias : key;
    }

    public static boolean canMakeRecipe(Recipe recipe, List<PantryItem> pantryItems) {
        for (RecipeIngredient required : recipe.getIngredients()) {
            if (!hasEnoughInPantry(required, pantryItems)) {
                return false;
            }
        }
        return !recipe.getIngredients().isEmpty();
    }

    private static boolean hasEnoughInPantry(RecipeIngredient required, List<PantryItem> pantryItems) {
        String requiredName = normalizeName(required.getName());
        String requiredUnit = normalizeUnit(required.getUnit());
        double requiredQty = required.getQuantity();

        for (PantryItem pantryItem : pantryItems) {
            String pantryName = normalizeName(pantryItem.getName());
            String pantryUnit = normalizeUnit(pantryItem.getUnit());

            if (pantryName.equals(requiredName) && pantryUnit.equals(requiredUnit)) {
                if (pantryItem.getQuantity() >= requiredQty) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<Recipe> getSuggestedRecipes(List<Recipe> allRecipes, List<PantryItem> pantryItems) {
        List<Recipe> suggested = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (canMakeRecipe(recipe, pantryItems)) {
                suggested.add(recipe);
            }
        }
        return suggested;
    }
}
