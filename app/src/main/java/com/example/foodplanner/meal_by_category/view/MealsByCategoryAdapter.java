package com.example.foodplanner.meal_by_category.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.login.view.LoginActivity;
import com.example.foodplanner.model.MealsByCategoryItem;

import java.util.List;

public class MealsByCategoryAdapter extends RecyclerView.Adapter<MealsByCategoryAdapter.MealsByCategoryViewHolder> {
    Context context;
    List<MealsByCategoryItem> meals;

    public MealsByCategoryAdapter(Context context, List<MealsByCategoryItem> meals) {
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealsByCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.meals_by_category_item,parent,false);
        MealsByCategoryViewHolder mealsByCategoryViewHolder=new MealsByCategoryViewHolder(view);
        return mealsByCategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealsByCategoryViewHolder holder, int position) {
        MealsByCategoryItem currntMealsByCategoryItem = meals.get(position);
        holder.title.setText(currntMealsByCategoryItem.getStrMeal());
        Glide.with(context).load(currntMealsByCategoryItem.getStrMealThumb()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (LoginActivity.isGuest) {
//                    new AlertDialog.Builder(context)
//                            .setTitle("Log In")
//                            .setMessage("Do you want signup?")
//                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    if (context instanceof Activity) {
//                                        ((Activity) context).finish();
//                                    }
//                                    Intent intent = new Intent(context, LoginActivity.class);
//                                    context.startActivity(intent);
//                                }
//                            })
//                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            })
//                            .show();
//                } else {
                    MealsByCategoryFragmentDirections.ActionMealsByCategoryFragmentToDatailMealFragment action=
                            MealsByCategoryFragmentDirections.actionMealsByCategoryFragmentToDatailMealFragment(currntMealsByCategoryItem.getStrMeal());
                    Navigation.findNavController(v).navigate(action);
               // }
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    protected class MealsByCategoryViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView imageView;
        public MealsByCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_mealsbyingredienttitle);
            imageView = itemView.findViewById(R.id.iv_mealsbyingredient);
        }
    }
}
