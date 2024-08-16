package com.example.foodplanner.details.view;

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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.login.view.LoginActivity;
import com.example.foodplanner.details.presenter.DetailsMealPresenter;
import com.example.foodplanner.home.view.HomeActivity;
import com.example.foodplanner.ingredient.view.IngredientmealAdapter;
import com.example.foodplanner.favorite.view.OnFavoriteClickListner;
import com.example.foodplanner.model.DetailsMealItem;
import com.example.foodplanner.model.PlanMealItem;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Calendar;
import java.util.List;

public class DatailMealFragment extends Fragment implements DetailsMealView {

    DetailsMealPresenter detailsMealPresenter;
    TextView title;
    TextView area;
    TextView category;
    TextView desc;
    TextView textView;
    ImageView img;
    ImageView favoriteImg;
    ImageView btn_calender;
    YouTubePlayer vid;
    RecyclerView rv_ingredient;
    YouTubePlayerView youTubePlayerView;
    DetailsMealItem detailsMealItem;
    PlanMealItem planMealItem;
    OnFavoriteClickListner onFavoriteClickListner;
    FirebaseAuth auth;
    public DatailMealFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_datail_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        detailsMealPresenter = new DetailsMealPresenter(this, this.getContext());
        String detailsMeal = DatailMealFragmentArgs.fromBundle(getArguments()).getDetailsMeal();
        auth = FirebaseAuth.getInstance();
        btn_calender = view.findViewById(R.id.btn_calender);
        detailsMealPresenter.getDetailMeal(detailsMeal);
        FirebaseApp.initializeApp(getContext());
        title = view.findViewById(R.id.tv_detailstitle);
        area = view.findViewById(R.id.tv_area);
        desc = view.findViewById(R.id.tv_detailsmealdesc);
        category = view.findViewById(R.id.tv_category);
        img = view.findViewById(R.id.iv_detailsmeal);
        favoriteImg = view.findViewById(R.id.btn_favorite);
        rv_ingredient = view.findViewById(R.id.rv_ingredient);
        youTubePlayerView = view.findViewById(R.id.yt_vid);
        getLifecycle().addObserver(youTubePlayerView);//>>
        favoriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.isGuest) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Log In")
                            .setMessage("Do you want signup?")
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

                    detailsMealPresenter.insertIntoFavoriteFirebase(auth.getUid(),detailsMealItem);
                    detailsMealPresenter.insert(detailsMealItem);
                    favoriteImg.setColorFilter(255);
                    favoriteImg.setBackgroundColor(255);
                }
            }
        });
        btn_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.isGuest) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Log In")
                            .setMessage("Do you want signup?")
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
                                    Log.d("TAG", "onDateSet: " + planMealItem.getIdMeal());
                                    planMealItem.setDay(dayOfMonth);
                                    detailsMealPresenter.insert(planMealItem);
                                    detailsMealPresenter.insertIntoPlanMeaFirebase(auth.getUid(),planMealItem);
                                }
                            },
                            year, month, day);
                    datePickerDialog.show();
                }
            }
        });
    }

    @Override
    public void getDetailsMeal(List<DetailsMealItem> list) {
        detailsMealItem = list.get(0);
        planMealItem = Convert.convert(detailsMealItem);
        title.setText(list.get(0).getStrMeal());
        desc.setText(list.get(0).getStrInstructions());
        area.setText(list.get(0).getStrArea());
        category.setText(list.get(0).getStrCategory());
        Glide.with(getContext()).load(list.get(0).getStrMealThumb()).into(img);

        IngredientmealAdapter ingredientmealAdapter = new IngredientmealAdapter(getContext(), list);
        rv_ingredient.setAdapter(ingredientmealAdapter);
        rv_ingredient.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoUrl = list.get(0).getStrYoutube();
                String videoId = videoUrl.substring(videoUrl.lastIndexOf("v=") + 2);
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }

    @Override
    public void getDetailsMealErrorMsg(String msg) {

    }
}