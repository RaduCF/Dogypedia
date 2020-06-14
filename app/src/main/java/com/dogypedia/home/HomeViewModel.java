package com.dogypedia.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dogypedia.api.Repository;

public class HomeViewModel extends ViewModel {
    Repository repository;

    public HomeViewModel() {
        repository = Repository.getInstance();
    }

    public void searchBreed(String breed) {
        repository.requestBreeds(breed);
    }

    public LiveData<String> getImageURL() {
        return repository.getLocalImageURL();
    }

    public LiveData<String> getColor() {
        return repository.getColor();
    }
}
