package com.example.foodplanner.network;

import com.example.foodplanner.model.IngredientNameItem;

import java.util.List;

public class IngredientNameResponse {
    private List<IngredientNameItem> meals;

    public List<IngredientNameItem> getMeals() {
        return meals;
    }

    public void setMeals(List<IngredientNameItem> meals) {
        this.meals = meals;
    }
}
