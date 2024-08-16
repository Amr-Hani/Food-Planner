package com.example.foodplanner.home.view;

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
import com.example.foodplanner.model.MealsOfTheDayItem;

import java.util.List;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.HomeScreenViewHolder> {
    Context context;
    List<MealsOfTheDayItem> meals;

    public HomeScreenAdapter(Context context, List<MealsOfTheDayItem> meals) {
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public HomeScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.mal_of_the_day, parent, false);
        HomeScreenViewHolder homeScreenViewHolder = new HomeScreenViewHolder(view);
        return homeScreenViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenViewHolder holder, int position) {
        MealsOfTheDayItem currntMealsOfTheDayItem = meals.get(position);
        Glide.with(context).load(currntMealsOfTheDayItem.getStrMealThumb()).into(holder.imageView);
        holder.title.setText(currntMealsOfTheDayItem.getStrMeal());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.isGuest) {
                    new AlertDialog.Builder(context)
                            .setTitle("Sign Up Required")
                            .setMessage("Please sign up to proceed. Do you want to sign up now?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (context instanceof Activity) {
                                        ((Activity) context).finish();}
                                    Intent intent = new Intent(context, LoginActivity.class);
                                    context.startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                } else {
                    HomeFragmentDirections.ActionHomeFragmentToDatailMealFragment action =
                            HomeFragmentDirections.actionHomeFragmentToDatailMealFragment(currntMealsOfTheDayItem.getStrMeal());
                    Navigation.findNavController(v).navigate(action);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class HomeScreenViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public HomeScreenViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_mealday);
            title = itemView.findViewById(R.id.tv_mealofthedaytitle);
        }
    }
}
