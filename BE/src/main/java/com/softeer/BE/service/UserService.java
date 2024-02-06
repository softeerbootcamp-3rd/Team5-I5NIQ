package com.softeer.BE.service;

import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UsersRepository usersRepository;

  public Boolean isDuplicated(String userId){
    Optional<Users> user = usersRepository.findById(userId);
    if(user.isPresent())
      return Boolean.TRUE;
    return Boolean.FALSE;
  }
}
