package com.example.foodplanner.meals_by_ingredient.view;

import com.example.foodplanner.model.MealByCountryItem;
import com.example.foodplanner.model.MealsByIngredientItem;

import java.util.List;

public interface MealsByIngredientView {
    public void getMealsByIngredient(List<MealsByIngredientItem> list);
    public void getMealsByIngredietnErrorMsg(String errMsg);
}
