package com.example.mongodbdemo.service.impl;

import com.example.mongodbdemo.service.FileStorageService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

@RequiredArgsConstructor
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final GridFsTemplate gridFsTemplate;

    @Override
    public ObjectId saveFile(InputStream content) {
        DBObject metadata = new BasicDBObject();
        metadata.put("anyTag", "tag");

        return gridFsTemplate.store(content, "paris.jpg", "image/jpg", metadata);
    }

    @Override
    public byte[] getFile() {
        GridFSFile file = gridFsTemplate.findOne(Query.query(whereFilename().is("paris.jpg")));

        try {

            byte[] byteArray = IOUtils.toByteArray(gridFsTemplate.getResource(file).getInputStream());
            return byteArray;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
