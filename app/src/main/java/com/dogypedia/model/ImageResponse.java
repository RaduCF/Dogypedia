package com.dogypedia.model;

import java.util.ArrayList;

public class ImageResponse {
    ArrayList< Object > breeds = new ArrayList < Object > ();
    private String id;
    private String url;
    private float width;
    private float height;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
