package com.softeer.BE.service;

import com.softeer.BE.domain.dto.ReservationResponse.DateCarSelectMenu;
import com.softeer.BE.domain.dto.ReservationResponse.ProgramCategorySelectMenu;
import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.repository.ClassCarRepository;
import com.softeer.BE.repository.DrivingClassRepository;
import com.softeer.BE.repository.ProgramRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramReservationService {
  private final ProgramRepository programRepository;
  private final DrivingClassRepository drivingClassRepository;
  private final ClassCarRepository classCarRepository;

  public ProgramCategorySelectMenu searchForAvailableProgram(){
    //모든 프로그램을 가져오기
    List<Program> allProgram = programRepository.findAll();
    HashMap<Long,ProgramValidation> programHashMap = new HashMap<>();
    //현재 예약할 수 있는 시간대의 DrivingClass 전부 가져오기
    List<DrivingClass> validReservationDateDrivingClasses =
            drivingClassRepository.findAllByReservationDate(LocalDateTime.now());
    //엔티티 그래프를 통해 예약 자리가 남아있는지 판별해서
    // DrivingClass를 예약 가능 정보까지 포함되어 있는 DrivingClassValidation으로 변환
    List<DrivingClassValidation> drivingClassValidations = validReservationDateDrivingClasses.stream()
            .map(DrivingClassValidation::of).toList();
    //모든 프로그램들을 programHashMap에 매핑 (예약 내용이 없는 Program이라도 응답값에 있어야 하므로)
    //ProgramValidation은 내부에 program정보와 함께 프로그램이 예약 가능한지 여부 정보를 포함하고 있음.
    for(Program p : allProgram){
      programHashMap.put(p.getId(),new ProgramValidation(false,p));
    }
    //DrivingClassValidation을 가지고 ProgramValidation이 예약 가능한지 판단
    for(DrivingClassValidation dc : drivingClassValidations){
      ProgramValidation pv = programHashMap.get(dc.getProgramId());
      pv.setCanReservation(dc);
    }
    //응답값에 맞는 DTO로 변환
    return ProgramCategorySelectMenu.of(programHashMap);
  }
  @AllArgsConstructor
  @Getter
  private static class DrivingClassValidation{
    private boolean canReservation;
    private DrivingClass drivingClass;
    public Long getProgramId(){
      return this.drivingClass.getProgram().getId();
    }
    //DrivingClass엔티티를 가지고 예약 가능한지 판단.
    //DrivingClass엔티티 -> ClassCar List -> Participation List까지 LazyLoading을 진행하기 때문에
    //BatchSize를 통해 쿼리가 줄어드는지 확인 필수
    public static DrivingClassValidation of(DrivingClass d){
      List<ClassCar> cars = d.getCarList();
      boolean canReservation=false;
      for(ClassCar c : cars){
        List<Participation> participationList = c.getParticipationList();
        long totalAmount = totalParticipationCount(participationList);
        if(totalAmount<c.getMaximumOccupancy()){
          canReservation=true;
          break;
        }
      }
      return new DrivingClassValidation(canReservation,d);
    }
  }
  @AllArgsConstructor
  @Getter
  public static class ProgramValidation{
    private boolean reservationAvailable;
    private Program program;
    public String getCompanyName(){
      return this.program.getCategory().name();
    }
    public String getLevelName(){
      return this.program.getLevel().name();
    }
    public void setCanReservation(DrivingClassValidation dc){
      reservationAvailable = reservationAvailable | dc.isCanReservation();
    }
  }

  public DateCarSelectMenu searchForAvailableDateCars(long programId){
    //현재 예약할 수 있는 시간대 및 특정 program id 의 ClassCar 전부 가져오기
    List<ClassCar> classCars = classCarRepository.findAllByReservationDate(LocalDateTime.now(),programId);
    //가져온 ClassCar들이 예약 가능한지 판단
    List<ClassCarValidation> validatedClassCar = classCars.stream().map(ClassCarValidation::of).toList();
    return DateCarSelectMenu.of(validatedClassCar);
  }
  //ClassCar정보와 함께 예약 가능 여부를 포함하고 있는 DTO
  @AllArgsConstructor
  @Getter
  public static class ClassCarValidation{
    private ClassCar classCar;
    private Program program;
    private boolean reservationAvailable;
    public static ClassCarValidation of(ClassCar c){
      Program selectedProgram = c.getDrivingClass().getProgram();
      List<Participation> participationList = c.getParticipationList();
      long totalAmount = totalParticipationCount(participationList);
      boolean reservationAvailable = totalAmount < c.getMaximumOccupancy();
      return new ClassCarValidation(c,selectedProgram,reservationAvailable);
    }
  }
  private static long totalParticipationCount(List<Participation> participationList){
    return participationList.stream()
            .collect(Collectors.summarizingLong(Participation::getParticipants)).getSum();
  }
}
