package com.example.foodplanner.favorite.presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.database.DAO;
import com.example.foodplanner.database.MyRoomDB;
import com.example.foodplanner.favorite.view.FavoriteMealView;
import com.example.foodplanner.model.DetailsMealItem;
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

public class FavoriteMealPresenter {
    Context context;
    FavoriteMealView favoriteMealView;
    DAO dao;
    MyRoomDB myRoomDB;
    FirebaseFirestore db;

    public FavoriteMealPresenter(Context context, FavoriteMealView favoriteMealView) {
        this.context = context;
        this.favoriteMealView = favoriteMealView;
        myRoomDB = MyRoomDB.getInstance(context);
        dao = myRoomDB.mealsDAO();
        db = FirebaseFirestore.getInstance();
    }
    public void sendFavData() {
        Log.d("TAG", "sendFavData: Method called");

        Flowable<List<DetailsMealItem>> flowable = dao.getAllMeals();
        Log.d("TAG", "sendFavData: Flowable created");

        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<List<DetailsMealItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        Log.d("TAG", "onSubscribe: Subscribed");
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<DetailsMealItem> detailsMealItems) {
                        Log.d("TAG", "onNext: Received " + detailsMealItems.size() + " products");
                        favoriteMealView.getFavoriteMeal(detailsMealItems);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("TAG", "onError: Error occurred", t);
                        favoriteMealView.getDetailsMealErrorMsg(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "onComplete: Completed");
                    }
        });
    }
    public Task<Void> deleteFavMealsFromFirebase(String uid, String mealId) {
        return db.collection("users").document(uid)
                .collection("meals").document(mealId)
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
    public void deleteMeal(DetailsMealItem detailsMealItem)
    {
        new Thread()
        {
            @Override
            public void run() {
                super.run();
                dao.deleteMeal(detailsMealItem.getIdMeal());
            }
        }.start();
    }
}
