package com.example.foodplanner.plane.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.login.view.LoginActivity;
import com.example.foodplanner.model.PlanMealItem;

import java.util.List;

public class PlanMealAdapter extends RecyclerView.Adapter<PlanMealAdapter.PlanMeaViewHolder> {
    Context context;
    List<PlanMealItem> planMealItemList;
    DeleteFromPlanListner deleteFromPlanListner;


    public PlanMealAdapter(Context context, List<PlanMealItem> planMealItemList, DeleteFromPlanListner deleteFromPlanListner) {
        this.context = context;
        this.planMealItemList = planMealItemList;
        this.deleteFromPlanListner = deleteFromPlanListner;
    }

    @NonNull
    @Override
    public PlanMeaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.plan_meal_item, parent, false);
        PlanMeaViewHolder planMeaViewHolder = new PlanMeaViewHolder(view);
        return planMeaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanMeaViewHolder holder, int position) {
        PlanMealItem currntPlanMealItem = planMealItemList.get(position);
        holder.title.setText(currntPlanMealItem.getStrMeal());
        Glide.with(context).load(currntPlanMealItem.getStrMealThumb()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.isGuest) {
                    Toast.makeText(context, "Please Sign Up", Toast.LENGTH_LONG).show();
                } else {
                    CalenderFragmentDirections.ActionCalenderFragmentToDatailMealFragment action =
                            CalenderFragmentDirections.actionCalenderFragmentToDatailMealFragment(currntPlanMealItem.getStrMeal());
                    Navigation.findNavController(v).navigate(action);
                }
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFromPlanListner.onDeleteClick(currntPlanMealItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return planMealItemList.size();
    }

    protected class PlanMeaViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        ImageView btn_delete;

        public PlanMeaViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_planmeal);
            imageView = itemView.findViewById(R.id.iv_planmeal);
            btn_delete = itemView.findViewById(R.id.iv_deleteplanemal);
        }
    }
}
