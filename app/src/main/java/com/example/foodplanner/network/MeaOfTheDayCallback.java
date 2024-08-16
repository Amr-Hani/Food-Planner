package com.example.foodplanner.network;

import com.example.foodplanner.model.MealsOfTheDayItem;


import java.util.List;

public interface MeaOfTheDayCallback {
    public void onSuccessResult(List<MealsOfTheDayItem>list);
    public void onFailureResult(String mesg);
}
