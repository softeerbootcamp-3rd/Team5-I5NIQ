package com.softeer.BE.service;

import com.softeer.BE.domain.dto.CursorResult;
import com.softeer.BE.domain.dto.KeyAndValue;
import com.softeer.BE.domain.dto.DrivingClassDto;
import com.softeer.BE.domain.dto.KeyAndList;
import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
import com.softeer.BE.repository.DrivingClassRepository;
import com.softeer.BE.repository.ProgramRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DrivingClassService {

    private final DrivingClassRepository drivingClassRepository;
    private final ProgramRepository programRepository;

    public List<DrivingClassDto> getScheduleList() {
        List<DrivingClass> drivingClassList = this.drivingClassRepository.findAllOrderByIdDesc();
        return drivingClassList.stream()
                .map(DrivingClassDto::toDto)
                .toList();
    }

    public CursorResult<LocalDate> getScheduleDateList(ProgramName programName, LocalDate lastLocalDate, Integer pageSize) {
        List<Date> dateList = this.drivingClassRepository.findAll(programName, lastLocalDate, PageRequest.of(0, pageSize)).getContent();
        List<LocalDate> localDateList = new ArrayList<>();
        for(Date date : dateList) {
            LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
            localDateList.add(localDate);
        }
        boolean hasNext = false;
        if(!localDateList.isEmpty()) {
            LocalDate lastDate = localDateList.get(localDateList.size()-1);
            hasNext = this.drivingClassRepository.existsByDateLessThan(lastDate);
        }
        return new CursorResult<>(localDateList, hasNext);
    }

    public List<KeyAndList<ProgramLevel, ProgramCategory>> getSchedulesAtLocalDate(ProgramName programName, LocalDate localDate) {
        List<Program> programList = this.programRepository.findAllByDateAndName(localDate, programName);
        programList.sort(((o1, o2) -> {
            if(o1.getLevel() == o2.getLevel()) return o1.getCategory().compareTo(o2.getCategory());
            else return o1.getLevel().compareTo(o2.getLevel());
        }));
        List<KeyAndList<ProgramLevel, ProgramCategory>> nameAndCategoryList = new ArrayList<>();
        for(Program program : programList) {
            if(nameAndCategoryList.isEmpty() ||
            !nameAndCategoryList.get(nameAndCategoryList.size()-1).getKey().equals(program.getLevel()))
                nameAndCategoryList.add(new KeyAndList<>(program.getLevel(), new ArrayList<>(List.of(program.getCategory()))));
            else nameAndCategoryList.get(nameAndCategoryList.size()-1).getList().add(program.getCategory());
        }
        return nameAndCategoryList;
    }

    @Transactional
    public void createSchedule(DrivingClassDto drivingClassDto) {
        this.drivingClassRepository.save(drivingClassDto.toEntity());
    }

    public List<KeyAndValue<LocalDate, ReservationStatus>> getScheduleStatusList() {
        List<DrivingClass> drivingClassList = drivingClassRepository.findValidClass();
        List<KeyAndValue<LocalDate, ReservationStatus>> dateStatusList = new ArrayList<>();
        for(DrivingClass drivingClass : drivingClassList) {
            LocalDate localDate = drivingClass.getStartDateTime().toLocalDate();
            ReservationStatus status;
            Long maxOccupancy = drivingClass.getProgram().getMaximumOccupancy();
            Long nowCount = 0L;
            for(ClassCar classCar : drivingClass.getCarList()) {
                for(Participation participation : classCar.getParticipationList()) {
                    nowCount += participation.getParticipants();
                }
            }
            if(drivingClass.getReservationStartTime().isAfter(LocalDateTime.now()))
                status = ReservationStatus.IMPOSSIBLE_YET;
            else if(maxOccupancy > nowCount)
                status = ReservationStatus.POSSIBLE;
            else
                status = ReservationStatus.FULL;
            if(dateStatusList.isEmpty()) dateStatusList.add(new KeyAndValue<LocalDate, ReservationStatus>(localDate, status));
            else if(!dateStatusList.get(dateStatusList.size()-1).getKey().isEqual(localDate))
                dateStatusList.add(new KeyAndValue<LocalDate, ReservationStatus>(localDate, status));
            else if(status == ReservationStatus.IMPOSSIBLE_YET) continue;
            else if(status == ReservationStatus.FULL) {
                if(dateStatusList.get(dateStatusList.size()-1).getValue() == ReservationStatus.IMPOSSIBLE_YET)
                    dateStatusList.get(dateStatusList.size()-1).setValue(ReservationStatus.FULL);
            }
            else dateStatusList.get(dateStatusList.size()-1).setValue(ReservationStatus.POSSIBLE);
        }
        return dateStatusList;
    }

    public List<KeyAndValue<String, ReservationStatus>> getPossibleCars(LocalDate date, ProgramName name, ProgramCategory category, ProgramLevel level) {
        List<DrivingClass> classList = drivingClassRepository.findByProgramAndStartDateTime(name, category, level, date);
        Map<String, Long> carAndRest = new HashMap<>();
        List<KeyAndValue<String, ReservationStatus>> carAndStatusList = new ArrayList<>();

        for(DrivingClass drivingClass : classList) {
            long currentCount = 0L;
            Map<String, Long> hm = new HashMap<>();
            for(ClassCar car : drivingClass.getCarList()) {
                long participants = 0L;
                for(Participation participation : car.getParticipationList()) {
                    participants += participation.getParticipants();
                }
                hm.put(car.getCar().getName(), car.getMaximumOccupancy()-participants);
                currentCount += participants;
            }
            if(currentCount >= drivingClass.getProgram().getMaximumOccupancy()) continue;
            for(String car : hm.keySet()) {
                carAndRest.put(car, carAndRest.getOrDefault(car, 0L) + hm.get(car));
            }
        }
        for(String car : carAndRest.keySet()) {
            if(carAndRest.get(car) == 0L) carAndStatusList.add(new KeyAndValue<>(car, ReservationStatus.FULL));
            else carAndStatusList.add(new KeyAndValue<>(car, ReservationStatus.POSSIBLE));
        }
        return carAndStatusList;
    }
}
