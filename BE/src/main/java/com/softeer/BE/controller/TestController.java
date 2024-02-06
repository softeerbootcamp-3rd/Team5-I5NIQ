package com.softeer.BE.controller;

import com.softeer.BE.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/")
    public ApiResponse<String> testAPI(){
        return ApiResponse.onSuccess("성공");
    }
}
