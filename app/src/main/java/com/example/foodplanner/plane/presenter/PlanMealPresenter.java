package com.example.foodplanner.plane.presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.database.DAO;
import com.example.foodplanner.database.MyRoomDB;
import com.example.foodplanner.model.PlanMealItem;
import com.example.foodplanner.plane.view.PlanMealView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanMealPresenter {
    PlanMealView planMealView;
    Context context;
    DAO dao;
    MyRoomDB myRoomDB;
    FirebaseFirestore db;

    public PlanMealPresenter(PlanMealView planMealView, Context context) {
        this.planMealView = planMealView;
        this.context = context;
        myRoomDB=MyRoomDB.getInstance(context);
        dao=myRoomDB.mealsDAO();
        db = FirebaseFirestore.getInstance();
    }
    public  void setData(String day)
    {
        Log.d("TAG", "sendFavData: Method called");

        Flowable<List<PlanMealItem>> flowable = dao.getPlansByDay(day);
        Log.d("TAG", "sendFavData: Flowable created");

        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<List<PlanMealItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        Log.d("TAG", "onSubscribe: Subscribed");
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<PlanMealItem> detailsMealItems) {
                        Log.d("TAG", "onNext: Received " + detailsMealItems.size() + " products");
                        planMealView.getMealsPlan(detailsMealItems);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("TAG", "onError: Error occurred", t);
                        planMealView.getMealsPlanErrorMsg(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "onComplete: Completed");
                    }
                });
    }
    public Task<Void> deletePlanMealFromFirebase(String uid, String mealId) {
        return db.collection("users").document(uid)
                .collection("plan").document(mealId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSuccess: Meal deleted successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Exception e) {
                        Log.d("TAG", "onFailure: Failed to delete meal: " + e.getMessage());
                    }
                });
    }
    public void deletePlanMeal ( PlanMealItem planMealItem)
    {
        new Thread( )
        {
            @Override
            public void run() {
                super.run();
                dao.deletePlaneMeal(planMealItem.getIdMeal());
            }
        }.start();

    }
}
