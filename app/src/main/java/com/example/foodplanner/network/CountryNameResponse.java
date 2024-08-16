package com.example.foodplanner.network;

import com.example.foodplanner.model.CountryNameItem;

import java.util.List;

public class CountryNameResponse {
    List<CountryNameItem> meals;
    public List<CountryNameItem> getMeals() {
        return meals;
    }
    public void setMeals(List<CountryNameItem> meals) {
        this.meals = meals;
    }
}
