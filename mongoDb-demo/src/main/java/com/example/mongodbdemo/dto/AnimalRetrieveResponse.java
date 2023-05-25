package com.example.mongodbdemo.dto;

import com.example.mongodbdemo.document.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRetrieveResponse {
    String id;
    String name;
    int age;
    String location;
    public static AnimalRetrieveResponse toEntity(List<Animal> all) {
        Animal animal = all.get(0);
        return new AnimalRetrieveResponse(animal.getId(), animal.getName(), animal.getAge(), animal.getLocation());
    }
}
