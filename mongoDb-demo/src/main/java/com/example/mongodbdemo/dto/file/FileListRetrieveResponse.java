package com.example.mongodbdemo.dto.file;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class FileListRetrieveResponse {
    private String id;
    private String fileName;
    private LocalDateTime uploadDateTime;
    private String contentType;

    @Builder
    public FileListRetrieveResponse(String id, String fileName, LocalDateTime uploadDateTime, String contentType) {
        this.id = id;
        this.fileName = fileName;
        this.uploadDateTime = uploadDateTime;
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "FileListRetrieveResponse{" +
                "fileName='" + fileName + '\'' +
                ", uploadDateTime=" + uploadDateTime +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
