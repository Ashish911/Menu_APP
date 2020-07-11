package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.example.menu_app.adapter.IntroViewAdapter;
import com.google.android.material.tabs.TabLayout;

public class IntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    IntroViewAdapter introViewAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (restorePrefData()){
            Intent intent = new Intent(IntroActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_intro);
    }

    private boolean restorePrefData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean hasIntroActivityOpenedBefore = preferences.getBoolean("isIntroOpened", false);
        return hasIntroActivityOpenedBefore;
    }

    private void savePrefsData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }


}
