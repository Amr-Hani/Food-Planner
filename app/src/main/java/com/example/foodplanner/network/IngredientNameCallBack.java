package com.example.foodplanner.network;

import com.example.foodplanner.model.CountryNameItem;
import com.example.foodplanner.model.IngredientNameItem;

import java.util.List;

public interface IngredientNameCallBack {
    public void onSuccessResult(List<IngredientNameItem> list);
    public void onFailureResult(String mesg);
}
