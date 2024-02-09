package com.softeer.BE.service;

import com.softeer.BE.domain.dto.DrivingClassDateResponse;
import com.softeer.BE.domain.dto.DrivingClassDto;
import com.softeer.BE.domain.dto.DrivingClassResponse;
import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.repository.DrivingClassRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public List<DrivingClassDateResponse> getScheduleStatusList() {
        List<DrivingClass> drivingClassList = drivingClassRepository.findValidClass();
        List<DrivingClassDateResponse> drivingClassDateResponseList = new ArrayList<>();
        for(DrivingClass drivingClass : drivingClassList) {
            LocalDate localDate = drivingClass.getStartDateTime().toLocalDate();
            String status = "";
            Long maxOccupancy = drivingClass.getProgram().getMaximumOccupancy();
            Long nowCount = 0L;
            for(ClassCar classCar : drivingClass.getCarList()) {
                for(Participation participation : classCar.getParticipationList()) {
                    nowCount += participation.getParticipants();
                }
            }
            if(drivingClass.getReservationStartTime().isAfter(LocalDateTime.now()))
                status = "NOT_POSSIBLE";
            else if(maxOccupancy > nowCount)
                status = "POSSIBLE";
            else
                status = "FULL";
            if(drivingClassDateResponseList.isEmpty()) drivingClassDateResponseList.add(new DrivingClassDateResponse(localDate, status));
            else if(!drivingClassDateResponseList.get(drivingClassDateResponseList.size()-1).getLocalDate().isEqual(localDate))
                drivingClassDateResponseList.add(new DrivingClassDateResponse(localDate, status));
            else if(status.equals("NOT_POSSIBLE")) continue;
            else if(status.equals("FULL")) {
                if(drivingClassDateResponseList.get(drivingClassDateResponseList.size()-1).getStatus().equals("NOT_POSSIBLE"))
                    drivingClassDateResponseList.get(drivingClassDateResponseList.size()-1).setStatus("FULL");
            }
            else drivingClassDateResponseList.get(drivingClassDateResponseList.size()-1).setStatus("POSSIBLE");
        }
        return drivingClassDateResponseList;
    }
}
