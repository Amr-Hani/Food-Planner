package com.example.foodplanner.meal_by_category.view;

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
import com.example.foodplanner.meal_by_category.presenter.MealsByCategoryPresenter;
import com.example.foodplanner.model.MealsByCategoryItem;

import java.util.ArrayList;
import java.util.List;

public class MealsByCategoryFragment extends Fragment implements MealsByCategoryView{

    MealsByCategoryPresenter mealsByCategoryPresenter;
    MealsByCategoryAdapter mealsByCategoryAdapter;
    RecyclerView rv_mealsByCategory;

    public MealsByCategoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meals_by_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsByCategoryPresenter = new MealsByCategoryPresenter(this);
        mealsByCategoryAdapter = new MealsByCategoryAdapter(getContext(),new ArrayList<>());
        rv_mealsByCategory = view.findViewById(R.id.rv_mealsbycategoryname);
        String categoryName = MealsByCategoryFragmentArgs.fromBundle(getArguments()).getCategoryName();
        mealsByCategoryPresenter.getMealsByCategoryName(categoryName);
    }

    @Override
    public void getMealsByCategory(List<MealsByCategoryItem> list) {
        mealsByCategoryAdapter = new MealsByCategoryAdapter(getContext(),list);
        rv_mealsByCategory.setAdapter(mealsByCategoryAdapter);
        rv_mealsByCategory.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void getMealsByCategoryErrorMsg(String errMsg) {

    }
}