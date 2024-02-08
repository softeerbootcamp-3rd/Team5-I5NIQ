package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

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
    public static ProgramDetail of(Program p){
      List<SelectedCar> selectedCars = p.getSelectedCarList();
      List<ProgramDetailCar> cars = selectedCars.stream().map(ProgramDetailCar::of).toList();
      List<String> programImages = p.getImageList().stream().map(ProgramImage::getUrl).toList();
      return new ProgramDetail(p.getId(),programImages,p.getLevel().getDescription(),cars);
    }
  }
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  private static class ProgramDetailCar{
    private Long carId;
    private String image;
    private String name;
    private String description;
    private String maxPower;
    private String maxTorque;
    public static ProgramDetailCar of(SelectedCar selectedCar){
      Car c = selectedCar.getCar();
      Optional<CarImage> carImage = c.getCarImageList().stream().findFirst();
      String imageUrl=null;
      if(carImage.isPresent())
        imageUrl=carImage.get().getUrl();
      return new ProgramDetailCar(c.getId(),imageUrl,c.getName(),null,
              c.getMaximumPower(),c.getMaximumTorque());
    }
  }
}
