package com.dogypedia.model;

public class BreedResponse {
    private String bred_for;
    private String breed_group;
    Height height;
    private float id;
    private String life_span;
    private String name;
    private String temperament;
    Weight weight;

    // Getter Methods

    public String getBred_for() {
        return bred_for;
    }

    public String getBreed_group() {
        return breed_group;
    }

    public Height getHeight() {
        return height;
    }

    public float getId() {
        return id;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getName() {
        return name;
    }

    public String getTemperament() {
        return temperament;
    }

    public Weight getWeight() {
        return weight;
    }

    // Setter Methods

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }

    public void setBreed_group(String breed_group) {
        this.breed_group = breed_group;
    }

    public void setHeight(Height heightObject) {
        this.height = heightObject;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public void setWeight(Weight weightObject) {
        this.weight = weightObject;
    }
}