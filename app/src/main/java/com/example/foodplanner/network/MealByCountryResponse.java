package com.example.foodplanner.network;

import com.example.foodplanner.model.MealByCountryItem;
import java.util.List;

public class MealByCountryResponse {

    private List<MealByCountryItem> meals;

    public List<MealByCountryItem> getMeals() {
        return meals;
    }

    public void setMeals(List<MealByCountryItem> meals) {
        this.meals = meals;
    }
}
