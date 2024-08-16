package com.example.foodplanner.meal_by_country_name.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.meal_by_country_name.presenter.MealsByCountryNamePresenter;
import com.example.foodplanner.model.MealByCountryItem;

import java.util.ArrayList;
import java.util.List;

public class MealByCuontryFragment extends Fragment implements MealByCountryView{

    MealByCountryNameAdapter mealByCountryNameAdapter;
    MealsByCountryNamePresenter mealsByCountryNamePresenter;
    RecyclerView rv_countryName;

    public MealByCuontryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_by_cuontry, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsByCountryNamePresenter = new MealsByCountryNamePresenter(this);
        rv_countryName = view.findViewById(R.id.rv_mealbycountry);
        String countryName = MealByCuontryFragmentArgs.fromBundle(getArguments()).getMealByCountryName();
        Log.d("TAG", "onViewCreated: ana get hent fe fragment bt3 mel By country " + countryName);
        mealsByCountryNamePresenter.getMealByCountryName(countryName);
    }

    @Override
    public void getMealsByCountryName(List<MealByCountryItem> list) {
        mealByCountryNameAdapter = new MealByCountryNameAdapter(getContext(),list);
        rv_countryName.setAdapter(mealByCountryNameAdapter);
        rv_countryName.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void getMealsByCategoryErrorMsg(String errMsg) {

    }
}