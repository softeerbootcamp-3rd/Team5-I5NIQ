package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Car;
import com.softeer.BE.domain.entity.Comment;
import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.domain.entity.Program;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UsersResponse {
  private static final String UNIQUE = "UNIQUE";
  private static final String DUPLICATE = "DUPLICATE";

  // 사용자의 수업 참여 상태
  private static final String COMPLETE_PAYMENT = "결제완료";
  private static final String COMPLETE_PARTICIPATION = "참여완료";

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
    private String status;

    public static ProgramList of(Long participationId, Program program, LocalDateTime startDateTime, boolean status){
      return new ProgramList(
              participationId,
              program.getCategory().name(),
              program.getName().name(),
              program.getLevel().name(),
              startDateTime,
              (status) ? COMPLETE_PARTICIPATION : COMPLETE_PAYMENT
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

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class UpcomingClass{
    private int num;
    private String category;
    private String programName;
    private String level;
    private LocalDateTime startDateTime;

    public static UpcomingClass of(int num, Program program, LocalDateTime startDateTime){
      return new UpcomingClass(
              num,
              program.getCategory().name(),
              program.getName().name(),
              program.getLevel().name(),
              startDateTime
      );
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class RecentComment{
    private String category;
    private String programName;
    private String level;
    private String contents;

    public static RecentComment of(Comment comment){
      return new RecentComment(
        comment.getProgram().getCategory().name(),
        comment.getProgram().getName().name(),
        comment.getProgram().getLevel().name(),
        comment.getContent()
      );
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class MyPageContents{
    private String name;
    private int level;
    private int totalClassNum;
    private int pastClassNum;
    private UpcomingClass upcomingClass;
    private RecentComment recentComment;

    public static MyPageContents of(UpcomingClass upcomingClass, RecentComment recentComment, String name, int level, int totalNum, int pastNum){
      return new MyPageContents(
              name,
              level,
              totalNum,
              pastNum,
              upcomingClass,
              recentComment
      );
    }
  }
}
