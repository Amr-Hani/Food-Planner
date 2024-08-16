package com.example.foodplanner.home.presenter;

import android.content.Context;

import com.example.foodplanner.home.view.HomeScreenView;
import com.example.foodplanner.model.MealsOfTheDayItem;
import com.example.foodplanner.network.APIs;
import com.example.foodplanner.network.MeaOfTheDayCallback;

import java.util.List;

public class HomePagePresenter implements MeaOfTheDayCallback {
    APIs apIs;
    Context context;
    HomeScreenView homeScreenView;

    public HomePagePresenter( HomeScreenView homeScreenView) {
        this.context = context;
        this.homeScreenView = homeScreenView;
        apIs = APIs.getInstance();
        apIs.makeNetworkCall(this);
    }


    @Override
    public void onSuccessResult(List<MealsOfTheDayItem> list) {
         homeScreenView.getMealOfTheDay(list);
    }

    @Override
    public void onFailureResult(String mesg) {

        homeScreenView.getMsgErr(mesg);
    }
}
