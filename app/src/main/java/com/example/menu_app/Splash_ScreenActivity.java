package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_ScreenActivity extends AppCompatActivity {

    Animation btnAnimation;
    ImageView splashimage;
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        splashimage = findViewById(R.id.splash_image);
        tvContent = findViewById(R.id.splash_content);
        btnAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_animation);

        splashimage.setAnimation(btnAnimation);
        tvContent.setAnimation(btnAnimation);
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
