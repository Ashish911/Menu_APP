package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menu_app.api.OrderAPI;
import com.example.menu_app.model.Order;
import com.example.menu_app.url.Url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    TextView tvFoodName,tvFoodPrice,tvQuantity,tvOrderPrice,tvOrderQuantity,tvTotalPrice;
    ImageButton imgPlus,imgMinus;
    ImageView FoodImage;
    EditText etFullName, etTableName;
    Button btnProceed;

    String FoodName, Price, img, Name, Table, qtt ;
    int Total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tvFoodName = findViewById(R.id.orderFoodName);
        tvFoodPrice = findViewById(R.id.orderFoodPrice);
        tvQuantity = findViewById(R.id.Quantity);
        tvOrderPrice = findViewById(R.id.OrderPrice);
        tvOrderQuantity = findViewById(R.id.OrderQuantity);
        tvTotalPrice = findViewById(R.id.TotalPrice);
        imgPlus = findViewById(R.id.imagePlus);
        imgMinus = findViewById(R.id.imageMinus);
        FoodImage = findViewById(R.id.OrderImage);
        etFullName = findViewById(R.id.FullName);
        etTableName = findViewById(R.id.TableName);
        btnProceed = findViewById(R.id.btnProceed);

        Intent intent = getIntent();
        FoodName = intent.getExtras().getString("name");
        Price = intent.getExtras().getString("price");
        img = intent.getExtras().getString("image");

        tvFoodName.setText(FoodName);
        tvFoodPrice.setText("RS " + Price);
        Picasso.get().load(Url.base_url_image + img).into(FoodImage);
        tvOrderPrice.setText("RS " + Price);

        qtt = tvQuantity.getText().toString();

        tvOrderQuantity.setText(qtt);

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(tvQuantity.getText().toString()) == 1){
                    Toast.makeText(OrderActivity.this, "Quantity cannot be lower than zero", Toast.LENGTH_SHORT).show();
                } else {
                    int qty = Integer.parseInt(tvQuantity.getText().toString());
                    int finalqty = qty - 1;
                    tvQuantity.setText("" + finalqty);
                    tvOrderQuantity.setText("" + finalqty);
                    Total = Integer.parseInt(Price) * finalqty;
                    tvTotalPrice.setText("RS " + Total);
                }
            }
        });

        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(tvQuantity.getText().toString());
                int finalqty = qty + 1;
                tvQuantity.setText("" + finalqty);
                tvOrderQuantity.setText("" + finalqty);
                Total = Integer.parseInt(Price) * finalqty;
                tvTotalPrice.setText("RS " + Total);
            }
        });


        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etFullName.getText())) {
                    etFullName.setError("Please Enter Full Name");
                    etFullName.requestFocus();
                } else if (TextUtils.isEmpty(etTableName.getText())) {
                    etTableName.setError("Please Enter Table Name");
                    etTableName.requestFocus();
                }

                String totalprice = tvTotalPrice.getText().toString();
                String TotalQuantity = tvOrderQuantity.getText().toString();
                Name = etFullName.getText().toString();
                Table = etTableName.getText().toString();

                Order order = new Order(FoodName,img,totalprice,TotalQuantity,Name,Table,false,false);

                OrderAPI orderAPI = Url.getInstance().create(OrderAPI.class);
                Call<Void> voidCall = orderAPI.order(order);
                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(OrderActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(OrderActivity.this, "Order successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(OrderActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                finish();
            }
        });

    }
}
