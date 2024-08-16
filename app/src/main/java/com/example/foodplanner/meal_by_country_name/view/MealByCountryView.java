package com.example.foodplanner.meal_by_country_name.view;

import com.example.foodplanner.model.MealByCountryItem;

import java.util.List;

public interface MealByCountryView {
    public void getMealsByCountryName(List<MealByCountryItem> list);
    public void getMealsByCategoryErrorMsg(String errMsg);
}
