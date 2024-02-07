package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Car;
import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UsersResponse {
  private static final String UNIQUE = "UNIQUE";
  private static final String DUPLICATE = "DUPLICATE";
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class UserIdDuplicated{
    private String userIdValidation;
    public static UserIdDuplicated of(boolean isDuplicate){
      if(isDuplicate) return new UserIdDuplicated(DUPLICATE);
      return new UserIdDuplicated(UNIQUE);
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ProgramList{
    private Long participationId;
    private String category;
    private String programName;
    private String level;
    private LocalDateTime dateTime;

    public static ProgramList of(Long participationId, Program program, Schedule schedule){
      return new ProgramList(
              participationId,
              program.getCategory().name(),
              program.getName().name(),
              program.getLevel().name(),
              schedule.getStartDateTime()
      );
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ParticipationDetail{
    private Long participationId;
    private String programName;
    private String programCategory;
    private String programLevel;
    private String carName;
    private Long participants;
    private LocalDateTime startDateTime;

    public static ParticipationDetail of(Participation participation, Program program, LocalDateTime startDateTime, Car car){
      return new ParticipationDetail(
              participation.getId(),
              program.getName().name(),
              program.getCategory().name(),
              program.getLevel().name(),
              car.getName(),
              participation.getParticipants(),
              startDateTime
      );
    }
  }
}
