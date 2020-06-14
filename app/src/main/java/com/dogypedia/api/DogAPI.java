package com.dogypedia.api;

import com.dogypedia.model.BreedResponse;
import com.dogypedia.model.ImageResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface DogAPI {
    @GET("breeds/search")
    Call<List<BreedResponse>> getBreeds(@QueryMap Map<String, String> info);

    @GET("images/search")
    Call<List<ImageResponse>> getRandImage();
}
