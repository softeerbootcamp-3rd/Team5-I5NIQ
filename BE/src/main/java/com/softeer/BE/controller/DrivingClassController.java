package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.DrivingClassDto;
import com.softeer.BE.domain.dto.DrivingClassResponse;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.DrivingClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/drivingClass")
@RequiredArgsConstructor
public class DrivingClassController {

    private final DrivingClassService drivingClassRepository;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    @GetMapping("/list/all")
    public ApiResponse<List<DrivingClassDto>> getScheduleList() {
        List<DrivingClassDto> drivingClassDtoList = drivingClassRepository.getScheduleList();
        return ApiResponse.onSuccess(drivingClassDtoList);
    }

    @GetMapping("/date/list")
    public ApiResponse<List<LocalDate>> getScheduleDateList(@RequestParam String programName,
                                                            @RequestParam LocalDate lastLocalDate,
                                                            @RequestParam Integer pageSize) {
        if(pageSize == null || pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        List<LocalDate> localDateList = drivingClassRepository.getScheduleDateList(programName, lastLocalDate, pageSize);
        return ApiResponse.onSuccess(localDateList);
    }

    @GetMapping("/list")
    public ApiResponse<List<DrivingClassResponse>> getScheduleListAt(@RequestParam String programName,
                                                                     @RequestParam LocalDate localDate) {
        List<DrivingClassResponse> drivingClassResponseList = drivingClassRepository.getSchedulesAtLocalDate(programName, localDate);
        return ApiResponse.onSuccess(drivingClassResponseList);
    }

    @PostMapping("/create")
    public ApiResponse<DrivingClassResponse> createSchedule(@RequestBody DrivingClassDto drivingClassDto) {
        this.drivingClassRepository.createSchedule(drivingClassDto);
        return ApiResponse.onSuccess(null);
    }
}
