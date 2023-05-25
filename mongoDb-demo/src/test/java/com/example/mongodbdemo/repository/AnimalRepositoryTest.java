package com.example.mongodbdemo.repository;

import com.example.mongodbdemo.document.Animal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
class AnimalRepositoryTest {

    private final AnimalRepository animalRepository;

    AnimalRepositoryTest(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Test
    void saveTest() {
        animalRepository.save(new Animal("IBao", 3, "판다월드"));

        for (Animal animal :
                animalRepository.findAll()) {
            System.out.println(animal);
        }
    }
}