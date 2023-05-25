package com.example.mongodbdemo.repository;

import com.example.mongodbdemo.document.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimalRepository extends MongoRepository<Animal, String> {


}
