package com.example.foodplanner.database;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.DetailsMealItem;
import com.example.foodplanner.model.PlanMealItem;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


@androidx.room.Dao
public interface DAO {

    @Query("SELECT * FROM favoriteMeals")
    Flowable<List<DetailsMealItem>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal(DetailsMealItem product);

    @Query("DELETE FROM favoriteMeals WHERE idMeal = :idMeal")
    void deleteMeal(String idMeal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal(PlanMealItem plan);

    @Query("SELECT * FROM planmeal WHERE day = :day")
    Flowable<List<PlanMealItem>> getPlansByDay(String day);

    @Query("DELETE FROM planmeal WHERE idMeal = :idMeal")
    void deletePlaneMeal(String idMeal);


    //Delete All Favorite Meals From Database
    @Query("DELETE FROM favoriteMeals")
    void deleteAllFavoriteMeals();

    @Query("DELETE FROM planmeal")
    void deleteAllPlanMeals();

}