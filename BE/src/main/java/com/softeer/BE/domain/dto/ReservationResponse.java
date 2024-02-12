package com.softeer.BE.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReservationResponse {
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ProgramSelectMenu{
    private List<CompanyProgram> companyPrograms;
    private Integer companyCount;
  }
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  private static class CompanyProgram{
    private List<ProgramLevel> programs;
    private Integer programCount;
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
  }
}
