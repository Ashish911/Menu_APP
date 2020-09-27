package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.menu_app.url.Url;
import com.squareup.picasso.Picasso;

public class FoodDisplayActivity extends AppCompatActivity {

    private TextView tvName, tvPrice, tag1, tag2, tvDescription;
    private ImageView imgFood;
    private Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_display);

        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvFoodPrice);
        tag1 = findViewById(R.id.tag1);
        tag2 = findViewById(R.id.tag2);
        imgFood = findViewById(R.id.displayFoodImage);
        tvDescription = findViewById(R.id.tvFoodDescription);

        Intent intent = getIntent();
        final String Name = intent.getExtras().getString("Name");
        final String Price = intent.getExtras().getString("Price");
        final String img = intent.getExtras().getString("Image");
        final String Foodid = intent.getExtras().getString("id");
        final String Tag1 = intent.getExtras().getString("Tag");
        final String Tag2 = intent.getExtras().getString("Tag2");
        final String Description = intent.getExtras().getString("Description");

        tvName.setText(Name);
        tag1.setText(Tag1);
        tag2.setText(Tag2);
        tvDescription.setText(Description);
        tvPrice.setText("RS " + Price);
        Picasso.get().load(Url.base_url_image + img).into(imgFood);


    }
}
