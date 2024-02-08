package com.softeer.BE.service;

import com.softeer.BE.domain.dto.ScheduleDto;
import com.softeer.BE.domain.dto.ScheduleResponse;
import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleDto> getScheduleList() {
        List<DrivingClass> drivingClassList = this.scheduleRepository.findAllOrderByIdDesc();
        return drivingClassList.stream()
                .map(ScheduleDto::toDto)
                .toList();
    }

    public List<LocalDate> getScheduleDateList(String programName, LocalDate lastLocalDate, Integer pageSize) {
        return this.scheduleRepository.findAll(programName, lastLocalDate, PageRequest.of(0, pageSize));
    }

    public List<ScheduleResponse> getSchedulesAtLocalDate(String programName, LocalDate localDate) {
        List<DrivingClass> drivingClassList = this.scheduleRepository.findAll(programName, localDate);
        List<ScheduleResponse> scheduleResponseList = new ArrayList<>();
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
            scheduleResponseList.add(new ScheduleResponse(programLevel.name(), stringList));
        }
        return scheduleResponseList;
    }

    @Transactional
    public void createSchedule(ScheduleDto scheduleDto) {
        this.scheduleRepository.save(scheduleDto.toEntity());
    }
}
