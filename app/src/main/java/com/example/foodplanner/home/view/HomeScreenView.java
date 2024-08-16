package com.example.foodplanner.home.view;

import com.example.foodplanner.model.MealsOfTheDayItem;

import java.util.List;

public interface HomeScreenView {
    public void getMealOfTheDay(List<MealsOfTheDayItem>list);
    public void getMsgErr(String msg);

}
