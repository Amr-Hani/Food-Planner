package com.example.foodplanner.details.presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.database.DAO;
import com.example.foodplanner.database.MyRoomDB;
import com.example.foodplanner.details.view.DetailsMealView;
import com.example.foodplanner.model.DetailsMealItem;
import com.example.foodplanner.model.PlanMealItem;
import com.example.foodplanner.network.APIs;
import com.example.foodplanner.network.DetailsMealCallBack;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class DetailsMealPresenter implements DetailsMealCallBack  {
    APIs apIs;
    DetailsMealView detailsMealView;
    DAO dao;
    MyRoomDB myRoomDB;

    FirebaseFirestore db;

    public DetailsMealPresenter(DetailsMealView detailsMealView, Context context) {
        this.detailsMealView = detailsMealView;
        apIs = APIs.getInstance();
        myRoomDB = MyRoomDB.getInstance(context);
        dao = myRoomDB.mealsDAO();
        db = FirebaseFirestore.getInstance();
    }

    public void getDetailMeal(String detailsMeal) {
        apIs.makeNetworkCall(this, detailsMeal);
    }

    @Override
    public void onSuccessResult(List<DetailsMealItem> list) {
        detailsMealView.getDetailsMeal(list);
    }

    public void insert(DetailsMealItem detailsMealItem){
        dao.insertMeal(detailsMealItem).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onComplete() {
                        Log.i("TAG", "onComplete: inserted");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
    public void insert(PlanMealItem planMealItem){
        dao.insertMeal(planMealItem).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onComplete() {
                        Log.i("TAG", "onComplete: inserted");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
    public Task<Void> insertIntoFavoriteFirebase(String uId,DetailsMealItem detailsMealItem)
    {
        return db.collection("users").document(uId).collection("meals").document(detailsMealItem.getIdMeal())
                .set(detailsMealItem).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSuccess: 3ezzat");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Exception e) {
                        Log.d("TAG", "onFailure: ");
                    }
                });
    }
    public Task<Void> insertIntoPlanMeaFirebase(String uId,PlanMealItem planMealItem)
    {
        return db.collection("users").document(uId).collection("plan").document(planMealItem.getIdMeal())
                .set(planMealItem).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSuccess: 3ezzat");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Exception e) {
                        Log.d("TAG", "onFailure: ");
                    }
                });
    }

    @Override
    public void onFailureResult(String mesg) {
        detailsMealView.getDetailsMealErrorMsg(mesg);
    }

   }
