package com.disney.studios.service;

import com.disney.studios.model.Dog;
import com.disney.studios.model.Dogs;
import com.disney.studios.repository.DogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class DogService {

    Logger log = LoggerFactory.getLogger(DogService.class);

    @Autowired
    private DogRepository dogRepository;



    public Dogs getAllDogs() {
        log.info("DogService.getAllDogs()");
        List<Order> orders = new ArrayList<Order>();
        Order orderBreed = new Order(Sort.Direction.ASC, "breed");
        orders.add(orderBreed);
        Order orderFav = new Order(Sort.Direction.DESC, "favCount");
        orders.add(orderFav);
        Dogs dogList = new Dogs();
        dogList.setDogList(dogRepository.findAll(new Sort(orders)));
        return dogList;
    }

    public Dogs favUpdate(Dog dog) {
        try {
            Dog dbDog = dogRepository.findById(dog.getId());
            dbDog.setFavCount(dbDog.getFavCount()+1);
            saveOrUpdateDogs(dbDog);
            return getAllDogs();
        } catch (Exception ex) {
            log.error("Exception in dogService.favUpdate() -- Ex:{}", ex.getLocalizedMessage());
            return getAllDogs();
        }
    }

    public Dogs unfavUpdate(Dog dog) {

        Dog dbDog = dogRepository.findById(dog.getId());
        int favCount = dbDog.getFavCount();
//        Todo Can a dog have negative favorites ???
        if(favCount>0){
            dbDog.setFavCount(favCount-1);
        } else {
            log.info("Dog picture has 0 favorites...");
            return getAllDogs();
        }
        saveOrUpdateDogs(dbDog);
        return getAllDogs();
    }

    public Dogs findByBreed(String breed) {
        Dogs dogs = new Dogs();
        List<Dog> dogByBreed = new ArrayList<>(dogRepository.findByBreedLikeIgnoreCaseOrderByFavCountDesc(breed));
        log.info("dogsByBreed len: "+dogByBreed.size());
        dogs.setDogList(dogByBreed);
        return dogs;
    }

    public void saveOrUpdateDogs(Dog dog) {
        dogRepository.save(dog);
    }



}
