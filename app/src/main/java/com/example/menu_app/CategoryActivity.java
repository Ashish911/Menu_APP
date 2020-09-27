package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menu_app.adapter.FoodAdapter;
import com.example.menu_app.api.FoodAPI;
import com.example.menu_app.model.Food;
import com.example.menu_app.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    ImageButton Back;
    RecyclerView recyclerView;
    TextView tvCategoryName;
    ImageView itemImage;
    String categoryid;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView = findViewById(R.id.categoryFoodView);
        Back = findViewById(R.id.backF);
        tvCategoryName = findViewById(R.id.tvCategorySection);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, CustomerHomeScreenActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            categoryid = bundle.getString("categoryid");
            name = bundle.getString("name");
        }

        tvCategoryName.setText(name);

        FoodAPI foodAPI = Url.getInstance().create(FoodAPI.class);
        Call<List<Food>> listCall = foodAPI.getFoodByID(categoryid);
        listCall.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                FoodAdapter foodAdapter = new FoodAdapter(response.body(), CategoryActivity.this);
                recyclerView.setAdapter(foodAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(CategoryActivity.this, 1));
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
