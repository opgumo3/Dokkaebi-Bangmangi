package com.example.mongodbfront.controller.file;

import com.example.mongodbfront.dto.file.FileListRetrieveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 파일 목록에 대한 요청을 받는 컨트롤러 입니다.
 */
@RequiredArgsConstructor
@Controller
public class FileListController {

    private final RestTemplate restTemplate;
    private final String listUrl = "http://localhost:8080/list";

    /**
     * 저장되어 있는 파일 목록 리스트를 보여줍니다.
     */
    @GetMapping("/list")
    public String getFileList(Model model) {
        ResponseEntity<List<FileListRetrieveResponse>> forEntity = restTemplate.exchange(listUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        List<FileListRetrieveResponse> body = forEntity.getBody();
        model.addAttribute("list", body);

        return "List";
    }
}
