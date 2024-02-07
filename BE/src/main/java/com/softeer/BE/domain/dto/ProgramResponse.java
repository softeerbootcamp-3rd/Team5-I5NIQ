package com.softeer.BE.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProgramResponse {
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ProgramInformation{
    private String level;
    private String programDescription;
    private List<String> programCars;
    private String estimatedDuration;
    private String maxMemberNumber;
    private String cost;
    private String qualification;
  }
}
