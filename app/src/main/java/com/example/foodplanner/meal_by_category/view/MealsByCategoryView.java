package com.example.foodplanner.meal_by_category.view;

import com.example.foodplanner.model.MealsByCategoryItem;

import java.util.List;

public interface MealsByCategoryView {
    public void getMealsByCategory(List<MealsByCategoryItem>list);
    public void getMealsByCategoryErrorMsg(String errMsg);

}
