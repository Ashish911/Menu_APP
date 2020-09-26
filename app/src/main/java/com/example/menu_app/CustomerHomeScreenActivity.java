package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.menu_app.adapter.CategoryAdapter;
import com.example.menu_app.adapter.FoodAdapter;
import com.example.menu_app.adapter.SliderAdapter;
import com.example.menu_app.api.CategoryAPI;
import com.example.menu_app.api.FoodAPI;
import com.example.menu_app.model.Category;
import com.example.menu_app.model.Food;
import com.example.menu_app.url.Url;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerHomeScreenActivity extends AppCompatActivity {

    SliderView sliderView;
    RecyclerView categoryRecyclerView, foodRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home_screen);

        sliderView = findViewById(R.id.Slider);
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        foodRecyclerView = findViewById(R.id.foodRecycleView);


        final SliderAdapter adapter = new SliderAdapter(getBaseContext());
        adapter.setCount(3);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();

        getCategory();
        getFoods();
    }

    private void getCategory(){
        CategoryAPI categoryAPI = Url.getInstance().create(CategoryAPI.class);
        Call<List<Category>> listCall = categoryAPI.getCategory();
        listCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getBaseContext(), "Toast" + response.code(), Toast.LENGTH_SHORT).show();
                }
                CategoryAdapter categoryAdapter = new CategoryAdapter(response.body(), CustomerHomeScreenActivity.this);
                categoryRecyclerView.setAdapter(categoryAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CustomerHomeScreenActivity.this);
                linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
                categoryRecyclerView.setLayoutManager(linearLayoutManager);
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(CustomerHomeScreenActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFoods(){
        FoodAPI foodAPI = Url.getInstance().create(FoodAPI.class);
        Call<List<Food>> listCall = foodAPI.getFood();
        listCall.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getBaseContext(), "Toast" + response.code(), Toast.LENGTH_SHORT).show();
                }
                FoodAdapter foodAdapter = new FoodAdapter(response.body(),CustomerHomeScreenActivity.this);
                foodRecyclerView.setAdapter(foodAdapter);
                foodRecyclerView.setHasFixedSize(true);
                foodRecyclerView.setLayoutManager(new GridLayoutManager(CustomerHomeScreenActivity.this,2));
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(CustomerHomeScreenActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
