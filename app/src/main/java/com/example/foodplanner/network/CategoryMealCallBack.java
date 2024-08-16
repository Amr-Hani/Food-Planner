package com.example.foodplanner.network;

import com.example.foodplanner.model.CategoryMealItem;

import java.util.List;

public interface CategoryMealCallBack {
    public void onSuccessResult(List<CategoryMealItem> list);
    public void onFailureResult(String mesg);

}
