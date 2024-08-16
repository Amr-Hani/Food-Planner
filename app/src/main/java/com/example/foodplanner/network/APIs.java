package com.example.foodplanner.network;

import android.annotation.SuppressLint;


import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIs {
    private static final String TAG = "API_Product";
    private static final String URL="https://www.themealdb.com/api/json/v1/1/";
    private static APIs product= null;
    MealsServices mealsServices;
    private APIs(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        mealsServices = retrofit.create(MealsServices.class);
    }
    public static APIs getInstance()
    {
        if(product==null)
        {
            product = new APIs();
        }
        return product;
    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(MeaOfTheDayCallback meaOfTheDayCallback)
    {
        mealsServices.getMealOfTheDay().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    meaOfTheDayCallback.onSuccessResult(item.getMealOfTheDay());
                },throwable ->
                {
                    meaOfTheDayCallback.onFailureResult(throwable.getMessage());
                }
        );

    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(CategoryMealCallBack categoryMealCallBack)
    {

        mealsServices.getCategoryMeal().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    categoryMealCallBack.onSuccessResult(item.getGetCategoryMeal());
                },throwable ->
                {
                    categoryMealCallBack.onFailureResult(throwable.getMessage());
                }
        );
    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(MealsByCategoryCallBack mealsByCategoryCallBack,String CategoryName)
    {
        mealsServices.getMealsByCategory(CategoryName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    mealsByCategoryCallBack.onSuccessResult(item.getMealsByCategory());
                },throwable ->
                {
                    mealsByCategoryCallBack.onFailureResult(throwable.getMessage());
                }
        );
    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(DetailsMealCallBack detailsMealCallBack,String detailsMeal)
    {
        mealsServices.getDetailsMeal(detailsMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    detailsMealCallBack.onSuccessResult(item.getDetailsMeal());
                },throwable ->
                {
                    detailsMealCallBack.onFailureResult(throwable.getMessage());
                }
        );
    }


    @SuppressLint("CheckResult")
    public void makeNetworkCall(CountryNameCaleBack countryNameCaleBack)
    {
        mealsServices.getCountry().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    countryNameCaleBack.onSuccessResult(item.getMeals());
                },throwable ->
                {
                    countryNameCaleBack.onFailureResult(throwable.getMessage());
                }
        );
    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(IngredientNameCallBack ingredientNameCallBack)
    {
        mealsServices.getIngredients().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    ingredientNameCallBack.onSuccessResult(item.getMeals());
                },throwable ->
                {
                    ingredientNameCallBack.onFailureResult(throwable.getMessage());
                }
        );

    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(MealByCountryCallBack mealByCountryCallBack,String mealCountry)
    {
        mealsServices.getMealsByCountry(mealCountry).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    mealByCountryCallBack.onSuccessResult(item.getMeals());
                },throwable ->
                {
                    mealByCountryCallBack.onFailureResult(throwable.getMessage());
                }
        );
    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(MealsByIngredientCallBack mealsByIngredientCallBack,String ingredientMeal)
    {
        mealsServices.getMealsByIngredient(ingredientMeal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                item -> {
                    mealsByIngredientCallBack.onSuccessResult(item.getMeals());
                },throwable ->
                {
                    mealsByIngredientCallBack.onFailureResult(throwable.getMessage());
                }
        );
    }
//
//    @SuppressLint("CheckResult")
//    public void makeNetworkCall(MealNameCallBack mealNameCallBack,String mealName)
//    {
//        mealsServices.getMealName(mealName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
//                item -> {
//                    mealNameCallBack.onSuccessResult(item.getMeals());
//                },throwable ->
//                {
//                    mealNameCallBack.onFailureResult(throwable.getMessage());
//                }
//        );
//    }

}
