package com.softeer.BE.service;

import com.softeer.BE.domain.dto.DrivingClassDto;
import com.softeer.BE.domain.dto.DrivingClassResponse;
import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.repository.DrivingClassRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrivingClassService {

    private final DrivingClassRepository drivingClassRepository;

    public List<DrivingClassDto> getScheduleList() {
        List<DrivingClass> drivingClassList = this.drivingClassRepository.findAllOrderByIdDesc();
        return drivingClassList.stream()
                .map(DrivingClassDto::toDto)
                .toList();
    }

    public List<LocalDate> getScheduleDateList(ProgramName programName, LocalDate lastLocalDate, Integer pageSize) {
        List<Date> dateList = this.drivingClassRepository.findAll(programName, lastLocalDate, PageRequest.of(0, pageSize));
        List<LocalDate> localDateList = new ArrayList<>();
        for(Date date : dateList) {
            LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
            localDateList.add(localDate);
        }
        return localDateList;
    }

    public List<DrivingClassResponse> getSchedulesAtLocalDate(ProgramName programName, LocalDate localDate) {
        List<DrivingClass> drivingClassList = this.drivingClassRepository.findAll(programName, localDate);
        List<DrivingClassResponse> drivingClassResponseList = new ArrayList<>();
        for(ProgramLevel programLevel : ProgramLevel.values()) {
            List<ProgramCategory> categoryList = new ArrayList<>();
            for(DrivingClass drivingClass : drivingClassList) {
                if(drivingClass.getProgram().getLevel() == programLevel)
                    categoryList.add(drivingClass.getProgram().getCategory());
            }
            if(categoryList.isEmpty()) continue;
            List<String> stringList = categoryList.stream()
                    .distinct()
                    .sorted()
                    .map(Enum::name)
                    .toList();
            drivingClassResponseList.add(new DrivingClassResponse(programLevel.name(), stringList));
        }
        return drivingClassResponseList;
    }

    @Transactional
    public void createSchedule(DrivingClassDto drivingClassDto) {
        this.drivingClassRepository.save(drivingClassDto.toEntity());
    }
}
