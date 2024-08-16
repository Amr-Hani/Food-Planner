package com.example.foodplanner.network;

import com.example.foodplanner.model.MealsOfTheDayItem;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsServices {
    @GET("random.php")
    Single<MealOfTheDayResponse> getMealOfTheDay();

    @GET("categories.php")
    Single<CategoryMealResponse> getCategoryMeal();

    @GET("filter.php")
    Single<MealsByCategoryResponse> getMealsByCategory(@Query("c")String categoryName);

    @GET("search.php")
    Single<DetailsMealResponse> getDetailsMeal(@Query("s")String categoryName);

//    @GET("search.php")
//    Single<MealNameResponse> getMealName(@Query("s")String mealName);

    @GET("filter.php")
    Single<MealByCountryResponse> getMealsByCountry(@Query("a") String country);

    @GET("filter.php")
    Single<MealsByIngredientResponse> getMealsByIngredient(@Query("i") String ingredient);

    @GET("list.php?a=list")
    Single<CountryNameResponse> getCountry();

    @GET("list.php?i=list")
    Single<IngredientNameResponse>getIngredients();

}
