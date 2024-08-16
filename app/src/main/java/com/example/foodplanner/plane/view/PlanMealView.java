package com.example.foodplanner.plane.view;

import com.example.foodplanner.model.MealsByIngredientItem;
import com.example.foodplanner.model.PlanMealItem;

import java.util.List;

public interface PlanMealView {
    public void getMealsPlan(List<PlanMealItem> list);
    public void getMealsPlanErrorMsg(String errMsg);

}
