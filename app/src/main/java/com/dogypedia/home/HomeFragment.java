package com.dogypedia.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.dogypedia.MainActivity;
import com.dogypedia.R;
import com.dogypedia.listUi.ListActivity;
import com.dogypedia.ui.SettingsActivity;

public class HomeFragment extends Fragment {
    private HomeViewModel model;
    EditText breed;
    Button search;
    ImageView imageDog;
    View homeLayout;
    View homeLayoutScroll;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View routeView = inflater.inflate(R.layout.home_fragment, container, false);
        model = new ViewModelProvider(this).get(HomeViewModel.class);
        breed = routeView.findViewById(R.id.breed_editText);
        search = routeView.findViewById(R.id.search_button);
        imageDog = routeView.findViewById(R.id.random_dog);
        homeLayout = routeView.findViewById(R.id.homeLayout);
        homeLayoutScroll = routeView.findViewById(R.id.homeLayoutScroll);
        model.getColor().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String color) {
                if (color.equals("grey")) {
                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    homeLayoutScroll.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                }
                else {
                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    homeLayoutScroll.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                }
            }
        });

        model.getImageURL().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String url) {
                Glide.with(HomeFragment.this).load(url).into(imageDog);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.searchBreed(breed.getText().toString());
                Log.d("SearchButton", "Searching for breed...");
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            }
        });
        return routeView;
    }
}