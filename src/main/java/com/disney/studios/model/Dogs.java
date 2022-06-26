package com.disney.studios.model;

import com.disney.studios.model.Dog;

import java.util.ArrayList;
import java.util.List;

public class Dogs {

    private List<Dog> dogList;

    public List<Dog> getDogList() {
        if(dogList == null) {
            dogList = new ArrayList<>();
        }
        return dogList;
    }

    public void setDogList(List<Dog> dogList) {
        this.dogList = dogList;
    }
}
