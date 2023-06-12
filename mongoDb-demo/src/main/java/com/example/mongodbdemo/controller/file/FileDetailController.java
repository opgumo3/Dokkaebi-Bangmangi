package com.example.mongodbdemo.controller.file;

import com.mongodb.client.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.AudioInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FileDetailController {

    private final MongoClient mongoClient;
    private final String databaseName;

    @GetMapping("/detail/{objectId}")
    public ResponseEntity<ByteArrayResource> getFileByteArray(@PathVariable String objectId) throws IOException {

        GridFSBucket bucket = GridFSBuckets.create(mongoClient.getDatabase(databaseName), "test1");
        GridFSDownloadStream downloadStream = bucket.openDownloadStream(new ObjectId(objectId));
        byte[] bytes = downloadStream.readAllBytes();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(new ByteArrayResource(bytes));
    }
}
