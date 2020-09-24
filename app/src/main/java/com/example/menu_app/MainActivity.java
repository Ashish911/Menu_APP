package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menu_app.bll.LoginBll;
import com.example.menu_app.strictmode.StrictModeClass;

public class MainActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    Button btnLogin;
    TextView tvcustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        tvcustomer = findViewById(R.id.customer);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etEmail.getText())) {
                    etEmail.setError("Enter Email");
                    return;
                } else if (TextUtils.isEmpty(etPassword.getText())) {
                    etPassword.setError("Enter Password");
                    return;
                }

                String Email = String.valueOf(etEmail.getText());
                String Password = String.valueOf(etPassword.getText());

                LoginBll loginBll = new LoginBll();

                StrictModeClass.StrictMode();
                if (loginBll.checkUser(Email, Password)) {
                    Intent intent = new Intent(MainActivity.this, LoadingScreenActivity.class);
                    startActivity(intent);
                    SaveUsernamePassword();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
                    etEmail.requestFocus();
                }
            }
        });

    }

    private void SaveUsernamePassword(){
        SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString("email",etEmail.getText().toString().trim());
        editor.putString("password",etPassword.getText().toString().trim());
        editor.commit();
    }
}
