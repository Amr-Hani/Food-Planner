package com.example.foodplanner.favorite.view;

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

import com.example.foodplanner.R;
import com.example.foodplanner.login.view.LoginActivity;
import com.example.foodplanner.favorite.presenter.FavoriteMealPresenter;
import com.example.foodplanner.home.view.HomeActivity;
import com.example.foodplanner.model.DetailsMealItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteMealView, OnFavoriteClickListner {

    FavoriteMealAdapter favoriteMealAdapter;
    FavoriteMealPresenter favoriteMealPresenter;
    RecyclerView rv_favorite;
    FirebaseAuth auth;
    public FavoriteFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        favoriteMealPresenter = new FavoriteMealPresenter(getContext(), this);
        rv_favorite = view.findViewById(R.id.rv_favoritemeal);
        favoriteMealPresenter.sendFavData();
    }

    @Override
    public void getFavoriteMeal(List<DetailsMealItem> list) {
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
            favoriteMealAdapter = new FavoriteMealAdapter(getContext(), list, this);
            rv_favorite.setAdapter(favoriteMealAdapter);
            rv_favorite.setLayoutManager(new LinearLayoutManager(this.getContext()));

        }
    }

    @Override
    public void getDetailsMealErrorMsg(String msg) {

    }

    @Override
    public void onFavClick(DetailsMealItem detailsMealItem) {
        Log.d("TAG", "onFavClick: fragment" + detailsMealItem.getStrMeal());
        favoriteMealPresenter.deleteMeal(detailsMealItem);
        favoriteMealPresenter.deleteFavMealsFromFirebase(auth.getUid(),detailsMealItem.getIdMeal());
    }
}