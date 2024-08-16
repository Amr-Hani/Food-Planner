package com.example.foodplanner.network;


import com.example.foodplanner.model.MealsOfTheDayItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealOfTheDayResponse
{
    @SerializedName("meals")
    private List<MealsOfTheDayItem> meals;

    public List<MealsOfTheDayItem> getMealOfTheDay() {
        return meals;
    }

    public void setMealOfTheDay(List<MealsOfTheDayItem> productList) {
        this.meals = productList;
    }
}