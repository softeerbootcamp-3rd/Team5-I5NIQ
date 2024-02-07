package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.SelectedCar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProgramResponse {
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ProgramInformation{
    private Long programId;
    private String programCategoryName;
    private String programCategoryDescription;
    private String levelName;
    private String programDescription;
    private List<String> programCars;
    private String estimatedDuration;
    private Long maxMemberNumber;
    private Long cost;
    private String qualification;
    public static ProgramInformation of(Program p){
      List<SelectedCar> cars = p.getSelectedCarList();
      List<String> programCars = cars.stream().map((c)->c.getCar().getName()).toList();
      return new ProgramInformation(p.getId(),p.getCategory().name(),p.getCategory().getDetail(),
              p.getLevel().name(),p.getLevel().getDetail(),programCars,p.getEstimatedDuration(),
              p.getMaximumOccupancy(),p.getCost(),p.getQualification());
    }
  }
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ProgramDetail{
    private Long programId;
    private List<String> programImages;
    private String detailDescription;
    private List<ProgramDetailCar> programCars;
  }
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  private static class ProgramDetailCar{
    private String image;
    private String name;
    private String description;
    private String maxPower;
    private String maxTorque;
  }
}
