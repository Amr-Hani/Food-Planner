package com.example.foodplanner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.DetailsMealItem;
import com.example.foodplanner.model.PlanMealItem;

@Database(entities = {DetailsMealItem.class, PlanMealItem.class},version = 2)
public abstract class MyRoomDB extends RoomDatabase {

    private static MyRoomDB instance = null;


    public abstract DAO mealsDAO();

    public static synchronized MyRoomDB getInstance(Context context)
    {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MyRoomDB.class,"myRoomDatabase")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}