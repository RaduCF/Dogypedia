package com.dogypedia.listUi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dogypedia.api.Repository;
import com.dogypedia.model.BreedResponse;

import java.util.List;

public class ListViewModel extends ViewModel {
    private Repository repository;

    public ListViewModel() {
        repository = Repository.getInstance();
    }

    public LiveData<List<BreedResponse>> getBreedList() {
        return repository.getBreeds();
    }
    public void newImageForItem(){
        repository.requestDetailImage();
    }
}
