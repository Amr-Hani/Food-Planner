package com.example.foodplanner.search.search_by_country.presenter;

import android.util.Log;

import com.example.foodplanner.model.CategoryMealItem;
import com.example.foodplanner.model.CountryNameItem;
import com.example.foodplanner.network.APIs;
import com.example.foodplanner.network.CountryNameCaleBack;
import com.example.foodplanner.search.search_by_country.view.SearchByCountryNameView;
import com.example.foodplanner.search.searchbycategory.view.SearchByCategoryMealView;

import java.util.List;

public class SearchByCountryNamePresenter implements CountryNameCaleBack {
    APIs apIs;
    SearchByCountryNameView searchByCountryNameView;

    public SearchByCountryNamePresenter(SearchByCountryNameView searchByCountryNameView) {
        this.searchByCountryNameView = searchByCountryNameView;
        apIs = APIs.getInstance();
        apIs.makeNetworkCall(this);
    }

    @Override
    public void onSuccessResult(List<CountryNameItem> list) {
        searchByCountryNameView.getSearchByCountryName(list);
    }

    @Override
    public void onFailureResult(String mesg) {
        searchByCountryNameView.getSearchByCountryNameError(mesg);
    }
}
