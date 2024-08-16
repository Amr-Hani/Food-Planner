package com.example.foodplanner.network;

import com.example.foodplanner.model.MealsByIngredientItem;

import java.util.List;

public interface MealsByIngredientCallBack {
    public void onSuccessResult(List<MealsByIngredientItem> list);
    public void onFailureResult(String mesg);
}
