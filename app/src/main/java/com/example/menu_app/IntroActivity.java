package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.menu_app.adapter.IntroViewAdapter;
import com.example.menu_app.model.ScreenItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    IntroViewAdapter introViewAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    TextView tvSkip;
    Animation btnAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (restorePrefData()){
            Intent intent = new Intent(IntroActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_intro);

        tabIndicator = findViewById(R.id.tab_indicator);
        btnNext = findViewById(R.id.btn_Next);
        btnGetStarted = findViewById(R.id.btn_getStarted);
        tvSkip = findViewById(R.id.tvSkip);
        btnAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_animation);

        //filling the view pager
        final List<ScreenItem> list = new ArrayList<>();
        list.add(new ScreenItem("Fresh Food", "All the foods present in our restaurant are freshly made. Food made from fresh ingredients bought from the \n" +
                "finest stores.", R.drawable.fresh_food));
        list.add(new ScreenItem("Safety Cooking", "All the foods are cooked by our professional chiefs with safety measures. All ingredients are freshly grown ingredients.", R.drawable.healthy));
        list.add(new ScreenItem("Choose Your Meal", "Select your meal from our large\n" +
                "collection of foods. We have the\n" +
                "best prices that you can find for many different categories of food.", R.drawable.choices));

        viewPager = findViewById(R.id.intro_viewpager);
        introViewAdapter = new IntroViewAdapter(this, list);
        viewPager.setAdapter(introViewAdapter);

        tabIndicator.setupWithViewPager(viewPager);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = viewPager.getCurrentItem();
                if (position < list.size()){
                    position++;
                    viewPager.setCurrentItem(position);
                }

                if (position == list.size()-1){
                    LoadLastScreen();
                }
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == list.size()-1) {
                    LoadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                savePrefsData();
                finish();
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(list.size());
            }
        });

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

    private void LoadLastScreen(){
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(btnAnimation);
    }

}
