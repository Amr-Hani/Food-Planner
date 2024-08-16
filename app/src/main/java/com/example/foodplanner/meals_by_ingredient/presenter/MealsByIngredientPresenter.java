package com.example.foodplanner.meals_by_ingredient.presenter;

import com.example.foodplanner.meals_by_ingredient.view.MealsByIngredientFragment;
import com.example.foodplanner.meals_by_ingredient.view.MealsByIngredientView;
import com.example.foodplanner.model.MealsByIngredientItem;
import com.example.foodplanner.network.APIs;
import com.example.foodplanner.network.MealsByIngredientCallBack;

import java.util.List;

public class MealsByIngredientPresenter implements MealsByIngredientCallBack {

    APIs apIs;
    MealsByIngredientView mealsByIngredientView;

    public MealsByIngredientPresenter(MealsByIngredientView mealsByIngredientView) {
        this.mealsByIngredientView = mealsByIngredientView;
        apIs = APIs.getInstance();
    }

    public void getMealsByIngredient(String ingredientMeal)
    {
        apIs.makeNetworkCall(this,ingredientMeal);
    }

    @Override
    public void onSuccessResult(List<MealsByIngredientItem> list) {
        mealsByIngredientView.getMealsByIngredient(list);
    }

    @Override
    public void onFailureResult(String mesg) {
        mealsByIngredientView.getMealsByIngredietnErrorMsg(mesg);
    }
}
