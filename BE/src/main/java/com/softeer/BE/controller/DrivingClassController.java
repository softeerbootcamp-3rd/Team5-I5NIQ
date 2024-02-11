package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.KeyAndValue;
import com.softeer.BE.domain.dto.DrivingClassDto;
import com.softeer.BE.domain.dto.KeyAndList;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
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

    private final DrivingClassService drivingClassService;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    @GetMapping("/list/all")
    public ApiResponse<List<DrivingClassDto>> getScheduleList() {
        List<DrivingClassDto> drivingClassDtoList = drivingClassService.getScheduleList();
        return ApiResponse.onSuccess(drivingClassDtoList);
    }

    @GetMapping("/date/list")
    public ApiResponse<List<LocalDate>> getScheduleDateList(@RequestParam ProgramName programName,
                                                            @RequestParam(required = false) LocalDate lastLocalDate,
                                                            @RequestParam(required = false) Integer pageSize) {
        if(pageSize == null || pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        if(lastLocalDate == null) lastLocalDate = LocalDate.of(3333, 12, 31);
        List<LocalDate> localDateList = drivingClassService.getScheduleDateList(programName, lastLocalDate, pageSize);
        return ApiResponse.onSuccess(localDateList);
    }

    @GetMapping("/date/list/all")
    public ApiResponse<List<KeyAndValue<LocalDate, ReservationStatus>>> getScheduleStatusList() {
        return ApiResponse.onSuccess(drivingClassService.getScheduleStatusList());
    }

    @GetMapping("/list")
    public ApiResponse<List<KeyAndList<ProgramLevel, ProgramCategory>>> getScheduleListAt(@RequestParam ProgramName programName,
                                                                                          @RequestParam LocalDate localDate) {
        List<KeyAndList<ProgramLevel, ProgramCategory>> nameAndCategoryList = drivingClassService.getSchedulesAtLocalDate(programName, localDate);
        return ApiResponse.onSuccess(nameAndCategoryList);
    }


    @PostMapping("/create")
    public ApiResponse<KeyAndValue> createSchedule(@RequestBody DrivingClassDto drivingClassDto) {
        this.drivingClassService.createSchedule(drivingClassDto);
        return ApiResponse.onSuccess(null);
    }
}
