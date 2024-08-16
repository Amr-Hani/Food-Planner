package com.example.foodplanner.network;

import com.example.foodplanner.model.MealNameItem;

import java.util.List;

public class MealNameResponse {
    List<MealNameItem>meals;

    public List<MealNameItem> getMeals() {
        return meals;
    }

    public void setMeals(List<MealNameItem> meals) {
        this.meals = meals;
    }
}
