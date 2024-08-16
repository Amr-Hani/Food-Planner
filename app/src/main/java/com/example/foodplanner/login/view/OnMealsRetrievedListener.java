package com.example.foodplanner.login.view;

import com.example.foodplanner.model.DetailsMealItem;

import java.util.List;

public interface OnMealsRetrievedListener {
    void onMealsRetrieved(List<DetailsMealItem> meals);
    void onError(Exception e);
}
