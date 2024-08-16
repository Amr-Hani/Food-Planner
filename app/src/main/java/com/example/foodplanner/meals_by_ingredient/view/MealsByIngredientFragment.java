package com.example.foodplanner.meals_by_ingredient.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.meals_by_ingredient.presenter.MealsByIngredientPresenter;
import com.example.foodplanner.model.MealsByIngredientItem;

import java.util.List;

public class MealsByIngredientFragment extends Fragment implements MealsByIngredientView {
    MealsByIngredientPresenter mealsByIngredientPresenter;
    MealsByIngredientAdapter mealsByIngredientAdapter;
    RecyclerView rv_mealsByIngredient;

    public MealsByIngredientFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_meals_by_ingredient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsByIngredientPresenter = new MealsByIngredientPresenter(this);
        rv_mealsByIngredient = view.findViewById(R.id.rv_mealbyingredient);
        String ingredientMeal = MealsByIngredientFragmentArgs.fromBundle(getArguments()).getMlByIngredient();
        mealsByIngredientPresenter.getMealsByIngredient(ingredientMeal);
    }

    @Override
    public void getMealsByIngredient(List<MealsByIngredientItem> list) {
        mealsByIngredientAdapter = new MealsByIngredientAdapter(getContext(),list);
        rv_mealsByIngredient.setAdapter(mealsByIngredientAdapter);
        rv_mealsByIngredient.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void getMealsByIngredietnErrorMsg(String errMsg) {

    }
}