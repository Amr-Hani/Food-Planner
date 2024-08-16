package com.example.foodplanner.network;

import com.example.foodplanner.model.DetailsMealItem;
import com.example.foodplanner.model.MealsByCategoryItem;

import java.util.List;

public interface DetailsMealCallBack {
    public void onSuccessResult(List<DetailsMealItem> list);
    public void onFailureResult(String mesg);

}
