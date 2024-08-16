package com.example.foodplanner.search.searchbycategory.presenter;

import android.util.Log;

import com.example.foodplanner.model.CategoryMealItem;
import com.example.foodplanner.network.APIs;
import com.example.foodplanner.network.CategoryMealCallBack;
import com.example.foodplanner.search.searchbycategory.view.SearchByCategoryMealView;

import java.util.List;

public class SearchByCategoryPresenter implements CategoryMealCallBack {
    APIs apIs;
    SearchByCategoryMealView searchByCategoryMealView;

    public SearchByCategoryPresenter(SearchByCategoryMealView searchByCategoryMealView) {
        this.searchByCategoryMealView = searchByCategoryMealView;
        apIs = APIs.getInstance();
        apIs.makeNetworkCall(this);
    }

    @Override
    public void onSuccessResult(List<CategoryMealItem> list) {
        Log.i("TAG", "onSuccessResult: ana get hena "+list.get(0).getStrCategory());
        searchByCategoryMealView.getSearchByCategory(list);
    }

    @Override
    public void onFailureResult(String mesg) {
        searchByCategoryMealView.getSearchByCategoryError(mesg);
    }
}
