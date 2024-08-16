package com.example.foodplanner.plane.view;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.example.foodplanner.R;
import com.example.foodplanner.login.view.LoginActivity;
import com.example.foodplanner.home.view.HomeActivity;
import com.example.foodplanner.model.PlanMealItem;
import com.example.foodplanner.plane.presenter.PlanMealPresenter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.List;

public class CalenderFragment extends Fragment implements PlanMealView , DeleteFromPlanListner {
    ImageView iv_calender;
    RecyclerView recyclerView;
    PlanMealAdapter planMealAdapter;
    PlanMealPresenter planMealPresenter;
    FirebaseAuth auth;
    public CalenderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calender, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        iv_calender = view.findViewById(R.id.iv_calender);
//        tv_calender = view.findViewById(R.id.tv_calender);
        planMealPresenter = new PlanMealPresenter(this,getContext());
        recyclerView = view.findViewById(R.id.rv_plan);
        iv_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.isGuest) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Sign Up Required")
                            .setMessage("Please sign up to proceed. Do you want to sign up now?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().finish();
                                    Intent intent = new Intent(getContext(), LoginActivity.class);
                                    getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().finish();
                                    Intent intent = new Intent(getContext(), HomeActivity.class);
                                    getContext().startActivity(intent);
                                    dialog.dismiss();
                                }
                            })
                            .show();
                } else {
                    final Calendar c = Calendar.getInstance();

                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            getContext(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    planMealPresenter.setData(String.valueOf(dayOfMonth));

                                }
                            },
                            year, month, day);
                    datePickerDialog.show();
                }
            }
        });
    }

    @Override
    public void getMealsPlan(List<PlanMealItem> list) {
        planMealAdapter = new PlanMealAdapter(getContext(),list , this );
        recyclerView.setAdapter(planMealAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void getMealsPlanErrorMsg(String errMsg) {

    }


    @Override
    public void onDeleteClick(PlanMealItem planMealItem) {
        planMealPresenter.deletePlanMeal(planMealItem);
        planMealPresenter.deletePlanMealFromFirebase(auth.getUid(),planMealItem.getIdMeal());

    }
}