package com.example.foodplanner.search.search_by_ingredient.view;

import com.example.foodplanner.model.CategoryMealItem;
import com.example.foodplanner.model.IngredientNameItem;

import java.util.List;

public interface SearchByIngredientNameView {

    public void getSearchByIngredient(List<IngredientNameItem> list);
    public void getSearchByIngredientError(String msg);
}
