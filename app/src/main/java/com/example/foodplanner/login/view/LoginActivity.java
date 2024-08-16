package com.example.foodplanner.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;
import com.example.foodplanner.authentication.SignUp;
import com.example.foodplanner.home.view.HomeActivity;
import com.example.foodplanner.login.presenter.LogInPresenter;
import com.example.foodplanner.model.DetailsMealItem;
import com.example.foodplanner.model.PlanMealItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button btn_login;
    private FirebaseAuth mAuth;
    private TextView tv_goToSignUp;
    private TextView skip;
    public static boolean isGuest;
    LogInPresenter logInPresenter;
    public static List<DetailsMealItem> favoriteMeals;
    public static List<PlanMealItem> planMealItems;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();
        setupListeners();

        mAuth = FirebaseAuth.getInstance();
        logInPresenter = new LogInPresenter(this);
        isGuest = false;
    }

    private void initializeViews() {
        email = findViewById(R.id.te_email_login);
        password = findViewById(R.id.te_password_login);
        btn_login = findViewById(R.id.btn_login);
        tv_goToSignUp = findViewById(R.id.btn_gotosignup);
        skip = findViewById(R.id.tv_skip);
    }

    private void setupListeners() {
        btn_login.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            if (!userEmail.isEmpty() && !userPassword.isEmpty()) {
                signInWithEmailAndPassword(userEmail, userPassword);
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            }
        });

        tv_goToSignUp.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUp.class));
        });

        skip.setOnClickListener(v -> {
            isGuest = true;
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        });
    }

    private void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();

                    logInPresenter.getUserFavoriteMeals(mAuth.getUid()).addOnCompleteListener(new OnCompleteListener<List<DetailsMealItem>>() {
                        @Override
                        public void onComplete(@NonNull Task<List<DetailsMealItem>> task) {
                            if (task.isSuccessful()) {
                                favoriteMeals = task.getResult();
                                if (favoriteMeals != null && !favoriteMeals.isEmpty()) {
                                    logInPresenter.addAllFavoriteMealsInLocalDatabase(favoriteMeals);
                                    Log.d("TAG", "onComplete: login " + favoriteMeals.get(0).getStrMeal());
                                } else {
                                    Log.d("TAG", "No favorite meals found.");
                                }
                            } else {
                                Log.e("TAG", "Error retrieving meals", task.getException());
                                Toast.makeText(LoginActivity.this, "Failed to retrieve meals.", Toast.LENGTH_SHORT).show();
                            }

                            // Navigate to HomePageActivity
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    logInPresenter.getUserPlanMeals(mAuth.getUid()).addOnCompleteListener(new OnCompleteListener<List<PlanMealItem>>() {
                        @Override
                        public void onComplete(@NonNull Task<List<PlanMealItem>> task) {
                            if (task.isSuccessful()) {
                                planMealItems = task.getResult();
                                if (planMealItems != null && !planMealItems.isEmpty()) {
                                    logInPresenter.addAllPlanMealsInLocalDatabase(planMealItems);
                                    Log.d("TAG", "onComplete: login " + planMealItems.get(0).getStrMeal());
                                } else {
                                    Log.d("TAG", "No favorite meals found.");
                                }
                            } else {
                                Log.e("TAG", "Error retrieving meals", task.getException());
                                Toast.makeText(LoginActivity.this, "Failed to retrieve meals.", Toast.LENGTH_SHORT).show();
                            }

                            // Navigate to HomePageActivity
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
    }
}