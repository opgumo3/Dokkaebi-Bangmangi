package com.example.mongodbdemo.service;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.io.InputStream;

public interface FileStorageService {
    ObjectId saveFile(InputStream content);

    byte[] getFile();
}
