package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.KeyAndList;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/program")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    @GetMapping("/name/category")
    public ApiResponse<List<KeyAndList<ProgramName, ProgramCategory>>> getCategories(@RequestParam LocalDate localDate) {
        return ApiResponse.onSuccess(programService.getCategoryListAt(localDate));
    }

    @GetMapping("/level/category")
    public ApiResponse<List<KeyAndList<ProgramLevel, ProgramCategory>>> getCategories(@RequestParam ProgramName programName,
                                                                                      @RequestParam LocalDate localDate) {
        return ApiResponse.onSuccess(programService.getCategoryListAt(programName, localDate));
    }
}