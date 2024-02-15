package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.CursorResult;
import com.softeer.BE.domain.dto.KeyAndList;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    @GetMapping("/{programName}") // (일정 안내) 일정 리스트 페이지네이션
    public ApiResponse<CursorResult<LocalDate>> getScheduleDateList(@PathVariable ProgramName programName,
                                                                    @RequestParam(required = false) LocalDate lastLocalDate,
                                                                    @RequestParam(required = false) Integer pageSize) {
        if(pageSize == null || pageSize <= 0) pageSize = DEFAULT_PAGE_SIZE;
        if(lastLocalDate == null) lastLocalDate = LocalDate.of(3333, 12, 31);
        CursorResult<LocalDate> localDateList = scheduleService.getScheduleDateList(programName, lastLocalDate, pageSize);
        return ApiResponse.onSuccess(localDateList);
    }

    @GetMapping("/{programName}/{date}") // (일정 안내) 날짜를 클릭했을 때
    public ApiResponse<List<KeyAndList<ProgramLevel, ProgramCategory>>> getLevelAndCategory(@PathVariable ProgramName programName,
                                                                                            @PathVariable LocalDate date) {
        return ApiResponse.onSuccess(scheduleService.getCategoryListAt(programName, date));
    }
}
