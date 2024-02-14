package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Car;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.service.ReservationService.ClassCarValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationStep3Response {
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ProgramSelectMenuStep3{
    private String carName;
    private LocalDate reservationDate;
    private String programName;
    private String companyName;
    private List<CarDrivingClass> classes;
    public static ProgramSelectMenuStep3 of(List<ClassCarValidation> classCarValidation,
                                            Program selectedProgram,
                                            Car selectedCar,
                                            LocalDate reservationDate){
      String programName = selectedProgram.getName().name();
      String companyName = selectedProgram.getCategory().name();
      String carName = selectedCar.getName();
      List<CarDrivingClass> classes = classCarValidation.stream().map(CarDrivingClass::of).toList();
      return new ProgramSelectMenuStep3(carName,reservationDate,programName,companyName,classes);
    }
  }
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  private static class CarDrivingClass{
    private LocalDateTime reservationDateTime;
    private boolean canReservation;
    private long classId;
    public static CarDrivingClass of(ClassCarValidation classCar){
      return new CarDrivingClass(classCar.getClassStartDateTime(), classCar.isReservationAvailable(),
              classCar.getClassCarId());
    }
  }
}
