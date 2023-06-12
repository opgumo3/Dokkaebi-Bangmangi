package com.example.mongodbfront.controller.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FileDownloadController {

    private final RestTemplate restTemplate;
    private final String downloadUrl = "http://localhost:8080/download";

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam List<String> downloadList) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<String>> requestEntity = new HttpEntity<>(downloadList, headers);

        ResponseEntity<ByteArrayResource> images = restTemplate.exchange(downloadUrl, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<>() {
        });

        Resource resource = new InputStreamResource(new ByteArrayInputStream(images.getBody().getByteArray()));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(resource);
    }
}
