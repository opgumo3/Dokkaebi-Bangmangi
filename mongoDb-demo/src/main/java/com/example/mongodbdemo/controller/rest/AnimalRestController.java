package com.example.mongodbdemo.controller.rest;

import com.example.mongodbdemo.document.Animal;
import com.example.mongodbdemo.dto.AnimalRetrieveResponse;
import com.example.mongodbdemo.repository.AnimalRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoIterable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnimalRestController {

    private final MongoClient client;
    private final AnimalRepository animalRepository;

    @GetMapping("/testAnimal")
    public String test() {
        MongoIterable<String> strings = client.listDatabaseNames();

        for (String names : strings) {
            System.out.println(names);
        }

        Animal testAnimal = Animal.builder().name("IBao").age(3).location("에버랜드 판다월드").build();
        Animal saved = animalRepository.save(testAnimal);

        return saved.getId();
    }

    @GetMapping("/testSave")
    public AnimalRetrieveResponse getAnimals() {
        List<Animal> all = animalRepository.findAll();

        AnimalRetrieveResponse entity = AnimalRetrieveResponse.toEntity(all);

        return entity;
    }
}
