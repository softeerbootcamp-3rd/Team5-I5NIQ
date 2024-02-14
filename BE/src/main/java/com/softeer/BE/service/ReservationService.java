package com.softeer.BE.service;

import com.softeer.BE.domain.dto.ReservationStep3Response.ProgramSelectMenuStep3;
import com.softeer.BE.domain.entity.Car;
import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.repository.CarRepository;
import com.softeer.BE.repository.ClassCarRepository;
import com.softeer.BE.repository.ProgramRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
  private final ClassCarRepository classCarRepository;
  private final ProgramRepository programRepository;
  private final CarRepository carRepository;
  public ProgramSelectMenuStep3 searchForStep3AvailableClassCar(LocalDate reservationDate, long programId, long carId){
    Program program = programRepository.findById(programId).orElseThrow(()->new RuntimeException("invalid program id"));
    Car car = carRepository.findById(carId).orElseThrow(()->new RuntimeException("invalid car id"));
    List<ClassCar> classes = classCarRepository.findAllByStep2(reservationDate,programId,carId);
    List<ClassCarValidation> validationClasses = classes.stream().map(ClassCarValidation::of).toList();
    return ProgramSelectMenuStep3.of(validationClasses,program,car,reservationDate);
  }
  @AllArgsConstructor
  @Getter
  public static class ClassCarValidation{
    private ClassCar classCar;
    private boolean reservationAvailable;
    public static ClassCarValidation of(ClassCar classCar){
      long participationCount = classCar.getParticipationList().size();
      long occupancy = classCar.getMaximumOccupancy();
      boolean reservationAvailable = participationCount < occupancy;
      return new ClassCarValidation(classCar,reservationAvailable);
    }
    public LocalDateTime getClassStartDateTime(){
      return classCar.getDrivingClass().getStartDateTime();
    }
    public long getClassCarId(){
      return classCar.getId();
    }
  }
}
