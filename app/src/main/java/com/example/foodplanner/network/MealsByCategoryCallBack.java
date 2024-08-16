package com.example.foodplanner.network;

import com.example.foodplanner.model.MealsByCategoryItem;
import com.example.foodplanner.model.MealsOfTheDayItem;

import java.util.List;

public interface MealsByCategoryCallBack {
    public void onSuccessResult(List<MealsByCategoryItem> list);
    public void onFailureResult(String mesg);

}
