package com.softeer.BE.service;

import com.softeer.BE.domain.dto.ReservationResponse;
import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.repository.ClassCarRepository;
import com.softeer.BE.repository.DrivingClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
    private final ClassCarRepository classCarRepository;
    private final DrivingClassRepository drivingClassRepository;

    public List<ReservationResponse.Step1CarStatus> getStep1CarStatusList(){
        // 현재 일시가 예약 시작 시간과 예약 마감 시간 사이인 ClassCar 목록을 조회한다.
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<ClassCar> availableClassCars = classCarRepository.findAvailableClassCars(currentDateTime);

        // 각 ClassCar에 대해 maximum_occupancy > participants 합 결과를 저장한다.
        return availableClassCars.stream().map(classCar -> {
            long totalParticipants = classCar.getParticipationList().stream()
                    .mapToLong(Participation::getParticipants)
                    .sum();
            boolean isAvailable = totalParticipants < classCar.getDrivingClass().getProgram().getMaximumOccupancy();
            return ReservationResponse.Step1CarStatus.of(classCar.getCar(), isAvailable);
        }).collect(Collectors.toList());
    }

    public List<ReservationResponse.Step2ProgramStatus> getStep2ProgramStatusList(Long carId) {
        // carId를 기준으로 reservation_deadline 전인 DrivingClass 목록을 조회한다.
        List<DrivingClass> drivingClassList = drivingClassRepository.findDrivingClassesByCarIdAndBeforeReservationDeadline(carId);
        // 조회된 DrivingClass 목록을 Program의 이름별로 그룹화한다.
        Map<String, List<DrivingClass>> nameToClassMap = drivingClassList.stream()
                .collect(Collectors.groupingBy(drivingClass -> drivingClass.getProgram().getName().name()));

        // 각 Program 이름별로 Step2ProgramStatus DTO를 생성한다.
        return nameToClassMap.entrySet().stream().map(entry -> {
            String programName = entry.getKey();
            // ProgramDetailList를 생성한다.
            List<ReservationResponse.ProgramDetail> programDetails = createProgramDetailList(entry.getValue());
            return ReservationResponse.Step2ProgramStatus.of(programName, programDetails);
        }).collect(Collectors.toList());
    }

    private List<ReservationResponse.ProgramDetail> createProgramDetailList(List<DrivingClass> drivingClassList) {
        // Category-Level 별로 DrivingClass를 그룹화하여 ProgramDetail 리스트를 생성한다.
        return drivingClassList.stream()
                // category와 level을 조합한 복합 키로 그룹화한다.
                .collect(Collectors.groupingBy(drivingClass -> new AbstractMap.SimpleEntry<>(drivingClass.getProgram().getCategory(), drivingClass.getProgram().getLevel())))
                .entrySet().stream().map(categoryLevelEntry -> {
                    // 각 category-level 조합에 해당하는 DrivingClass 목록에 대해 ClassDetailList를 생성한다.
                    List<ReservationResponse.ClassDetail> classDetailList = categoryLevelEntry.getValue().stream().map(this::createClassDetail).collect(Collectors.toList());
                    Program program = categoryLevelEntry.getValue().get(0).getProgram();
                    return ReservationResponse.ProgramDetail.of(program, classDetailList);
                }).collect(Collectors.toList());
    }

    private ReservationResponse.ClassDetail createClassDetail(DrivingClass drivingClass) {
        String status = determineClassStatus(drivingClass);
        String monthDate = formatMonthDate(drivingClass.getStartDateTime());
        return ReservationResponse.ClassDetail.of(monthDate, status);
    }

    private String determineClassStatus(DrivingClass drivingClass) {
        LocalDateTime now = LocalDateTime.now();

        // DrivingClass의 총 참여 인원을 계산한다.
        long totalParticipants = drivingClass.getCarList().stream()
                .flatMap(car -> car.getParticipationList().stream())
                .mapToLong(Participation::getParticipants)
                .sum();

        if (now.isBefore(drivingClass.getReservationStartTime())) {
            return "예정"; // 예약 시작 기간 전
        } else if (now.isAfter(drivingClass.getReservationStartTime()) && totalParticipants >= drivingClass.getProgram().getMaximumOccupancy()) {
            return "매진"; // 예약 시작 기간 후 + 참여 인원 합 >= 최대 인원
        } else {
            return "가능"; // 예약 시작 기간 후 + 참여 인원 합 < 최대 인원
        }
    }

    private String formatMonthDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd");
        return dateTime.format(formatter);
    }
}
