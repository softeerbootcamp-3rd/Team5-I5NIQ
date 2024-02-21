package com.softeer.BE.service;

import com.softeer.BE.domain.dto.KeyAndList;
import com.softeer.BE.domain.dto.KeyAndValue;
import com.softeer.BE.domain.dto.ProgramResponse;
import com.softeer.BE.domain.entity.*;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.domain.dto.CarResponse.CarStatus;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramReservationInfo;
import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;
import com.softeer.BE.global.exception.GeneralHandler;
import com.softeer.BE.repository.DrivingClassRepository;
import com.softeer.BE.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DateService {

    private final DrivingClassRepository drivingClassRepository;
    private final ProgramRepository programRepository;

    public List<KeyAndValue<LocalDate, ReservationStatus>> getDateAndStatusList() {
        List<DrivingClass> drivingClassList = drivingClassRepository.findAvailableClass();
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
            if(dateStatusList.isEmpty()) dateStatusList.add(new KeyAndValue<>(localDate, status));
            else if(!dateStatusList.get(dateStatusList.size()-1).getKey().isEqual(localDate))
                dateStatusList.add(new KeyAndValue<>(localDate, status));
            else if(status == ReservationStatus.IMPOSSIBLE_YET) continue;
            else if(status == ReservationStatus.FULL) {
                if(dateStatusList.get(dateStatusList.size()-1).getValue() == ReservationStatus.IMPOSSIBLE_YET)
                    dateStatusList.get(dateStatusList.size()-1).setValue(ReservationStatus.FULL);
            }
            else dateStatusList.get(dateStatusList.size()-1).setValue(ReservationStatus.POSSIBLE);
        }
        return dateStatusList;
    }

    public List<KeyAndList<ProgramName, KeyAndList<ProgramCategory, ProgramReservationInfo>>>
    getProgramAndStatusList(LocalDate date) {
        List<Program> programList = programRepository.findAll();
        programList.sort(((o1, o2) -> {
            if(o1.getName() == o2.getName()) {
                if(o1.getCategory() == o2.getCategory()) return o1.getLevel().compareTo(o2.getLevel());
                else return o1.getCategory().compareTo(o2.getCategory());
            }
            else return o1.getName().compareTo(o2.getName());
        }));
        List<KeyAndList<ProgramName, KeyAndList<ProgramCategory, ProgramResponse.ProgramReservationInfo>>>
                programStatusList = new ArrayList<>();

        int i = 0;
        for(ProgramName name : ProgramName.values()) {
            KeyAndList<ProgramName, KeyAndList<ProgramCategory, ProgramReservationInfo>> tmp1 = new KeyAndList<>(name, new ArrayList<>());
            for(ProgramCategory category : ProgramCategory.values()) {
                KeyAndList<ProgramCategory, ProgramReservationInfo> tmp2 = new KeyAndList<>(category, new ArrayList<>());
                while(i < programList.size() && programList.get(i).getName() == name && programList.get(i).getCategory() == category) {
                    Program program = programList.get(i);
                    List<DrivingClass> classList = drivingClassRepository.findByProgramAndStartDateTime(program, date);
                    boolean possible = false;
                    for(DrivingClass drivingClass : classList) {
                        Long currentNumber = 0L;
                        for(ClassCar car : drivingClass.getCarList())
                            for(Participation participation : car.getParticipationList())
                                currentNumber += participation.getParticipants();
                        if(currentNumber < program.getMaximumOccupancy()) {
                            possible = true;
                            break;
                        }
                    }
                    tmp2.getList().add(ProgramReservationInfo.of(programList.get(i), possible ? ReservationStatus.POSSIBLE : ReservationStatus.IMPOSSIBLE));
                    i++;
                 }
                if(!tmp2.getList().isEmpty()) tmp1.getList().add(tmp2);
            }
            if(!tmp1.getList().isEmpty()) programStatusList.add(tmp1);
        }
        return programStatusList;
    }

    public ProgramResponse.ProgramCarStatusList getCarAndStatusList(LocalDate date, Long programId) {
        Program program = this.programRepository.findById(programId)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.PROGRAM_NOT_FOUND));
        List<DrivingClass> classList = drivingClassRepository.findByProgramAndStartDateTime(program, date);
        if(classList.isEmpty())
            throw new GeneralHandler(ErrorStatus._BAD_REQUEST);

        List<CarStatus> carAndStatus = new ArrayList<>();
        Map<Car, Long> availableCars = new HashMap<>();

        for(DrivingClass drivingClass : classList) {
            Long numberOfClass = 0L;
            Long maxOccupancyOfProgram = drivingClass.getProgram().getMaximumOccupancy();

            Map<Car, Long> tmp = new HashMap<>();
            for(ClassCar car : drivingClass.getCarList()) {
                Long currentNumber = 0L;
                for(Participation participation : car.getParticipationList()) {
                    currentNumber += participation.getParticipants();
                    numberOfClass += participation.getParticipants();
                }
                if(currentNumber >= car.getMaximumOccupancy()) tmp.put(car.getCar(), 0L);
                else tmp.put(car.getCar(), car.getMaximumOccupancy() - currentNumber);
            }
            if(maxOccupancyOfProgram > numberOfClass) {
                for(Car car : tmp.keySet())
                    availableCars.put(car, availableCars.getOrDefault(car, 0L) + tmp.get(car));
            }
            else {
                for (Car car : tmp.keySet())
                    availableCars.put(car, availableCars.getOrDefault(car, 0L));
            }
        }

        for(Car car : availableCars.keySet()) {
            if(availableCars.get(car) == 0L) carAndStatus.add(CarStatus.of(car, ReservationStatus.IMPOSSIBLE));
            else carAndStatus.add(CarStatus.of(car, ReservationStatus.POSSIBLE));
        }
        return ProgramResponse.ProgramCarStatusList.of(program, date, carAndStatus);
    }
}
