package com.example.foodplanner.favorite.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.DetailsMealItem;

import java.util.List;

public class FavoriteMealAdapter extends RecyclerView.Adapter<FavoriteMealAdapter.favoriteMealViewHolder>{
    Context context;
    List<DetailsMealItem>meals;
    OnFavoriteClickListner onFavoriteClickListner;

    public FavoriteMealAdapter(Context context, List<DetailsMealItem> meal,OnFavoriteClickListner onFavoriteClickListner) {
        this.context = context;
        this.meals = meal;
        this.onFavoriteClickListner = onFavoriteClickListner;
    }

    @NonNull
    @Override
    public favoriteMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.favorite_meal,parent,false);
        favoriteMealViewHolder favoriteMealViewHolder = new favoriteMealViewHolder(view);
        return favoriteMealViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull favoriteMealViewHolder holder, int position) {
        DetailsMealItem currntDetailsMealItem = meals.get(position);
        Glide.with(context).load(currntDetailsMealItem.getStrMealThumb()).into(holder.imageView);
        holder.title.setText(currntDetailsMealItem.getStrMeal());
       holder.imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FavoriteFragmentDirections.ActionFavoriteFragmentToDatailMealFragment action =
                       FavoriteFragmentDirections.actionFavoriteFragmentToDatailMealFragment(currntDetailsMealItem.getStrMeal());
               Navigation.findNavController(v).navigate(action);
           }
       });
       holder.iv_delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.d("TAG", "onDeleteClick: "+currntDetailsMealItem.getStrMeal());
                onFavoriteClickListner.onFavClick(currntDetailsMealItem);
           }
       });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class favoriteMealViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageView iv_delete;
        TextView title;
        public favoriteMealViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_fvoritemeal);
            iv_delete = itemView.findViewById(R.id.btn_delete);
            title = itemView.findViewById(R.id.tv_favoritemealtitle);
        }
    }
}
