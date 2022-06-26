package com.disney.studios.contoller;


import com.disney.studios.model.Dog;
import com.disney.studios.model.Dogs;
import com.disney.studios.service.DogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/dogs")
public class DogController {

    Logger logger = LoggerFactory.getLogger(DogController.class);

    @Autowired
    private DogService dogService;

    @RequestMapping(path="/all", method = RequestMethod.GET)
    public Dogs getAll(){
        System.out.println("GET ALL");
        return dogService.getAllDogs();
    }

    @RequestMapping(path="/fav", method=RequestMethod.POST)
    public Dogs dogFav(@RequestBody Dog dog){


        return dogService.favUpdate(dog);
    }


//    Unfavorite is tricky, directions mention and up/down vote
//    UI directions mention only being able to Unfavorite a Favorite which would require User tracking
    @RequestMapping(path="/unfav", method=RequestMethod.POST)
    public Dogs dogUnfav(@RequestBody Dog dog){


        return dogService.unfavUpdate(dog);
    }

    @RequestMapping(path="/breed", method = RequestMethod.GET)
    public Dogs getByBreed(@RequestParam(name="breed") String breed) {

        return dogService.findByBreed(breed);
    }
}


