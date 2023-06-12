package com.example.mongodbfront.controller.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

/**
 * 파일 상세에 대한 요청을 받는 컨트롤러 입니다.
 */
@RequiredArgsConstructor
@Controller
public class FileDetailController {

    private final RestTemplate restTemplate;
    private final String detailUrl = "http://localhost:8080/detail/";

    /**
     * ObjectId 필드에 대해 objectId 값을 가진 파일의 상세를 조회하고 보여줍니다.
     * @param objectId 상세를 조회하려는 파일의 objectId 입니다.
     */
    @GetMapping("/detail/{objectId}")
    public String getFileDetail(@PathVariable String objectId, Model model) {

        ResponseEntity<ByteArrayResource> forEntity = restTemplate.exchange(detailUrl + objectId, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        byte[] fileByteArray = forEntity.getBody().getByteArray();
        model.addAttribute("resource", Base64.getEncoder().encodeToString(fileByteArray));

        return "detail";
    }
}
