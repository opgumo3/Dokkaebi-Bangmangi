package com.example.mongodbdemo.controller.file;

import com.example.mongodbdemo.dto.file.FileListRetrieveResponse;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FileListController {

    private final MongoClient mongoClient;
    private final String databaseName;

    /**
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<FileListRetrieveResponse>> getFileList() {

        List<FileListRetrieveResponse> retrieveResponses = new ArrayList<>();
        MongoDatabase demo = mongoClient.getDatabase(databaseName);

        // 버킷의 종류를 가져올 수 있을까?
        MongoCollection<Document> collection = demo.getCollection("test1.files");
        FindIterable<Document> documents = collection.find();

        for (Document d : documents) {
            Date uploadDate = d.getDate("uploadDate");
            LocalDateTime localDateTime = uploadDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            Document metadata = (Document) d.get("metadata");
            retrieveResponses.add(
                    FileListRetrieveResponse.builder()
                            .id(String.valueOf(d.getObjectId("_id")))
                            .fileName((String) d.get("filename"))
                            .uploadDateTime(localDateTime)
                            .contentType((String) metadata.get("_contentType")).build());
        }


        return ResponseEntity.status(HttpStatus.OK)
                .body(retrieveResponses);
    }
}
