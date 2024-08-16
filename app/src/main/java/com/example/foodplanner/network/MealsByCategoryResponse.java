package com.example.foodplanner.network;

import com.example.foodplanner.model.MealsByCategoryItem;
import com.example.foodplanner.model.MealsOfTheDayItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealsByCategoryResponse {

    private List<MealsByCategoryItem> meals;

    public List<MealsByCategoryItem> getMealsByCategory() {
        return meals;
    }

    public void setMealsByCategory(List<MealsByCategoryItem> meals) {
        this.meals = meals;
    }
}
