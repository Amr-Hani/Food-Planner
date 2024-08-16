package com.example.foodplanner.search.search_by_meal_name.presenter;

import com.example.foodplanner.model.MealNameItem;
import com.example.foodplanner.network.APIs;
import com.example.foodplanner.network.MealNameCallBack;
import com.example.foodplanner.search.search_by_meal_name.view.MealNameView;

import java.util.List;

public class MealNamePresenter implements MealNameCallBack {
    APIs apIs;
    MealNameView mealNameView;

    public MealNamePresenter(MealNameView mealNameView) {
        this.mealNameView = mealNameView;
        apIs = APIs.getInstance();
    }


    @Override
    public void onSuccessResult(List<MealNameItem> list) {
        mealNameView.getSearchByMealName(list);
    }

    @Override
    public void onFailureResult(String mesg) {
        mealNameView.getSearchByMealNameError(mesg);
    }
}
