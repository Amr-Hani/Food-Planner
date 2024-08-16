package com.example.foodplanner.meal_by_country_name.view;

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
import com.example.foodplanner.model.MealByCountryItem;

import java.util.List;

public class MealByCountryNameAdapter extends RecyclerView.Adapter<MealByCountryNameAdapter.MealByCountryNameViewHolder> {
    Context context;
    List<MealByCountryItem> mealByCountryItemList;

    public MealByCountryNameAdapter(Context context, List<MealByCountryItem> mealByCountryItemList) {
        this.context = context;
        this.mealByCountryItemList = mealByCountryItemList;
    }

    @NonNull
    @Override
    public MealByCountryNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.meal_by_country_item, parent, false);
        MealByCountryNameViewHolder mealByCountryNameViewHolder = new MealByCountryNameViewHolder(view);
        return mealByCountryNameViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealByCountryNameViewHolder holder, int position) {
        MealByCountryItem currntMealByCountryItem = mealByCountryItemList.get(position);
        holder.title.setText(currntMealByCountryItem.getStrMeal());
        Glide.with(context).load(currntMealByCountryItem.getStrMealThumb()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.isGuest) {
                    new AlertDialog.Builder(context)
                            .setTitle("Log In")
                            .setMessage("Do you want signup?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
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
                    MealByCuontryFragmentDirections.ActionMealByCuontryFragmentToDatailMealFragment action =
                            MealByCuontryFragmentDirections.actionMealByCuontryFragmentToDatailMealFragment(currntMealByCountryItem.getStrMeal());
                    Navigation.findNavController(v).navigate(action);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealByCountryItemList.size();
    }

    protected class MealByCountryNameViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;

        public MealByCountryNameViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_mealsbyingredient);
            title = itemView.findViewById(R.id.tv_mealsbyingredienttitle);
        }
    }
}
