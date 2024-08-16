package com.example.foodplanner.network;

import com.example.foodplanner.model.CategoryMealItem;

import java.util.List;

public class CategoryMealResponse {
    private List<CategoryMealItem> categories;

    public List<CategoryMealItem> getGetCategoryMeal() {
        return categories;
    }

    public void setGetCategoryMeal(List<CategoryMealItem> productList) {
        this.categories = productList;
    }
}
