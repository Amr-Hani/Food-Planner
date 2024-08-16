package com.example.foodplanner.search.search_by_ingredient.pesenter;

import com.example.foodplanner.model.IngredientNameItem;
import com.example.foodplanner.network.APIs;
import com.example.foodplanner.network.IngredientNameCallBack;
import com.example.foodplanner.search.search_by_ingredient.view.SearchByIngredientNameView;

import java.util.List;

public class SearchByIngredientNamePresenter implements IngredientNameCallBack {

    APIs apIs;
    SearchByIngredientNameView searchByIngredientNameView;

    public SearchByIngredientNamePresenter(SearchByIngredientNameView searchByIngredientNameView) {
        this.searchByIngredientNameView = searchByIngredientNameView;
        apIs = APIs.getInstance();
        apIs.makeNetworkCall(this);
    }

    @Override
    public void onSuccessResult(List<IngredientNameItem> list) {
        searchByIngredientNameView.getSearchByIngredient(list);
    }

    @Override
    public void onFailureResult(String mesg) {
        searchByIngredientNameView.getSearchByIngredientError(mesg);
    }
}
