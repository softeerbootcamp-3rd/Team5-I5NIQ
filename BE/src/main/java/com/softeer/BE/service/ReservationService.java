package com.softeer.BE.service;

import com.softeer.BE.domain.dto.ReservationResponse;
import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.repository.ClassCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
    private final ClassCarRepository classCarRepository;

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
}
