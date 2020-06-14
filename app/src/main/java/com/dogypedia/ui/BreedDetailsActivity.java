package com.dogypedia.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.dogypedia.R;
import com.dogypedia.authentication.LoginActivity;
import com.dogypedia.model.BreedResponse;
import com.google.firebase.auth.FirebaseAuth;

public class BreedDetailsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private UIViewModel model;
    BreedResponse breedInfo;
    TextView breedName;
    TextView breedGroup;
    TextView temperament;
    TextView height;
    TextView weight;
    TextView lifeSpan;
    ImageView dogImage;
    View detailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_detais);
        model = new ViewModelProvider(this).get(UIViewModel.class);

        Toolbar toolbar = findViewById(R.id.details_toolbar);
        mAuth = FirebaseAuth.getInstance();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        breedInfo = model.getBreedInfo(getIntent().getIntExtra("index",0));

        breedName = findViewById(R.id.breed_detail_name);
        breedGroup = findViewById(R.id.breed_detail_group);
        temperament = findViewById(R.id.breed_detail_temperament);
        height = findViewById(R.id.breed_detail_height);
        weight = findViewById(R.id.breed_detail_weight);
        lifeSpan = findViewById(R.id.breed_detail_lifespan);
        dogImage = findViewById(R.id.image_dog);

        breedName.setText(breedInfo.getName());
        breedGroup.setText(breedInfo.getBreed_group());
        temperament.setText(breedInfo.getTemperament());
        height.setText(breedInfo.getHeight().getMetric() + " cm");
        weight.setText(breedInfo.getWeight().getMetric() + " kg");
        lifeSpan.setText(breedInfo.getLife_span() + " ages");

        model.getDetailImage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String url) {
                Glide.with(BreedDetailsActivity.this).load(url).into(dogImage);
            }
        });

        detailsLayout = findViewById(R.id.detailsLayout);
        model.getColor().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String color) {
                if (color.equals("grey"))
                    detailsLayout.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                else
                    detailsLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            }
        });
    }

        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_settings:
                    Intent intentSettings = new Intent(BreedDetailsActivity.this, SettingsActivity.class);
                    startActivity(intentSettings);
                    return true;
                case R.id.action_logout:
                    mAuth.signOut();
                    Intent intentOut = new Intent(BreedDetailsActivity.this, LoginActivity.class);
                    startActivity(intentOut);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
}