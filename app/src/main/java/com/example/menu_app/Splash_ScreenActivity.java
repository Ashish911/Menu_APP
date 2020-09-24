package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_ScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        Handler handler = new Handler();
        handler.postAtTime(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_ScreenActivity.this,IntroActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
