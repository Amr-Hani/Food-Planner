package com.example.foodplanner.favorite.view;

import com.example.foodplanner.model.DetailsMealItem;

import java.util.List;

public interface FavoriteMealView {
    public void getFavoriteMeal(List<DetailsMealItem> list);
    public void getDetailsMealErrorMsg(String msg);
}
