package com.example.foodplanner.category.view;

import android.content.Context;
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
import com.example.foodplanner.home.view.HomeFragmentDirections;
import com.example.foodplanner.model.CategoryMealItem;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    Context context;
    List<CategoryMealItem>category;

    public CategoryAdapter(Context context, List<CategoryMealItem> category) {
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.category_meal,parent,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryMealItem currntCategoryMealItem = category.get(position);
        Glide.with(context).load(currntCategoryMealItem.getStrCategoryThumb()).into(holder.imageView);
        holder.title.setText(currntCategoryMealItem.getStrCategory());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentDirections.ActionHomeFragmentToMealsByCategoryFragment action =
                    HomeFragmentDirections.actionHomeFragmentToMealsByCategoryFragment(currntCategoryMealItem.getStrCategory());
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_categorymeal);
            title = itemView.findViewById(R.id.tv_categorytitle);
        }
    }
}
