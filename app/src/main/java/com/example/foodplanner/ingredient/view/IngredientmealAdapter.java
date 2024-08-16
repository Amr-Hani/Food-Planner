package com.example.foodplanner.ingredient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.DetailsMealItem;

import java.util.List;

public class IngredientmealAdapter extends RecyclerView.Adapter<IngredientmealAdapter.IngredientMealViewHolder> {
    Context context;
    List<DetailsMealItem> meals;
    private static final String URL = "https://www.themealdb.com/images/ingredients/";
    private static final String END_POINT = "-Small.png";

    public IngredientmealAdapter(Context context, List<DetailsMealItem> meals) {
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public IngredientMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.igredient_meal, parent, false);
        IngredientMealViewHolder ingredientMealViewHolder = new IngredientMealViewHolder(view);
        return ingredientMealViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientMealViewHolder holder, int position) {
        DetailsMealItem currntDetailsMealItem = meals.get(position);

        if (!currntDetailsMealItem.getStrIngredient1().isEmpty() && !currntDetailsMealItem.getStrIngredient1().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient1() + END_POINT)
                    .into(holder.imageView1);
            holder.ingredient1.setText(currntDetailsMealItem.getStrIngredient1());
        } else {
            holder.cv_ingredient1.setVisibility(View.GONE);
        }

        if (!currntDetailsMealItem.getStrIngredient2().isEmpty() && !currntDetailsMealItem.getStrIngredient2().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient2() + END_POINT)
                    .into(holder.imageView2);
            holder.ingredient2.setText(currntDetailsMealItem.getStrIngredient2());
        } else {
            holder.cv_ingredient2.setVisibility(View.GONE);
        }

        if (!currntDetailsMealItem.getStrIngredient3().isEmpty() && !currntDetailsMealItem.getStrIngredient3().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient3() + END_POINT)
                    .into(holder.imageView3);
            holder.ingredient3.setText(currntDetailsMealItem.getStrIngredient3());
        } else {
            holder.cv_ingredient3.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient4().isEmpty() && !currntDetailsMealItem.getStrIngredient4().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient4() + END_POINT)
                    .into(holder.imageView4);
            holder.ingredient4.setText(currntDetailsMealItem.getStrIngredient4());
        } else {
            holder.cv_ingredient4.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient5().isEmpty() && !currntDetailsMealItem.getStrIngredient5().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient5() + END_POINT)
                    .into(holder.imageView5);
            holder.ingredient5.setText(currntDetailsMealItem.getStrIngredient5());
        } else {
            holder.cv_ingredient5.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient6().isEmpty() && !currntDetailsMealItem.getStrIngredient6().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient6() + END_POINT)
                    .into(holder.imageView6);
            holder.ingredient6.setText(currntDetailsMealItem.getStrIngredient6());
        } else {
            holder.cv_ingredient6.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient7().isEmpty() && !currntDetailsMealItem.getStrIngredient7().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient7() + END_POINT)
                    .into(holder.imageView7);
            holder.ingredient7.setText(currntDetailsMealItem.getStrIngredient7());
        } else {
            holder.cv_ingredient7.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient8().isEmpty() && !currntDetailsMealItem.getStrIngredient8().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient8() + END_POINT)
                    .into(holder.imageView8);
            holder.ingredient8.setText(currntDetailsMealItem.getStrIngredient8());
        } else {
            holder.cv_ingredient8.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient9().isEmpty() && !currntDetailsMealItem.getStrIngredient9().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient9() + END_POINT)
                    .into(holder.imageView9);
            holder.ingredient9.setText(currntDetailsMealItem.getStrIngredient9());
        } else {
            holder.cv_ingredient9.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient10().isEmpty() && !currntDetailsMealItem.getStrIngredient10().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient10() + END_POINT)
                    .into(holder.imageView10);
            holder.ingredient10.setText(currntDetailsMealItem.getStrIngredient10());
        } else {
            holder.cv_ingredient10.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient11().isEmpty() && !currntDetailsMealItem.getStrIngredient11().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient11() + END_POINT)
                    .into(holder.imageView11);
            holder.ingredient11.setText(currntDetailsMealItem.getStrIngredient11());
        } else {
            holder.cv_ingredient11.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient12().isEmpty() && !currntDetailsMealItem.getStrIngredient12().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient12() + END_POINT)
                    .into(holder.imageView12);
            holder.ingredient12.setText(currntDetailsMealItem.getStrIngredient12());
        } else {
            holder.cv_ingredient12.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient13().isEmpty() && !currntDetailsMealItem.getStrIngredient13().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient13() + END_POINT)
                    .into(holder.imageView13);
            holder.ingredient13.setText(currntDetailsMealItem.getStrIngredient13());
        } else {
            holder.cv_ingredient13.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient14().isEmpty() && !currntDetailsMealItem.getStrIngredient14().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient14() + END_POINT)
                    .into(holder.imageView14);
            holder.ingredient14.setText(currntDetailsMealItem.getStrIngredient14());
        } else {
            holder.cv_ingredient14.setVisibility(View.GONE);
        }
        if (!currntDetailsMealItem.getStrIngredient15().isEmpty() && !currntDetailsMealItem.getStrIngredient15().equals(null)) {
            Glide.with(context)
                    .load(URL + currntDetailsMealItem.getStrIngredient3() + END_POINT)
                    .into(holder.imageView15);
            holder.ingredient15.setText(currntDetailsMealItem.getStrIngredient15());
        } else {
            holder.cv_ingredient15.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    protected class IngredientMealViewHolder extends RecyclerView.ViewHolder {

        TextView ingredient1;
        ImageView imageView1;
        CardView cv_ingredient1;
        TextView ingredient2;
        ImageView imageView2;
        CardView cv_ingredient2;

        TextView ingredient3;
        ImageView imageView3;
        CardView cv_ingredient3;

        TextView ingredient4;
        ImageView imageView4;
        CardView cv_ingredient4;

        TextView ingredient5;
        ImageView imageView5;
        CardView cv_ingredient5;

        TextView ingredient6;
        ImageView imageView6;
        CardView cv_ingredient6;

        TextView ingredient7;
        ImageView imageView7;
        CardView cv_ingredient7;

        TextView ingredient8;
        ImageView imageView8;
        CardView cv_ingredient8;

        TextView ingredient9;
        ImageView imageView9;
        CardView cv_ingredient9;

        TextView ingredient10;
        ImageView imageView10;
        CardView cv_ingredient10;

        TextView ingredient11;
        ImageView imageView11;
        CardView cv_ingredient11;

        TextView ingredient12;
        ImageView imageView12;
        CardView cv_ingredient12;

        TextView ingredient13;
        ImageView imageView13;
        CardView cv_ingredient13;

        TextView ingredient14;
        ImageView imageView14;
        CardView cv_ingredient14;

        TextView ingredient15;
        ImageView imageView15;
        CardView cv_ingredient15;

        public IngredientMealViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredient1 = itemView.findViewById(R.id.tv_ingredienttitle1);
            imageView1 = itemView.findViewById(R.id.iv_ingredient1);
            cv_ingredient1 = itemView.findViewById(R.id.cv_ingredient1);

            ingredient2 = itemView.findViewById(R.id.tv_ingredienttitle2);
            imageView2 = itemView.findViewById(R.id.iv_ingredient2);
            cv_ingredient2 = itemView.findViewById(R.id.cv_ingredient2);

            ingredient3 = itemView.findViewById(R.id.tv_ingredienttitle3);
            imageView3 = itemView.findViewById(R.id.iv_ingredient3);
            cv_ingredient3 = itemView.findViewById(R.id.cv_ingredient3);

            ingredient4 = itemView.findViewById(R.id.tv_ingredienttitle4);
            imageView4 = itemView.findViewById(R.id.iv_ingredient4);
            cv_ingredient4 = itemView.findViewById(R.id.cv_ingredient4);

            ingredient5 = itemView.findViewById(R.id.tv_ingredienttitle5);
            imageView5 = itemView.findViewById(R.id.iv_ingredient5);
            cv_ingredient5 = itemView.findViewById(R.id.cv_ingredient5);

            ingredient6 = itemView.findViewById(R.id.tv_ingredienttitle6);
            imageView6 = itemView.findViewById(R.id.iv_ingredient6);
            cv_ingredient6 = itemView.findViewById(R.id.cv_ingredient6);

            ingredient7 = itemView.findViewById(R.id.tv_ingredienttitle7);
            imageView7 = itemView.findViewById(R.id.iv_ingredient7);
            cv_ingredient7 = itemView.findViewById(R.id.cv_ingredient7);

            ingredient8 = itemView.findViewById(R.id.tv_ingredienttitle8);
            imageView8 = itemView.findViewById(R.id.iv_ingredient8);
            cv_ingredient8 = itemView.findViewById(R.id.cv_ingredient8);

            ingredient9 = itemView.findViewById(R.id.tv_ingredienttitle9);
            imageView9 = itemView.findViewById(R.id.iv_ingredient9);
            cv_ingredient9 = itemView.findViewById(R.id.cv_ingredient9);
            ingredient10 = itemView.findViewById(R.id.tv_ingredienttitle10);
            imageView10 = itemView.findViewById(R.id.iv_ingredient10);
            cv_ingredient10 = itemView.findViewById(R.id.cv_ingredient10);
            ingredient11 = itemView.findViewById(R.id.tv_ingredienttitle11);
            imageView11 = itemView.findViewById(R.id.iv_ingredient11);
            cv_ingredient11 = itemView.findViewById(R.id.cv_ingredient11);
            ingredient12 = itemView.findViewById(R.id.tv_ingredienttitle12);
            imageView12 = itemView.findViewById(R.id.iv_ingredient12);
            cv_ingredient12 = itemView.findViewById(R.id.cv_ingredient12);
            ingredient13 = itemView.findViewById(R.id.tv_ingredienttitle13);
            imageView13 = itemView.findViewById(R.id.iv_ingredient13);
            cv_ingredient13 = itemView.findViewById(R.id.cv_ingredient13);
            ingredient14 = itemView.findViewById(R.id.tv_ingredienttitle14);
            imageView14 = itemView.findViewById(R.id.iv_ingredient14);
            cv_ingredient14 = itemView.findViewById(R.id.cv_ingredient14);
            ingredient15 = itemView.findViewById(R.id.tv_ingredienttitle15);
            imageView15 = itemView.findViewById(R.id.iv_ingredient15);
            cv_ingredient15 = itemView.findViewById(R.id.cv_ingredient15);
        }
    }

}
