package com.softeer.BE.controller;

import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/")
    public ApiResponse<String> exceptionAPI(@RequestParam Integer flag){
        testService.CheckFlag(flag);
        return ApiResponse.onSuccess("성공");
    }
}
