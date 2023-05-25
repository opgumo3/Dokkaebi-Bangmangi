package com.example.mongodbdemo.controller.rest;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
public class FileRestController {

    private final GridFsTemplate gridFsTemplate;

    @GetMapping("/file")
    public String testFile() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("C:\\Users\\S20184477\\Pictures\\paris.jpg");

        ObjectId store = gridFsTemplate.store(inputStream, "paris.jpg", "image/jpg");
        return store.getDate().toString();
    }
}
