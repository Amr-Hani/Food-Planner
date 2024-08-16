package com.example.foodplanner.search.search_by_country.view;

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

import com.example.foodplanner.R;
import com.example.foodplanner.model.CountryNameItem;
import com.example.foodplanner.search.SearchFragmentDirections;

import java.util.List;

public class SearchByCountryNameAdapter extends RecyclerView.Adapter<SearchByCountryNameAdapter.SearchByCountryNameViewHolder>{
    Context context;
    List<CountryNameItem>countryNameItemList;

    public SearchByCountryNameAdapter(Context context, List<CountryNameItem> countryNameItemList) {
        this.context = context;
        this.countryNameItemList = countryNameItemList;
    }

    public void setData( List<CountryNameItem>countryNameItemList)
    {
        this.countryNameItemList = countryNameItemList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SearchByCountryNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.country_name_item,parent,false);
        SearchByCountryNameViewHolder searchByCountryNameViewHolder = new SearchByCountryNameViewHolder(view);
        return searchByCountryNameViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchByCountryNameViewHolder holder, int position) {
        CountryNameItem currntCountryNameItem = countryNameItemList.get(position);
        holder.name.setText(currntCountryNameItem.getStrArea());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: ana fe sear adapter" + currntCountryNameItem.getStrArea());
                SearchFragmentDirections.ActionSearchFragmentToMealByCuontryFragment action =
                    SearchFragmentDirections.actionSearchFragmentToMealByCuontryFragment(currntCountryNameItem.getStrArea());
                Navigation.findNavController(v).navigate(action);

            }
        });
    }

    @Override
    public int getItemCount() {
        return countryNameItemList.size();
    }

    class SearchByCountryNameViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public SearchByCountryNameViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_countryname);
        }
    }
}
