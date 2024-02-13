package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Car;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.service.ProgramReservationService.ClassCarValidation;
import com.softeer.BE.service.ProgramReservationService.ProgramValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationResponse {
  /** ProgramSelectMenu
   * "프로그램 먼저 선택하기" Step1 응답용 데이터
   */
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ProgramSelectMenu{
    //key : 회사이름, value : 회사에 속하는 프로그램 정보
    private HashMap<String,CompanyProgram> companyPrograms;
    private Integer companyCount;
    public static ProgramSelectMenu of(HashMap<Long, ProgramValidation> programs){
      HashMap<String,CompanyProgram> companyProgramMap = new HashMap<>();
      // 회사 이름을 key로 가질 수 있도록 Map 초기화 작업
      for(ProgramCategory pc : ProgramCategory.values()){
        companyProgramMap.put(pc.name(),new CompanyProgram(new HashMap<>(),0));
      }
      // 가져온 ProgramValidation객체를 회사에 맞게 매핑
      // ProgramValidation 내부에는 Program정보와 예약 가능 여부 정보가 들어 있음
      for(Map.Entry<Long,ProgramValidation> entry : programs.entrySet()){
        ProgramValidation program = entry.getValue();
        CompanyProgram companyProgram = companyProgramMap.get(program.getCompanyName());
        companyProgram.putProgram(program);
      }
      return new ProgramSelectMenu(companyProgramMap,companyProgramMap.size());
    }
  }
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  private static class CompanyProgram{
    //key : 레벨 이름, value : 해당 레벨 프로그램의 자세한 정보
    private HashMap<String,ProgramLevel> programs;
    private Integer programCount;
    //ProgramValidation을 받아서 ProgramLevel로 변환 후 HashMap에 삽입
    public void putProgram(ProgramValidation program){
      this.programs.put(program.getLevelName(),ProgramLevel.of(program));
      programCount+=1;
    }
  }
  /** ProgramLevel
   * "프로그램 먼저 선택하기" 예약화면 step1에서 선택하고자 하는 프로그램의 단위 (날짜를 고려하지 않는 프로그램)
   */
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  private static class ProgramLevel{
    private Long programId;
    // 프로그램 이름 ex. DRIVING_EXPERIENCE,DRIVING_PLEASURE
    private String programName;
    // 프로그램 레벨 ex. LEVEL_1,N_ADVANCED
    private String programLevel;
    //주관 회사 ex. KIA, HYUNDAI, GENESIS
    private String managerCompany;
    //예약 가능 여부
    private Boolean canReservation;
    public static ProgramLevel of(ProgramValidation p){
      Program programEntity = p.getProgram();
      return new ProgramLevel(programEntity.getId(),programEntity.getName().name(),programEntity.getLevel().name(),
              programEntity.getCategory().name(),p.isReservationAvailable());
    }
  }
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class DateCarSelectMenu{
    HashMap<String,DateSelectMenu> selectMenus;
    public static DateCarSelectMenu of(List<ClassCarValidation> classCarValidations){
      HashMap<String,DateSelectMenu> selectMenus = new HashMap<>();
      for(ClassCarValidation c : classCarValidations){
        Car car = c.getClassCar().getCar();
        String carName = car.getName();
        if(!selectMenus.containsKey(carName))
          selectMenus.put(carName,new DateSelectMenu(new HashMap<>()));
        DateSelectMenu selectMenu = selectMenus.get(carName);
        selectMenu.put(c);
      }
      return new DateCarSelectMenu(selectMenus);
    }
  }

  /** DateSelectMenu
   * "프로그램 먼저 선택하기" Step2 응답용 데이터
   */
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  private static class DateSelectMenu{
    HashMap<LocalDate,ProgramDate> programDates;
    public void put(ClassCarValidation classCarValidation){
      LocalDate keyDate = classCarValidation.getClassCar().getDrivingClass().getStartDateTime()
              .toLocalDate();
      if(!programDates.containsKey(keyDate))
        programDates.put(keyDate,ProgramDate.of(classCarValidation,keyDate));
      else
        programDates.get(keyDate).setCanReservation(classCarValidation);
    }
  }

  /** Program
   * "프로그램 먼저 선택하기" 예약화면 step2에서 선택하고자 하는 프로그램의 단위
   *  날짜정보(시간 제외), 프로그램 Id, 자동차 Id 가 다음 step3로 넘어가야함.
   */
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  private static class ProgramDate{
    private Long carId;
    private Long programId;
    private LocalDate reservationDate;
    private Boolean canReservation;
    public static ProgramDate of(ClassCarValidation c,LocalDate reservationDate){
      return new ProgramDate(c.getClassCar().getCar().getId(),c.getProgram().getId(),
              reservationDate,c.isReservationAvailable());
    }
    public void setCanReservation(ClassCarValidation c){
      this.canReservation = this.canReservation | c.isReservationAvailable();
    }
  }
}
