package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.*;
import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.DrivingClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramCarStatusList;

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
    public ApiResponse<CursorResult<LocalDate>> getScheduleDateList(@RequestParam(required = false) ProgramName programName,
                                                                    @RequestParam(required = false) LocalDate lastLocalDate,
                                                                    @RequestParam(required = false) Integer pageSize) {
        if(programName == null) programName = ProgramName.DRIVING_EXPERIENCE;
        if(pageSize == null || pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        if(lastLocalDate == null) lastLocalDate = LocalDate.of(3333, 12, 31);
        CursorResult<LocalDate> localDateList = drivingClassService.getScheduleDateList(programName, lastLocalDate, pageSize);
        return ApiResponse.onSuccess(localDateList);
    }

    @GetMapping("/date/status/list")
    public ApiResponse<List<KeyAndValue<LocalDate, ReservationStatus>>> getScheduleStatusList() {
        return ApiResponse.onSuccess(drivingClassService.getScheduleStatusList());
    }

    @GetMapping("/cars")
    public ApiResponse<ProgramCarStatusList> getPossibleCars(@RequestParam LocalDate localDate,
                                                             @RequestParam ProgramName programName,
                                                             @RequestParam ProgramCategory programCategory,
                                                             @RequestParam ProgramLevel programLevel) {
        return ApiResponse.onSuccess(drivingClassService.getAvailableCarList(localDate, programName, programCategory, programLevel));
    }

    @GetMapping("/level/status/list")
    public ApiResponse<List<KeyAndValue<ProgramLevel, ReservationStatus>>> getLevelStatusList(@RequestParam LocalDate localDate,
                                                                                              @RequestParam ProgramName programName,
                                                                                              @RequestParam ProgramCategory programCategory) {
        return ApiResponse.onSuccess(drivingClassService.getLevelStatusList(localDate, programName, programCategory));
    }


    @PostMapping("/create")
    public ApiResponse<DrivingClass> createSchedule(@RequestBody DrivingClassDto drivingClassDto) {
        this.drivingClassService.createSchedule(drivingClassDto);
        return ApiResponse.onSuccess(null);
    }
}
