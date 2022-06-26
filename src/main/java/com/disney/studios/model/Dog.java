package com.disney.studios.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Breed may not be null")
    private String breed;

    @NotNull(message = "picUrl may not be null")
    private String picUrl;

    @NotNull(message = "favCount may not be null")
    private int favCount;

    public Dog() {
    }

    public Dog(Long id, String breed, String picUrl, int favCount) {
        this.id = id;
        this.breed = breed;
        this.picUrl = picUrl;
        this.favCount = favCount;
    }

    public Dog(String breed, String picUrl, int favCount) {
        this.breed = breed;
        this.picUrl = picUrl;
        this.favCount = favCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getFavCount() {
        return favCount;
    }

    public void setFavCount(int favCount) {
        this.favCount = favCount;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", breed='" + breed + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", favCount=" + favCount +
                '}';
    }
}
