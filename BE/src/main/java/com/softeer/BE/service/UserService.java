package com.softeer.BE.service;

import com.softeer.BE.domain.dto.UsersRequest;
import com.softeer.BE.domain.dto.UsersRequest.JoinForm;
import com.softeer.BE.domain.dto.UsersRequest.LoginForm;
import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
  private final UsersRepository usersRepository;

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
}
