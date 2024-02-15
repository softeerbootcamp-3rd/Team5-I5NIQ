package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private String qualification;
    public static ProgramInformation of(Program p){
      List<SelectedCar> cars = p.getSelectedCarList();
      List<String> programCars = cars.stream().map((c)->c.getCar().getName()).toList();
      return new ProgramInformation(p.getId(),p.getCategory().name(),p.getCategory().getDetail(),
              p.getLevel().name(),p.getLevel().getDetail(),programCars,p.getEstimatedDuration(),
              p.getMaximumOccupancy(),p.getQualification());
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
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ProgramLocations{
    private Integer circuitSize;
    private List<ProgramCircuit> circuits;
    public static ProgramLocations of(Program p){
      List<Course> courses = p.getCourseList();
      return new ProgramLocations(courses.size(),courses.stream().map(ProgramCircuit::of).toList());
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  private static class ProgramCircuit{
    private String imageUrl;
    private String name;
    private String description;
    public static ProgramCircuit of(Course c){
      return new ProgramCircuit(c.getImageUrl(),c.getName(),c.getDetail());
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ProgramComments{
    private Integer commentSize;
    private List<ProgramComment> comments;
    public static ProgramComments of(List<Comment> cs){
      List<ProgramComment> comments = cs.stream().map(ProgramComment::of).toList();
      return new ProgramComments(comments.size(),comments);
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  private static class ProgramComment{
    private String userName;
    private LocalDateTime createdAt;
    private String content;
    public static ProgramComment of(Comment c){
      return new ProgramComment(c.getUser().getName(), c.getCreatedAt(), c.getContent());
    }
  }
}
