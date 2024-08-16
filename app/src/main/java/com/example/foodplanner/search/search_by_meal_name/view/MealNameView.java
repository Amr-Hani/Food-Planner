package com.example.foodplanner.search.search_by_meal_name.view;

import com.example.foodplanner.model.MealNameItem;

import java.util.List;

public interface MealNameView {
    public void getSearchByMealName(List<MealNameItem> list);
    public void getSearchByMealNameError(String msg);
}
