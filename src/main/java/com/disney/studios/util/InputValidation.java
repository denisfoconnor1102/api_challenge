package com.disney.studios.util;

import com.disney.studios.model.Dog;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class InputValidation {

    public static final List<String> VALID_BREEDS = Arrays.asList(
            "labrador",
            "pug",
            "retriever",
            "yorkie");
    
    public boolean isValid(Dog dog) {
        if(dog.getId() == null || dog.getId() <= 0) {
            return false;
        } else if (dog.getBreed() == null || !VALID_BREEDS.contains(dog.getBreed())) {
            return false;
        } else if(dog.getPicUrl() == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidBreed(String breed) {
        if(VALID_BREEDS.contains(breed)){
            return true;
        }else {
            return false;
        }
    }
}
