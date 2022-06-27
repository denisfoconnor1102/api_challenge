package com.disney.studios.contoller;


import com.disney.studios.model.Dog;
import com.disney.studios.model.Dogs;
import com.disney.studios.service.DogService;
import com.disney.studios.util.InputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/api")
public class DogController {

    Logger log = LoggerFactory.getLogger(DogController.class);

//    TODO ADD POJO Validation
    @Autowired
    InputValidation inputValidation;

    @Autowired
    private DogService dogService;

    @RequestMapping(path="/id", method = RequestMethod.GET)
    public ResponseEntity<Dog> getDogById(@RequestParam(value = "id") Long id){
        log.info("Get dog by id...");
        Dog dog = dogService.findDogById(id);
        if(dog != null){
            return ResponseEntity.ok().body(dog);
        } else {
//            Todo Better standardized error handling for Exception return Responses
            log.error("No dog info exists for id: {}", id);
            return new ResponseEntity<>(new Dog(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path="/all", method = RequestMethod.GET)
    public Dogs getAll(){
        log.info("Get All dogs...");
        return dogService.getAllDogs();
    }

//     TODO Add current index for UI to swipe back and forth???
//    TODO Returning Full list of the dogs after the update.  Perhaps just the updated???
    @RequestMapping(path="/fav", method=RequestMethod.POST)
    public Dogs dogFav(@RequestBody Dog dog){
        if(inputValidation.isValid(dog)){
            log.info("Upvote Dog...");
            return dogService.favUpdate(dog);
        } else {
            log.error("Invalid RequestBody!!!");
            return dogService.getAllDogs();
        }

    }


//    Unfavorite is tricky, directions mention and up/down vote
//    UI directions mention only being able to Unfavorite a Favorite which would require User tracking
//    Conflicting Info --- One to One Client vote for picture but can only unfavorite a favorite
//
    @RequestMapping(path="/unfav", method=RequestMethod.POST)
    public Dogs dogUnfav(@RequestBody Dog dog){
        if(inputValidation.isValid(dog)){
            log.info("Downvote Dog...");
            return dogService.unfavUpdate(dog);
        } else {
            log.error("Invalid RequestBody!!!");
            return dogService.getAllDogs();
        }
    }

    @RequestMapping(path="/breed", method = RequestMethod.GET)
    public Dogs getByBreed(@RequestParam(name="breed") String breed) {

        if(inputValidation.isValidBreed(breed)){
            log.info("Get dogs by breed...");
            return dogService.findByBreed(breed);
        }else {
            log.error("Invalid Dog Breed!!!");
            return dogService.getAllDogs();
        }
    }
}


