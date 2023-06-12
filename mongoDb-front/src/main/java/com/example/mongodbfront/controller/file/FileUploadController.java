package com.example.mongodbfront.controller.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FileUploadController {

    private final RestTemplate restTemplate;
    private final String uploadUrl = "http://localhost:8080/upload/bucket";

    @GetMapping("/upload")
    public String saveFileForm() {
        return "UploadForm";
    }

    /**
     * 이미지 파일 업로드 요청을 보냅니다.
     *
     * @param file 업로드 할 이미지
     * @return 파일 목록 페이지
     */
    @PostMapping("/upload")
    public String saveFile(MultipartFile file) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        ByteArrayResource contentsAsResource = new ByteArrayResource(file.getBytes()){
            @Override
            public String getFilename(){
                return file.getOriginalFilename();
            }
        };
        body.add("file", contentsAsResource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        restTemplate.postForEntity(uploadUrl, requestEntity, String.class);


        return "redirect:/list";
    }
}
