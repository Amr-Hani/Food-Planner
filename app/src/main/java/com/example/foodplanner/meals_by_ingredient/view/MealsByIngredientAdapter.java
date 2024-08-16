package com.example.foodplanner.meals_by_ingredient.view;

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
import com.example.foodplanner.model.MealsByIngredientItem;

import java.util.List;

public class MealsByIngredientAdapter extends RecyclerView.Adapter<MealsByIngredientAdapter.MealsByIngredientViewHolder> {

    Context context;
    List<MealsByIngredientItem> mealsByIngredientItemList;

    public MealsByIngredientAdapter(Context context, List<MealsByIngredientItem> mealsByIngredientItemList) {
        this.context = context;
        this.mealsByIngredientItemList = mealsByIngredientItemList;
    }

    @NonNull
    @Override
    public MealsByIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.meals_by_ingredient_item, parent, false);
        MealsByIngredientViewHolder mealsByIngredientViewHolder = new MealsByIngredientViewHolder(view);
        return mealsByIngredientViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealsByIngredientViewHolder holder, int position) {
        MealsByIngredientItem currntMealsByIngredientItem = mealsByIngredientItemList.get(position);
        holder.title.setText(currntMealsByIngredientItem.getStrMeal());
        Glide.with(context).load(currntMealsByIngredientItem.getStrMealThumb()).into(holder.imageView);
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
                    MealsByIngredientFragmentDirections.ActionMealsByIngredientFragmentToDatailMealFragment action =
                            MealsByIngredientFragmentDirections.actionMealsByIngredientFragmentToDatailMealFragment(currntMealsByIngredientItem.getStrMeal());
                    Navigation.findNavController(v).navigate(action);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealsByIngredientItemList.size();
    }

    protected class MealsByIngredientViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public MealsByIngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_mealsbyingredient);
            title = itemView.findViewById(R.id.tv_mealsbyingredienttitle);

        }
    }
}
