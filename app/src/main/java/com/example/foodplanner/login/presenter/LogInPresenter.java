package com.example.foodplanner.login.presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.database.DAO;
import com.example.foodplanner.database.MyRoomDB;
import com.example.foodplanner.model.DetailsMealItem;
import com.example.foodplanner.model.PlanMealItem;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LogInPresenter {
    FirebaseFirestore db;
    DAO dao;
    MyRoomDB myRoomDB;

    public LogInPresenter(Context context) {
        db = FirebaseFirestore.getInstance();
        myRoomDB = MyRoomDB.getInstance(context);
        dao = myRoomDB.mealsDAO();

    }

    public Task<List<DetailsMealItem>> getUserFavoriteMeals(String uid) {
        return db.collection("users")
                .document(uid)
                .collection("meals")
                .get()
                .continueWith(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                            List<DetailsMealItem> meals = new ArrayList<>();
                            for (DocumentSnapshot document : querySnapshot) {
                                DetailsMealItem meal = document.toObject(DetailsMealItem.class);
                                if (meal != null) {
                                    meal.setIdMeal(document.getId());
                                    meals.add(meal);
                                }
                            }
                            return meals;
                        } else {
                            return new ArrayList<>();
                        }
                    } else {
                        throw task.getException();
                    }
                });
    }

    public void addAllFavoriteMealsInLocalDatabase(List<DetailsMealItem> detailsMealItems) {
        for (DetailsMealItem p : detailsMealItems) {
            dao.insertMeal(p).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new CompletableObserver() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {

                                }

                                @Override
                                public void onComplete() {
                                    Log.d("TAG", "onComplete: inserted ");
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.d("TAG", e.getMessage());
                                }
                            });
        }

    }


    public Task<List<PlanMealItem>> getUserPlanMeals(String uid) {
        return db.collection("users")
                .document(uid)
                .collection("plan")
                .get()
                .continueWith(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                            List<PlanMealItem> meals = new ArrayList<>();
                            for (DocumentSnapshot document : querySnapshot) {
                                PlanMealItem meal = document.toObject(PlanMealItem.class);
                                if (meal != null) {
                                    meal.setIdMeal(document.getId());
                                    meals.add(meal);
                                }
                            }
                            return meals;
                        } else {
                            return new ArrayList<>();
                        }
                    } else {
                        throw task.getException();
                    }
                });
    }

    public void addAllPlanMealsInLocalDatabase(List<PlanMealItem> planMealItems) {
        for (PlanMealItem p : planMealItems) {
            dao.insertMeal(p).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new CompletableObserver() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {

                                }

                                @Override
                                public void onComplete() {
                                    Log.d("TAG", "onComplete: inserted ");
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.d("TAG", e.getMessage());
                                }
                            });
        }

    }


}
