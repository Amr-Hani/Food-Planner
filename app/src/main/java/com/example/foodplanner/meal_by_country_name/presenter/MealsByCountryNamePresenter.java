package com.example.foodplanner.meal_by_country_name.presenter;

import com.example.foodplanner.meal_by_country_name.view.MealByCountryView;
import com.example.foodplanner.model.MealByCountryItem;
import com.example.foodplanner.network.APIs;
import com.example.foodplanner.network.MealByCountryCallBack;

import java.util.List;

public class MealsByCountryNamePresenter implements MealByCountryCallBack {

    APIs apIs;
    MealByCountryView mealByCountryView;

    public MealsByCountryNamePresenter(MealByCountryView mealByCountryView) {
        this.mealByCountryView = mealByCountryView;
        apIs = APIs.getInstance();
    }

    public void getMealByCountryName(String countryName)
    {
        apIs.makeNetworkCall(this,countryName);
    }

    @Override
    public void onSuccessResult(List<MealByCountryItem> list) {
        mealByCountryView.getMealsByCountryName(list);
    }

    @Override
    public void onFailureResult(String mesg) {
        mealByCountryView.getMealsByCategoryErrorMsg(mesg);
    }
}
