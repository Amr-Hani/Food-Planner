package com.example.foodplanner.details.view;

import com.example.foodplanner.model.DetailsMealItem;

import java.util.List;

public interface DetailsMealView {
    public void getDetailsMeal(List<DetailsMealItem> list);
    public void getDetailsMealErrorMsg(String msg);
}
