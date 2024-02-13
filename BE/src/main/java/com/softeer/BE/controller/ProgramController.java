package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.KeyAndList;
import com.softeer.BE.domain.dto.KeyAndValue;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
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

    @GetMapping("/name/category") // (날짜 먼저 선택하기)날짜를 선택했을 때
    public ApiResponse<List<KeyAndList<ProgramName, KeyAndList<ProgramCategory, KeyAndValue<ProgramLevel, ReservationStatus>>>>>
    getNameAndCategory(@RequestParam LocalDate localDate) {
        return ApiResponse.onSuccess(programService.getCategoryListAt(localDate));
    }

    @GetMapping("/level/category") // 일정 안내 화면에서 날짜를 클릭했을 때
    public ApiResponse<List<KeyAndList<ProgramLevel, ProgramCategory>>> getLevelAndCategory(@RequestParam ProgramName programName,
                                                                                            @RequestParam LocalDate localDate) {
        return ApiResponse.onSuccess(programService.getCategoryListAt(programName, localDate));
    }
}