package com.example.mongodbfront.dto.file;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
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
}
