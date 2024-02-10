package com.softeer.BE.service;

import com.softeer.BE.domain.dto.UsersRequest.JoinForm;
import com.softeer.BE.domain.dto.UsersRequest.LoginForm;
import com.softeer.BE.domain.dto.UsersResponse;
import com.softeer.BE.domain.entity.*;
import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;
import com.softeer.BE.global.exception.GeneralHandler;
import com.softeer.BE.repository.UsersRepository;
import com.softeer.BE.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
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
      throw new RuntimeException("duplicate user exception");
    usersRepository.save(JoinForm.toUsers(joinForm));
  }
  public boolean validUser(LoginForm loginForm){
    Optional<Users> user = usersRepository.findById(loginForm.getId());
    if(user.isEmpty())
      return false;
    return loginForm.getPassword().equals(user.get().getPassword());
  }
  public Users findUserAfterValidation(String userId){
    return usersRepository.findById(userId).orElseThrow(()->new RuntimeException("invalid user id"));
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
                          drivingClass.getStartDateTime()
                  );
              }).collect(Collectors.toList());
  }

  public UsersResponse.ParticipationDetail getParticipationDetail(Long participationId){
      Participation participation = participationRepository.findById(participationId)
              .orElseThrow(() -> new GeneralHandler(ErrorStatus.PARTICIPATION_NOT_FOUND));

      ClassCar classCar = participation.getClassCar();
      DrivingClass drivingClass = classCar.getDrivingClass();
      return UsersResponse.ParticipationDetail.of(
              participation,
              drivingClass.getProgram(),
              drivingClass.getStartDateTime(),
              classCar.getCar()
      );
  }
}
