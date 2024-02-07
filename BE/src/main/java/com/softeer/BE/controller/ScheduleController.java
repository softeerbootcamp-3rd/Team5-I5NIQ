package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.ScheduleDto;
import com.softeer.BE.domain.dto.ScheduleResponse;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private static final Long DEFAULT_PAGE_SIZE = 10L;

    @GetMapping("/list")
    public ApiResponse<List<ScheduleDto>> getScheduleList() {
        List<ScheduleDto> scheduleDtoList = scheduleService.getScheduleList();
        return ApiResponse.onSuccess(scheduleDtoList);
    }

    @GetMapping("/date/list")
    public ApiResponse<List<LocalDate>> getScheduleDateList(@RequestParam String programName,
                                                            @RequestParam LocalDate lastLocalDate,
                                                            @RequestParam Integer pageSize) {
        List<LocalDate> localDateList = scheduleService.getScheduleDateList(programName, lastLocalDate, pageSize);
        return ApiResponse.onSuccess(localDateList);
    }

    @GetMapping("/list")
    public ApiResponse<List<ScheduleResponse>> getScheduleListAt(@RequestParam String programName,
                                                                 @RequestParam LocalDate localDate) {
        List<ScheduleResponse> scheduleResponseList = scheduleService.getSchedulesAtLocalDate(programName, localDate);
        return ApiResponse.onSuccess(scheduleResponseList);
    }
}
