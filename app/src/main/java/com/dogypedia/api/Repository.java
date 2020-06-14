package com.dogypedia.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dogypedia.localStorage.Dao;
import com.dogypedia.model.BreedResponse;
import com.dogypedia.model.ImageResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static Repository instance;
    private Dao localStorage;
    private MutableLiveData<List<BreedResponse>> breedList;
    private MutableLiveData<String> imageURL;
    private MutableLiveData<String> imageDetailURL;

    private Repository() {
        localStorage = Dao.getInstance();
        breedList = new MutableLiveData<>();
        imageURL = new MutableLiveData<>();
        imageDetailURL = new MutableLiveData<>();
        requestImage();
    }

    public static Repository getInstance() {
        if (instance == null)
            instance = new Repository();
        return instance;
    }

    public LiveData<List<BreedResponse>> getBreeds() {
        return breedList;
    }

    public LiveData<String> getImageURL() {
        return imageURL;
    }

    public LiveData<String> getLocalImageURL() {
        return localStorage.getImageURL();
    }

    public LiveData<String> getColor() {
        return localStorage.getColor();
    }

    public void setColor(String color) {
        localStorage.setColor(color);
    }

    public MutableLiveData<String> getImageDetailURL() {
        return imageDetailURL;
    }

    public void requestBreeds(String breed) {
        DogAPI dogAPI = ServiceGenerator.getApi();
        Map<String, String> info = new HashMap<String, String>();
        info.put("api_key", "1da084b3-f931-475f-8378-50cb193ce658");
        info.put("q", breed);
        Call<List<BreedResponse>> call = dogAPI.getBreeds(info);
        call.enqueue(new Callback<List<BreedResponse>>() {
            @Override
            public void onResponse(Call<List<BreedResponse>> call, Response<List<BreedResponse>> response) {
                if (response.code() == 200) {
                    breedList.setValue(response.body());
                    Log.d("Retrofit", "Received breeds: " + response.body().isEmpty() + " , " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<BreedResponse>> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve breeds.");
            }
        });
    }

    public void requestImage() {
        DogAPI dogAPI = ServiceGenerator.getApi();
        Call<List<ImageResponse>> call = dogAPI.getRandImage();
        call.enqueue(new Callback<List<ImageResponse>>() {
            @Override
            public void onResponse(Call<List<ImageResponse>> call, Response<List<ImageResponse>> response) {
                if (response.code() == 200) {
                    imageURL.setValue(response.body().get(0).getUrl());
                    localStorage.changeImageURL(imageURL.getValue());
                    Log.d("Retrofit", "Received image");
                }
            }

            @Override
            public void onFailure(Call<List<ImageResponse>> call, Throwable t) {
                Log.i("Retrofit", "Could not retrieve image.");
            }
        });
    }

        public void requestDetailImage() {
            DogAPI dogAPI = ServiceGenerator.getApi();
            Call<List<ImageResponse>> call = dogAPI.getRandImage();
            call.enqueue(new Callback<List<ImageResponse>>() {
                @Override
                public void onResponse(Call<List<ImageResponse>> call, Response<List<ImageResponse>> response) {
                    if (response.code() == 200) {
                        imageDetailURL.setValue(response.body().get(0).getUrl());
                        Log.d("Retrofit", "Received image");
                    }
                }

                @Override
                public void onFailure(Call<List<ImageResponse>> call, Throwable t) {
                    Log.i("Retrofit", "Could not retrieve image.");
                }
            });
    }
}
