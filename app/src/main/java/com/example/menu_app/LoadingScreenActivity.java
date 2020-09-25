package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadingScreenActivity extends AppCompatActivity {

    TextView tvUserType, tvUsername;
    ImageView ivChief, ivWaiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        tvUserType = findViewById(R.id.userType);
        tvUsername = findViewById(R.id.userName);
        ivChief = findViewById(R.id.chiefPic);
        ivWaiter = findViewById(R.id.waiterPic);

        String a = "Waiter";
        if (a == "Waiter"){
            ivChief.setVisibility(View.INVISIBLE);
        }
    }
}
