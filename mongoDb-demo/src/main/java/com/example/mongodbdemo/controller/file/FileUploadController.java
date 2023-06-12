package com.example.mongodbdemo.controller.file;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/upload")
@RestController
public class FileUploadController {
    private final GridFsTemplate gridFsTemplate;
    private final MongoClient mongoClient;
    private final String databaseName;
    private static int count=2;

    /**
     * GridFsTemplate 를 사용하여 파일을 저장합니다.
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveFile(@RequestPart MultipartFile file) throws IOException {
        InputStream fileStream = file.getInputStream();

        // metadata
        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", file.getSize());

        ObjectId fileId = gridFsTemplate.store(fileStream, file.getOriginalFilename(), file.getContentType(), metadata);
        log.info("file saved : {}", fileId);

        return fileId.toString();
    }

    /**
     * GridFsBucket 을 사용하여 파일을 저장합니다.
     */
    @PostMapping(value = "/bucket",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveFileUsingBucket(@RequestPart MultipartFile file) throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        GridFSBucket gridFSFilesBucket = GridFSBuckets.create(mongoClient.getDatabase(databaseName), "test1");

        try {
            InputStream streamToUploadFrom = file.getInputStream();

            GridFSUploadOptions options = new GridFSUploadOptions()
                    .chunkSizeBytes(255)
                    .metadata(new Document("TestDataSet", "1"));

            gridFSFilesBucket.uploadFromStream(file.getOriginalFilename(), streamToUploadFrom, options);

            stopWatch.stop();
            log.info("********** File Save End : {} s **********", stopWatch.getTotalTimeSeconds());
            return "success";

        } catch (FileNotFoundException e){

        }

        return "failed";
    }
}
