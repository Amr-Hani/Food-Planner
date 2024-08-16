package com.example.foodplanner.search.search_by_ingredient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.IngredientNameItem;
import com.example.foodplanner.search.SearchFragment;
import com.example.foodplanner.search.SearchFragmentDirections;

import java.util.List;

public class SearchByIngredientNameAdapter extends RecyclerView.Adapter<SearchByIngredientNameAdapter.SearchByIngredientNameViewHolder>{

    Context context;
    List<IngredientNameItem> ingredientNameItemList;

    private static final String URL = "https://www.themealdb.com/images/ingredients/";
    private static final String END_POINT = "-Small.png";

    public SearchByIngredientNameAdapter(Context context, List<IngredientNameItem> ingredientNameItemList) {
        this.context = context;
        this.ingredientNameItemList = ingredientNameItemList;
    }

    public SearchByIngredientNameAdapter(Context context, SearchByIngredientNameView searchByIngredientNameView) {
        this.context = context;
    }
    public void setData(List<IngredientNameItem> ingredientNameItemList)
    {
        this.ingredientNameItemList = ingredientNameItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchByIngredientNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.ingredient_name_item,parent,false);
        SearchByIngredientNameViewHolder searchByIngredientNameViewHolder = new SearchByIngredientNameViewHolder(view);
        return searchByIngredientNameViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchByIngredientNameViewHolder holder, int position) {
        IngredientNameItem currntIngredientNameItem = ingredientNameItemList.get(position);
        holder.name.setText(currntIngredientNameItem.getStrIngredient());
        Glide.with(context).load(URL+currntIngredientNameItem.getStrIngredient()+END_POINT).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragmentDirections.ActionSearchFragmentToMealsByIngredientFragment2 action =
                    SearchFragmentDirections.actionSearchFragmentToMealsByIngredientFragment2(currntIngredientNameItem.getStrIngredient());
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientNameItemList.size();
    }

    protected class SearchByIngredientNameViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView img;
        CardView card;
        public SearchByIngredientNameViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_ingredient);
            img = itemView.findViewById(R.id.iv_ingredient);
            card = itemView.findViewById(R.id.cv_ingredient);
        }
    }
}
