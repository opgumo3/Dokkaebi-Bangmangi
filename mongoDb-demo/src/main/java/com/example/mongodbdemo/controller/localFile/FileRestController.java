package com.example.mongodbdemo.controller.localFile;

import com.example.mongodbdemo.service.FileStorageService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
public class FileRestController {

    private final FileStorageService fileStorageService;

    // TODO 1. 로컬에 있는 파일을 InputStream 으로 저장.
    @PostMapping("/file")
    public String restSaveFile() throws FileNotFoundException {
        InputStream inputStreamContent = new FileInputStream("C:\\Users\\User\\Pictures\\paris.jpg");

        ObjectId storedObjectId = fileStorageService.saveFile(inputStreamContent);

        return storedObjectId.toString();
    }

    @GetMapping("/file")
    public ResponseEntity<ByteArrayResource> restGetFile() {
        byte[] file = fileStorageService.getFile();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(new ByteArrayResource(file));
    }
}
