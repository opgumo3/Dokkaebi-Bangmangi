package com.example.mongodbdemo.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Animal class를 animal 이라는 이름의 컬렉션으로 생성하고 데이터를 저장 할 거.
// 이름 바꾸고 싶다면 @Document >> value, collection, language, collation
@Getter
public class Animal {
    @Id
    private String id;
    // id값으로 String, ObjectId, BigInteger 지원.
    // 필드명이 id라면, 굳이 어노테이션을 적지 않아도 됨.
    private String name;

    private int age;
    private String location;

    @Builder
    public Animal(String name, int age, String location) {
        this.name = name;
        this.age = age;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", location='" + location + '\'' +
                '}';
    }
}
