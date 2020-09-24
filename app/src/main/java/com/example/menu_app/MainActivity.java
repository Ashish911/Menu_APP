package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
            }
        });

    }
}
