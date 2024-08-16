package com.example.foodplanner.network;

import com.example.foodplanner.model.MealNameItem;
import com.example.foodplanner.model.MealsByCategoryItem;

import java.util.List;

public interface MealNameCallBack {
    public void onSuccessResult(List<MealNameItem> list);
    public void onFailureResult(String mesg);
}
