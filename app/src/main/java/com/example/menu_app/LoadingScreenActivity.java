package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menu_app.api.UsersAPI;
import com.example.menu_app.model.Users;
import com.example.menu_app.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        final Call<Users> usersCall = usersAPI.getUserDetails(Url.token);

        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    if (response.body().isChief() == true){
                        tvUserType.setText("Welcome Chief");
                        ivWaiter.setVisibility(View.INVISIBLE);
                        tvUsername.setText(response.body().getUserName());
                    } else if (response.body().isWaiter() == true){
                        tvUserType.setText("Welcome Waiter");
                        ivChief.setVisibility(View.INVISIBLE);
                        tvUsername.setText(response.body().getUserName());
                    }
                    return;
                }
                else{
                    Toast.makeText(LoadingScreenActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(LoadingScreenActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
