package com.softeer.BE.service;

import com.softeer.BE.domain.dto.ReservationStep3Response.ProgramSelectMenuStep3;
import com.softeer.BE.domain.entity.*;
import com.softeer.BE.domain.entity.enums.Status;
import com.softeer.BE.global.scheduler.ReservationPayCheckExecutorService;
import com.softeer.BE.repository.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
  private final ProgramReservationService programReservationService;
  private final ClassCarRepository classCarRepository;
  private final ProgramRepository programRepository;
  private final CarRepository carRepository;
  private final ParticipationRepository participationRepository;
  private final ReservationPayCheckExecutorService payCheckScheduler;
  private final DrivingClassRepository drivingClassRepository;
  private final RedisService redisService;
  public ProgramSelectMenuStep3 searchForStep3AvailableClassCar(LocalDate reservationDate, long programId, long carId){
    Program program = programRepository.findById(programId).orElseThrow(()->new RuntimeException("invalid program id"));
    Car car = carRepository.findById(carId).orElseThrow(()->new RuntimeException("invalid car id"));
    List<ClassCar> classes = classCarRepository.findAllByStep2(reservationDate,programId,carId,LocalDateTime.now());
    List<ClassCarValidation> validationClasses = classes.stream().map(ClassCarValidation::of).toList();
    return ProgramSelectMenuStep3.of(validationClasses,program,car,reservationDate);
  }
  @AllArgsConstructor
  @Getter
  public static class ClassCarValidation{
    private ClassCar classCar;
    private boolean reservationAvailable;
    private long participationCount;
    private long participationOccupancy;
    public static ClassCarValidation of(ClassCar classCar){
      long participationCount = classCar.getParticipationList().size();
      long occupancy = classCar.getMaximumOccupancy();
      boolean reservationAvailable = participationCount < occupancy;
      return new ClassCarValidation(classCar,reservationAvailable,participationCount,occupancy);
    }
    public LocalDateTime getClassStartDateTime(){
      return classCar.getDrivingClass().getStartDateTime();
    }
    public long getClassCarId(){
      return classCar.getId();
    }
  }
  private Logger logger = LoggerFactory.getLogger(ReservationService.class);
  @Transactional
  public boolean classCarReservation(long classCarId, long reservationSize, Users user, String uuid){
    ClassCar classCar = classCarRepository.findById(classCarId)
            .orElseThrow(()->new RuntimeException("invalid class car id"));
    DrivingClass drivingClass = drivingClassRepository.findByClassCarIdForUpdate(classCarId); // 락

    String value = redisService.getValues(uuid);      // redis에 존재하는지 확인
    if(redisService.checkExistsValue(value)) return false;     // redis에 이미 존재하면 생성 막기

    if(classCar.canReservation(reservationSize,programReservationService)) {
      long participationId = Participation.makeReservation(classCar, user, reservationSize, participationRepository);
      logger.info("insert into participation table");
      payCheckScheduler.executeTimer(participationId);
      redisService.setValues(uuid, String.valueOf(participationId));      // redis에 값 추가
      return true;
    }
    return false;
  }
}
