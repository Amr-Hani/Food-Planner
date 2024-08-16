package com.example.foodplanner.meal_by_category.presenter;

import com.example.foodplanner.meal_by_category.view.MealsByCategoryView;
import com.example.foodplanner.model.MealsByCategoryItem;
import com.example.foodplanner.network.APIs;
import com.example.foodplanner.network.MealsByCategoryCallBack;

import java.util.List;

public class MealsByCategoryPresenter implements MealsByCategoryCallBack {

    APIs apIs;
    MealsByCategoryView mealsByCategoryView;

    public MealsByCategoryPresenter(MealsByCategoryView mealsByCategoryView) {
        this.apIs = APIs.getInstance();
        this.mealsByCategoryView = mealsByCategoryView;
    }
    public void getMealsByCategoryName(String name)
    {
        apIs.makeNetworkCall(this,name);
    }
    @Override
    public void onSuccessResult(List<MealsByCategoryItem> list) {
        mealsByCategoryView.getMealsByCategory(list);
    }

    @Override
    public void onFailureResult(String mesg) {
        mealsByCategoryView.getMealsByCategoryErrorMsg(mesg);

    }
}
