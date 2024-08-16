package com.example.foodplanner.category.view;

import com.example.foodplanner.model.CategoryMealItem;

import java.util.List;

public interface CategoryMealView {
    public void getCategoryOfTheDay(List<CategoryMealItem> list);
    public void getCategoryErrorMsg(String msg);

}
