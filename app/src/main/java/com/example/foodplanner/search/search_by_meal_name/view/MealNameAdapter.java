package com.example.foodplanner.search.search_by_meal_name.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.MealNameItem;

import java.util.List;

public class MealNameAdapter extends RecyclerView.Adapter<MealNameAdapter.MealsNameViewHolder> {
    Context context;
    List<MealNameItem>mealNameItemList;

    public MealNameAdapter(Context context, List<MealNameItem> mealNameItemList) {
        this.context = context;
        this.mealNameItemList = mealNameItemList;
    }

    @NonNull
    @Override
    public MealsNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.meal_name_item,parent,false);
        MealsNameViewHolder mealsNameViewHolder = new MealsNameViewHolder(view);
        return mealsNameViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealsNameViewHolder holder, int position) {
        MealNameItem currntMealNameItem = mealNameItemList.get(position);
        holder.title.setText(currntMealNameItem.getStrMeal());
        Glide.with(context).load(currntMealNameItem.getStrMealThumb()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mealNameItemList.size();
    }

    protected class MealsNameViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView imageView;
        public MealsNameViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_mealname);
            imageView = itemView.findViewById(R.id.iv_mealname);
        }
    }
}
