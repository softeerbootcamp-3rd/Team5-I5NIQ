package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.*;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
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
      List<ProgramCourse> programCourseList = p.getProgramCourseList();
      List<Course> courses = programCourseList.stream().map(ProgramCourse::getCourse).toList();
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
  @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ProgramCarStatusList{
        private Long programId;
        private LocalDate startDate;
        private List<CarResponse.CarStatus> carStatusList;
        public static ProgramCarStatusList of(Program p,
                                              LocalDate startDate,
                                              List<CarResponse.CarStatus> carStatusList){
            return new ProgramCarStatusList(p.getId(), startDate, carStatusList);
        }
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ProgramReservationInfo {
        private Long programId;
        private ProgramLevel programLevel;
        private ReservationStatus reservationStatus;
        public static ProgramReservationInfo of(Program p, ReservationStatus status) {
            return new ProgramReservationInfo(p.getId(), p.getLevel(), status);
        }
    }
}
