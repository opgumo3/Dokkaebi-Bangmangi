package com.example.mongodbdemo.controller.file;

import com.mongodb.client.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class FileDownloadController {

    private final MongoClient mongoClient;
    private final String databaseName;

    @PostMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestBody List<String> downloadList) throws IOException {
        GridFSBucket bucket = GridFSBuckets.create(mongoClient.getDatabase(databaseName), "test1");
        GridFSDownloadStream downloadStream = bucket.openDownloadStream(new ObjectId(downloadList.get(0)));
        byte[] bytes = downloadStream.readAllBytes();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(new ByteArrayResource(bytes));

    }
}
