package com.example.foodplanner.network;

import com.example.foodplanner.model.MealByCountryItem;
import java.util.List;

public interface MealByCountryCallBack {
    public void onSuccessResult(List<MealByCountryItem> list);
    public void onFailureResult(String mesg);
}
