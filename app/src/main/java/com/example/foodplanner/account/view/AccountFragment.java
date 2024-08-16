package com.example.foodplanner.account.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.account.presenter.AccountPresenter;
import com.example.foodplanner.login.view.LoginActivity;
import com.example.foodplanner.home.view.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AccountFragment extends Fragment {
    TextView logOut;

    FirebaseAuth auth;
    AccountPresenter accountPresenter;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        accountPresenter = new AccountPresenter(getContext());
        logOut = view.findViewById(R.id.tv_logout);
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
            logOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    auth.signOut();
                }
            });

            auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (auth.getCurrentUser() == null) {
                        accountPresenter.deleteAllFavoriteFromDatabase();
                        accountPresenter.deleteAllPlaneMealFromDatabase();
                        if (getContext() != null) {
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            Log.e("AccountFragment", "Context is null");
                        }

                    }
                }
            });
        }
    }


}

