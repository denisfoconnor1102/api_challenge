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

    public static final List<String> VALID_BREEDS = Arrays.asList("labrador", "pug", "retriever", "yorkie");


//    public Dogs getAllDogs() {
//        Dogs dogList = new Dogs();
//        System.out.println("1");
//        System.out.println("dl");
//        System.out.println("DL2 len: ");
////        dogList.setDogList(dogRepository.findAll());
//        dogList.setDogList(dogRepository.findByOrderByBreed());
//        return dogList;
//    }

    public Dogs getAllDogs() {
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

        Dog dbDog = dogRepository.findById(dog.getId());
        dbDog.setFavCount(dbDog.getFavCount()+1);
        saveOrUpdateDogs(dbDog);
        return getAllDogs();

    }

    public Dogs unfavUpdate(Dog dog) {

        Dog dbDog = dogRepository.findById(dog.getId());
        int favCount = dbDog.getFavCount();
//        Todo Can a dog have negative favorites ???
        if(favCount>=0){
            dbDog.setFavCount(favCount-1);
        } else {
            return getAllDogs();
        }

        saveOrUpdateDogs(dbDog);
        return getAllDogs();

    }

    public Dogs findByBreed(String breed) {
        Dogs dogs = new Dogs();
        if(!VALID_BREEDS.contains(breed.toLowerCase())) {
            log.error("No breeds of type: {} available in database!", breed);
            return dogs;
        } else {
//            List<Dog> dogByBreed = new ArrayList<>(dogRepository.findByBreed(breed));
            List<Dog> dogByBreed = new ArrayList<>(dogRepository.findByBreedLikeIgnoreCaseOrderByFavCountDesc(breed));
//            dogs.setDogList(dogRepository.findByBreed(breed));
            log.info("dogsByBreed len: "+dogByBreed.size());
            dogs.setDogList(dogByBreed);

        }

        return dogs;
    }

    public void saveOrUpdateDogs(Dog dog) {
        dogRepository.save(dog);
    }



}
