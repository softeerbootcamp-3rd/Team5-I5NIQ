package com.softeer.BE.service;

import com.softeer.BE.domain.dto.DrivingClassDto;
import com.softeer.BE.domain.dto.DrivingClassResponse;
import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.repository.DrivingClassRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<LocalDate> getScheduleDateList(String programName, LocalDate lastLocalDate, Integer pageSize) {
        return this.drivingClassRepository.findAll(programName, lastLocalDate, PageRequest.of(0, pageSize));
    }

    public List<DrivingClassResponse> getSchedulesAtLocalDate(String programName, LocalDate localDate) {
        List<DrivingClass> drivingClassList = this.drivingClassRepository.findAll(programName, localDate);
        List<DrivingClassResponse> drivingClassResponseList = new ArrayList<>();
        for(ProgramLevel programLevel : ProgramLevel.values()) {
            List<ProgramLevel> categoryList = new ArrayList<>();
            for(DrivingClass drivingClass : drivingClassList) {
                if(drivingClass.getProgram().getLevel() == programLevel)
                    categoryList.add(drivingClass.getProgram().getLevel());
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
