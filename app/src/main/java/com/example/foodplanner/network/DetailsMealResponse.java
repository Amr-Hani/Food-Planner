package com.example.foodplanner.network;

import com.example.foodplanner.model.DetailsMealItem;
import com.example.foodplanner.model.MealsOfTheDayItem;

import java.util.List;

public class DetailsMealResponse {
    private List<DetailsMealItem> meals;

    public List<DetailsMealItem> getDetailsMeal() {
        return meals;
    }

    public void setDetailsMeal(List<DetailsMealItem> productList) {
        this.meals = productList;
    }

}
