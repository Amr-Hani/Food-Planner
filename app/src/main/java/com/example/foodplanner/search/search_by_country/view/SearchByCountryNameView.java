package com.example.foodplanner.search.search_by_country.view;

import com.example.foodplanner.model.CountryNameItem;

import java.util.List;

public interface SearchByCountryNameView {
    public void getSearchByCountryName(List<CountryNameItem> list);
    public void getSearchByCountryNameError(String msg);

}
