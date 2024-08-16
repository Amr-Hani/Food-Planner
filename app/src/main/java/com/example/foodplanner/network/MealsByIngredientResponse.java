package com.example.foodplanner.network;

import com.example.foodplanner.model.MealsByCategoryItem;
import com.example.foodplanner.model.MealsByIngredientItem;

import java.util.List;

public class MealsByIngredientResponse {

    private List<MealsByIngredientItem> meals;

    public List<MealsByIngredientItem> getMeals() {
        return meals;
    }

    public void setMeals(List<MealsByIngredientItem> meals) {
        this.meals = meals;
    }
}
