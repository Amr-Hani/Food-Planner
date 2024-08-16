package com.example.foodplanner.search.searchbycategory.view;

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
import com.example.foodplanner.model.CategoryMealItem;
import com.example.foodplanner.search.SearchFragmentDirections;

import java.util.List;

public class SearchByCategoryAdapter extends RecyclerView.Adapter<SearchByCategoryAdapter.SearchByCategoryViewHolder>{
    Context context;
    List<CategoryMealItem>category;

    public SearchByCategoryAdapter(Context context, List<CategoryMealItem> category) {
        this.context = context;
        this.category = category;
    }
    public void setData(List<CategoryMealItem>category)
    {
        this.category = category;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchByCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.search_by_category_meal,parent,false);
        SearchByCategoryViewHolder searchByCategoryViewHolder = new SearchByCategoryViewHolder(view);
        return searchByCategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchByCategoryViewHolder holder, int position) {
        CategoryMealItem currntCategoryMealItem = category.get(position);
        Glide.with(context).load(currntCategoryMealItem.getStrCategoryThumb()).into(holder.imageView);
        holder.title.setText(currntCategoryMealItem.getStrCategory());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragmentDirections.ActionSearchFragmentToMealsByCategoryFragment action =
                    SearchFragmentDirections.actionSearchFragmentToMealsByCategoryFragment(currntCategoryMealItem.getStrCategory());
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    class SearchByCategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        public SearchByCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_categorymeal);
            title = itemView.findViewById(R.id.tv_categorytitle);
        }
    }
}
