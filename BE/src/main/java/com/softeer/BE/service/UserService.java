package com.softeer.BE.service;

import com.softeer.BE.domain.dto.UsersRequest.JoinForm;
import com.softeer.BE.domain.dto.UsersRequest.LoginForm;
import com.softeer.BE.domain.dto.UsersResponse;
import com.softeer.BE.domain.entity.*;
import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;
import com.softeer.BE.global.exception.GeneralHandler;
import com.softeer.BE.repository.CommentRepository;
import com.softeer.BE.repository.UsersRepository;
import com.softeer.BE.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
  private final UsersRepository usersRepository;
  private final ParticipationRepository participationRepository;
  private final CommentRepository commentRepository;

  private static final String UPCOMING = "upcoming";
  private static final String PAST = "past";

  public Boolean isDuplicated(String userId){
    Optional<Users> user = usersRepository.findById(userId);
    if(user.isPresent())
      return Boolean.TRUE;
    return Boolean.FALSE;
  }

  @Transactional
  public void join(JoinForm joinForm){
    if(usersRepository.findById(joinForm.getId()).isPresent())
      throw new GeneralHandler(ErrorStatus.DUPLICATED_USERID);
    usersRepository.save(JoinForm.toUsers(joinForm));
  }
  public boolean validUser(LoginForm loginForm){
    Users user = usersRepository.findById(loginForm.getId()).orElseThrow(()-> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));
    return loginForm.getPassword().equals(user.getPassword());
  }
  public Users findUserAfterValidation(String userId){
    return usersRepository.findById(userId).orElseThrow(()->new GeneralHandler(ErrorStatus.USER_NOT_FOUND));
  }

  public List<UsersResponse.ProgramList> getUserProgramList(String userId, String status) {
      List<Participation> participationList;
      if (UPCOMING.equals(status)) {
          participationList = participationRepository.findUpcomingParticipationByUserId(userId);
      } else if (PAST.equals(status)) {
          participationList = participationRepository.findPastParticipationByUserId(userId);
      } else {
          participationList = participationRepository.findByUserId(userId);
      }

      return participationList.stream()
              .map(participation -> {
                  DrivingClass drivingClass = participation.getClassCar().getDrivingClass();
                  return UsersResponse.ProgramList.of(
                          participation.getId(),
                          drivingClass.getProgram(),
                          drivingClass.getStartDateTime(),
                          participation.isCompletion()
                  );
              }).collect(Collectors.toList());
  }

  public UsersResponse.ParticipationDetail getParticipationDetail(String userId, Long participationId){
      Participation participation = participationRepository.findById(participationId)
              .orElseThrow(() -> new GeneralHandler(ErrorStatus.PARTICIPATION_NOT_FOUND));

      if(!participation.getUser().getId().equals(userId))
          throw new GeneralHandler(ErrorStatus.USER_NOT_FOUND);

      ClassCar classCar = participation.getClassCar();
      DrivingClass drivingClass = classCar.getDrivingClass();
      return UsersResponse.ParticipationDetail.of(
              participation,
              drivingClass.getProgram(),
              drivingClass.getStartDateTime(),
              classCar.getCar()
      );
  }

  private int determineUserLevel(int pastProgramNum) {
      if (pastProgramNum >= 8) {
          return 3; // 8개 이상의 프로그램에 참여한 경우, 레벨 3
      } else if (pastProgramNum >= 3) {
          return 2; // 3개 이상의 프로그램에 참여한 경우, 레벨 2
      } else if (pastProgramNum >= 1) {
          return 1; // 1개 이상의 프로그램에 참여한 경우, 레벨 1
      }
      return 0; // 참여한 프로그램이 없는 경우, 레벨 0
  }

  private UsersResponse.UpcomingClass getUpcomingClass(List<Participation> participationList){
      int upcomingClassCount = 0;
      Participation firstUpcomingParticipation = null;

      for (Participation participation : participationList) {
          LocalDateTime startDateTime = participation.getClassCar().getDrivingClass().getStartDateTime();
          if (startDateTime.isAfter(LocalDateTime.now())) {
              if (firstUpcomingParticipation == null) {
                  firstUpcomingParticipation = participation;
              }
              upcomingClassCount++;
          }
      }

      UsersResponse.UpcomingClass upcomingClass = null;
      if (firstUpcomingParticipation != null) {
          Program program = firstUpcomingParticipation.getClassCar().getDrivingClass().getProgram();
          LocalDateTime startDateTime = firstUpcomingParticipation.getClassCar().getDrivingClass().getStartDateTime();
          upcomingClass = UsersResponse.UpcomingClass.of(upcomingClassCount, program, startDateTime);
      }

      return upcomingClass;
  }

  public UsersResponse.MyPageContents getMyPageContents(String userId){
      Users user = usersRepository.findById(userId).orElseThrow(()-> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));

      List<Participation> participationList = participationRepository.findAllByUserIdOrderByStartDateTime(userId);

      int totalClassNum = participationList.size();
      UsersResponse.UpcomingClass upcomingClass = getUpcomingClass(participationList);
      // Participation의 completion이 true인 요소의 수를 센다.
      int pastClassNum = (int) participationList.stream()
              .filter(Participation::isCompletion)
              .count();
      int userLevel = determineUserLevel(pastClassNum);

      UsersResponse.RecentComment recentComment = null;
      List<Comment> commentList = commentRepository.findRecentCommentByUserId(userId, PageRequest.of(0, 1));
      if (!commentList.isEmpty()) {
          recentComment = UsersResponse.RecentComment.of(commentList.get(0));
      }

      return UsersResponse.MyPageContents.of(
              upcomingClass,
              recentComment,
              user.getName(),
              userLevel,
              totalClassNum,
              pastClassNum
      );
  }
}
