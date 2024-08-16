package com.example.foodplanner.search.searchbycategory.view;

import com.example.foodplanner.model.CategoryMealItem;

import java.util.List;

public interface SearchByCategoryMealView {
    public void getSearchByCategory(List<CategoryMealItem> list);
    public void getSearchByCategoryError(String msg);

}
