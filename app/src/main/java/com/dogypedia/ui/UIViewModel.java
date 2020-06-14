package com.dogypedia.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dogypedia.api.Repository;
import com.dogypedia.model.BreedResponse;

public class UIViewModel extends ViewModel {
    private Repository repository;

    public UIViewModel() {
        repository = Repository.getInstance();
    }

    public void changeImage() {
        repository.requestImage();
    }

    public BreedResponse getBreedInfo(int index) {
        return repository.getBreeds().getValue().get(index);
    }

    public LiveData<String> getColor() {
        return repository.getColor();
    }

    public void setNewColor(String color) {
        repository.setColor(color);
    }

    public LiveData<String> getDetailImage() {
        return repository.getImageDetailURL();
    }
}
