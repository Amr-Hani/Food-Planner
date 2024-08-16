package com.example.foodplanner.account.presenter;

import android.content.Context;

import com.example.foodplanner.database.DAO;
import com.example.foodplanner.database.MyRoomDB;

public class AccountPresenter {
    DAO dao;
    MyRoomDB myRoomDB;
    Context context;
    public AccountPresenter(Context context) {
        myRoomDB = MyRoomDB.getInstance(context);
        dao = myRoomDB.mealsDAO();
        this.context=context;
    }
    public void deleteAllFavoriteFromDatabase()
    {
        new Thread()
        {
            @Override
            public void run() {
                super.run();
                dao.deleteAllFavoriteMeals();
            }
        }.start();
    }
    public void deleteAllPlaneMealFromDatabase()
    {
        new Thread()
        {
            @Override
            public void run() {
                super.run();
                dao.deleteAllPlanMeals();
            }
        }.start();
    }
}
