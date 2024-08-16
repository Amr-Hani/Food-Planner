package com.example.foodplanner.network;

import com.example.foodplanner.model.CategoryMealItem;
import com.example.foodplanner.model.CountryNameItem;

import java.util.List;

public interface CountryNameCaleBack {
    public void onSuccessResult(List<CountryNameItem> list);
    public void onFailureResult(String mesg);
}
