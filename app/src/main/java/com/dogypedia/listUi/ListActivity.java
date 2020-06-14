package com.dogypedia.listUi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dogypedia.R;
import com.dogypedia.authentication.LoginActivity;
import com.dogypedia.model.BreedResponse;
import com.dogypedia.ui.BreedDetailsActivity;
import com.dogypedia.ui.SettingsActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements OnListItemClickListener {
    ListAdapter adapter;
    MutableLiveData<List<BreedResponse>> breeds;
    ListViewModel model;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breedlist);
        model = new ViewModelProvider(this).get(ListViewModel.class);

        Toolbar toolbar = findViewById(R.id.list_toolbar);
        mAuth = FirebaseAuth.getInstance();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView breedList = findViewById(R.id.lRecyclerView);
        breedList.setLayoutManager(new LinearLayoutManager(this));
        breeds = new MutableLiveData<>(new ArrayList<>());


        model.getBreedList().observe(this, new Observer<List<BreedResponse>>() {
            @Override
            public void onChanged(List<BreedResponse> breedResponses) {
                breeds.setValue(breedResponses);
                adapter = new ListAdapter(breeds.getValue(), ListActivity.this);
                breedList.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        model.newImageForItem();
        Intent intent = new Intent(this, BreedDetailsActivity.class);
        intent.putExtra("index", clickedItemIndex);
        startActivity(intent);
        Log.d("RecyclerView","Clicked on item "+ clickedItemIndex);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intentSettings = new Intent(ListActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                return true;
            case R.id.action_logout:
                mAuth.signOut();
                Intent intentOut = new Intent(ListActivity.this, LoginActivity.class);
                startActivity(intentOut);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
