package com.example.foodplanner.category.presenter;

import android.util.Log;

import com.example.foodplanner.category.view.CategoryMealView;
import com.example.foodplanner.model.CategoryMealItem;
import com.example.foodplanner.network.APIs;
import com.example.foodplanner.network.CategoryMealCallBack;

import java.util.List;

public class CategoryMealPresenter implements CategoryMealCallBack {
    APIs apIs;
    CategoryMealView categoryMealView;

    public CategoryMealPresenter(CategoryMealView categoryMealView) {
        this.categoryMealView = categoryMealView;
        apIs = APIs.getInstance();
        apIs.makeNetworkCall(this);
    }

    @Override
    public void onSuccessResult(List<CategoryMealItem> list) {
        Log.i("TAG", "onSuccessResult: ana get hena "+list.get(0).getStrCategory());
        categoryMealView.getCategoryOfTheDay(list);
    }

    @Override
    public void onFailureResult(String mesg) {

    }
}
