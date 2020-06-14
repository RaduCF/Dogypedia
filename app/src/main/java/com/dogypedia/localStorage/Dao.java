package com.dogypedia.localStorage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Dao {
    private static Dao instance;
    private MutableLiveData<String> imageURL;
    private MutableLiveData<String> color;

    private Dao() {
        imageURL = new MutableLiveData<>();
        color = new MutableLiveData<>();
        color.setValue("grey");
    }

    public static Dao getInstance() {
        if (instance == null)
            instance = new Dao();
        return instance;
    }

    public LiveData<String> getImageURL() {
        return imageURL;
    }

    public void changeImageURL(String url) {
        imageURL.setValue(url);
    }

    public LiveData<String> getColor() {
        return color;
    }

    public void setColor(String newColor) {
        color.setValue(newColor);
    }
}
