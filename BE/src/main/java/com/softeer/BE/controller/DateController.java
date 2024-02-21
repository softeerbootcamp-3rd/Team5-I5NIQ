package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.KeyAndList;
import com.softeer.BE.domain.dto.KeyAndValue;
import com.softeer.BE.domain.dto.ProgramResponse;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramReservationInfo;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/date")
@RequiredArgsConstructor
public class DateController {

    private final DateService dateService;

    @GetMapping("/step1") // 첫번째 화면, 일정마다 상태 표시
    public ApiResponse<List<KeyAndValue<LocalDate, ReservationStatus>>> getDateAndStatusList() {
        return ApiResponse.onSuccess(dateService.getDateAndStatusList());
    }

    @GetMapping("/step1/{date}") // 두번째 화면, 프로그램마다 상태 표시
    public ApiResponse<List<KeyAndList<ProgramName, KeyAndList<ProgramCategory, ProgramReservationInfo>>>>
    getProgramAndStatusList(@PathVariable LocalDate date) {
        return ApiResponse.onSuccess(dateService.getProgramAndStatusList(date));
    }

    @GetMapping("/step2/{date}/{programId}") // 세번째 화면, 차량마다 상태 표시
    public ApiResponse<ProgramResponse.ProgramCarStatusList> getCarAndStatusList(@PathVariable LocalDate date,
                                                                                 @PathVariable Long programId) {
        return ApiResponse.onSuccess(dateService.getCarAndStatusList(date, programId));
    }
}
