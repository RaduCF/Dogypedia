package com.dogypedia.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dogypedia.R;
import com.dogypedia.authentication.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private UIViewModel model;
    CheckBox greyBox;
    CheckBox blueBox;
    Button buttonImage;
    View layoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        model = new ViewModelProvider(this).get(UIViewModel.class);

        Toolbar toolbar = findViewById(R.id.settings_toolbar);
        mAuth = FirebaseAuth.getInstance();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        greyBox = findViewById(R.id.checkBox_grey);
        blueBox = findViewById(R.id.checkBox_blue);
        if(model.getColor().getValue().equals("grey"))
            greyBox.setChecked(true);
        else
            blueBox.setChecked(true);
        greyBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    model.setNewColor("grey");
                    blueBox.setChecked(false);
                }
            }
        });

        blueBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    model.setNewColor("blue");
                    greyBox.setChecked(false);
                }
            }
        });

        layoutView = findViewById(R.id.settingsLayout);
        model.getColor().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String color) {
                if (color.equals("grey"))
                    layoutView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                else
                    layoutView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            }
        });

        buttonImage = findViewById(R.id.button_newImage);
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.changeImage();
                Toast.makeText(SettingsActivity.this, "Home image changed.", Toast.LENGTH_SHORT).show();
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

                return true;
            case R.id.action_logout:
                mAuth.signOut();
                Intent intentOut = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intentOut);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
