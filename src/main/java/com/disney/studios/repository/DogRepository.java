package com.disney.studios.repository;

import com.disney.studios.model.Dog;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findByAllIgnoreCaseOrderByBreedAsc();

    List<Dog> findByOrderByBreed();

    List<Dog> findAll();

    Dog findById(Long id);

    List<Dog> findByBreed(String breed);

    @Query("select d from Dog d where upper(d.breed) like upper(?1) order by d.favCount DESC")
    List<Dog> findByBreedLikeIgnoreCaseOrderByFavCountDesc(String breed);
}