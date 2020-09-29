package com.example.menu_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.menu_app.ui.AccountFragment;
import com.example.menu_app.ui.CompletedFragment;
import com.example.menu_app.ui.OrdersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WaiterDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_dashboard);

        BottomNavigationView bottomNavigationView = findViewById(R.id.WaiterBottomNavigation);

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) {
            String name = bundle.getString("name");
            if (name.equals("home")) {
                bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
                openFragment(new OrdersFragment());
            }
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.waiter_frame_container, new OrdersFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.navigation_order:
                    selectedFragment = new OrdersFragment();
                    break;
                case R.id.navigation_completed:
                    selectedFragment = new CompletedFragment();
                    break;
                case R.id.navigation_account:
                    selectedFragment = new AccountFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.waiter_frame_container, selectedFragment).commit();
            return true;
        }
    };

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.waiter_frame_container, fragment);
        transaction.commit();
    }
}
