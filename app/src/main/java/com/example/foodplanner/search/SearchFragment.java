package com.example.foodplanner.search;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.CategoryMealItem;
import com.example.foodplanner.model.CountryNameItem;
import com.example.foodplanner.model.IngredientNameItem;
import com.example.foodplanner.model.MealNameItem;
import com.example.foodplanner.search.search_by_country.presenter.SearchByCountryNamePresenter;
import com.example.foodplanner.search.search_by_country.view.SearchByCountryNameAdapter;
import com.example.foodplanner.search.search_by_country.view.SearchByCountryNameView;
import com.example.foodplanner.search.search_by_ingredient.pesenter.SearchByIngredientNamePresenter;
import com.example.foodplanner.search.search_by_ingredient.view.SearchByIngredientNameAdapter;
import com.example.foodplanner.search.search_by_ingredient.view.SearchByIngredientNameView;
import com.example.foodplanner.search.search_by_meal_name.presenter.MealNamePresenter;
import com.example.foodplanner.search.search_by_meal_name.view.MealNameAdapter;
import com.example.foodplanner.search.search_by_meal_name.view.MealNameView;
import com.example.foodplanner.search.searchbycategory.presenter.SearchByCategoryPresenter;
import com.example.foodplanner.search.searchbycategory.view.SearchByCategoryAdapter;
import com.example.foodplanner.search.searchbycategory.view.SearchByCategoryMealView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class SearchFragment extends Fragment implements SearchByCategoryMealView, SearchByCountryNameView , SearchByIngredientNameView  {
    SearchView searchView;

    ChipGroup chipGroup;
    Chip chp_category;
    RecyclerView rv_category;
    SearchByCategoryPresenter searchByCategoryPresenter;
    SearchByCategoryAdapter searchByCategoryAdapter;

    Chip chp_country;
    RecyclerView rv_country;
    SearchByCountryNamePresenter searchByCountryNamePresenter;
    SearchByCountryNameAdapter searchByCountryNameAdapter;

    Chip chp_ingredient;
    RecyclerView rv_ingredient;
    SearchByIngredientNameAdapter searchByIngredientNameAdapter;
    SearchByIngredientNamePresenter searchByIngredientNamePresenter;

    Chip chp_meal;
    RecyclerView rv_meal;
    MealNameAdapter mealNameAdapter;
    MealNamePresenter mealNamePresenter;

    List<IngredientNameItem> ingredientNameItemList;
    List<CountryNameItem> countryNameItemList;
    List<CategoryMealItem> categoryMealItemList;
    List<MealNameItem> mealNameItemList;
    public SearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchByCategoryPresenter= new SearchByCategoryPresenter(this);


        searchView= view.findViewById(R.id.searchView);

        chipGroup = view.findViewById(R.id.ch_group);
        chipGroup.setSingleSelection(true);
        chp_category = view.findViewById(R.id.chp_category);
        rv_category = view.findViewById(R.id.rv_category);
        rv_country = view.findViewById(R.id.rv_country);
        chp_country = view.findViewById(R.id.chp_country);

        chp_ingredient = view.findViewById(R.id.chp_ingredient);
        rv_ingredient = view.findViewById(R.id.rv_ingredient);
        searchByIngredientNamePresenter = new SearchByIngredientNamePresenter(this);

        searchByCountryNamePresenter = new SearchByCountryNamePresenter(this);
        chp_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv_category.setAdapter(searchByCategoryAdapter);
                rv_category.setLayoutManager(new GridLayoutManager(getContext(),2));
                rv_category.setVisibility(View.VISIBLE);
                rv_country.setVisibility(View.GONE);
            }
        });
        chp_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv_country.setAdapter(searchByCountryNameAdapter);
                rv_country.setLayoutManager(new GridLayoutManager(getContext(),3));
                rv_country.setVisibility(View.VISIBLE);
                rv_category.setVisibility(View.GONE);
                rv_ingredient.setVisibility(View.GONE);
            }
        });
        chp_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv_ingredient.setAdapter(searchByIngredientNameAdapter);
                rv_ingredient.setLayoutManager(new GridLayoutManager(getContext(),3));
                rv_ingredient.setVisibility(View.VISIBLE);
                rv_country.setVisibility(View.GONE);
                rv_category.setVisibility(View.GONE);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @SuppressLint("CheckResult")
            @Override
            public boolean onQueryTextChange(String newText) {

                if(chp_ingredient.isChecked()) {
                    Observable.fromIterable(ingredientNameItemList)
                            .filter(item -> item.getStrIngredient().toLowerCase().contains(newText.toString()))
                            .toList().subscribe(
                                    fillterList -> searchByIngredientNameAdapter.setData(fillterList)
                            );
                }
                else if(chp_country.isChecked())
                {
                    Observable.fromIterable(countryNameItemList)
                            .filter(item -> item.getStrArea().toLowerCase().contains(newText.toString()))
                            .toList().subscribe(
                                    fillterList -> searchByCountryNameAdapter.setData(fillterList)
                            );

                }
                else if(chp_category.isChecked())
                {
                    Observable.fromIterable(categoryMealItemList)
                            .filter(item -> item.getStrCategory().toLowerCase().contains(newText.toString()))
                            .toList().subscribe(
                                    fillterList -> searchByCategoryAdapter.setData(fillterList)
                            );

                }
//                else if(chp_meal.isChecked())
//                {
//                    Observable.fromIterable(mealNameItemList)
//                            .filter(item -> item.getStrMeal().toLowerCase().contains(newText.toString()))
//                            .toList().subscribe(
//                                    fillterList -> mealNameAdapter.setData(fillterList)
//                            );
//
//                }
                return false;
            }
        });
    }
    @Override
    public void getSearchByCategory(List<CategoryMealItem> list) {
        searchByCategoryAdapter = new SearchByCategoryAdapter(getContext(),list);
        categoryMealItemList = list;
    }

    @Override
    public void getSearchByCategoryError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSearchByCountryName(List<CountryNameItem> list) {
        searchByCountryNameAdapter = new SearchByCountryNameAdapter(getContext(),list);
        countryNameItemList = list;
    }

    @Override
    public void getSearchByCountryNameError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSearchByIngredient(List<IngredientNameItem> list) {
        searchByIngredientNameAdapter = new SearchByIngredientNameAdapter(getContext(),list);
        ingredientNameItemList = list;
    }

    @Override
    public void getSearchByIngredientError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
//
//    @Override
//    public void getSearchByMealName(List<MealNameItem> list) {
//        mealNameAdapter = new MealNameAdapter(getContext(),list);
//        mealNameItemList = list;
//    }
//
//    @Override
//    public void getSearchByMealNameError(String msg) {
//
//    }
}