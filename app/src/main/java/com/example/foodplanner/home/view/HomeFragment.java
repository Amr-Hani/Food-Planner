package com.example.foodplanner.home.view;

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
import com.example.foodplanner.category.presenter.CategoryMealPresenter;
import com.example.foodplanner.category.view.CategoryAdapter;
import com.example.foodplanner.category.view.CategoryMealView;
import com.example.foodplanner.home.presenter.HomePagePresenter;
import com.example.foodplanner.model.CategoryMealItem;
import com.example.foodplanner.model.MealsOfTheDayItem;

import java.util.List;

public class HomeFragment extends Fragment implements HomeScreenView, CategoryMealView {
    RecyclerView rv_mealOfTheDay;
    RecyclerView rv_categoryMeal;
    HomeScreenAdapter homeScreenAdapter;
    HomePagePresenter homePagePresenter;
    CategoryMealPresenter categoryMealPresenter;
    CategoryAdapter categoryAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homePagePresenter = new HomePagePresenter(this);
        categoryMealPresenter = new CategoryMealPresenter(this);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_mealOfTheDay = view.findViewById(R.id.rv_mealoftheday);
        rv_categoryMeal = view.findViewById(R.id.rv_categorymeal);
    }

    @Override
    public void getMealOfTheDay(List<MealsOfTheDayItem> list) {
        homeScreenAdapter = new HomeScreenAdapter(getContext(),list);
        rv_mealOfTheDay.setAdapter(homeScreenAdapter);
        rv_mealOfTheDay.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void getMsgErr(String msg) {
    }

    @Override
    public void getCategoryOfTheDay(List<CategoryMealItem> list) {
        categoryAdapter = new CategoryAdapter(getContext(),list);
        rv_categoryMeal.setAdapter(categoryAdapter);
        rv_categoryMeal.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void getCategoryErrorMsg(String msg) {

    }

}