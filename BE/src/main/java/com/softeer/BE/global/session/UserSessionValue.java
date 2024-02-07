package com.softeer.BE.global.session;

import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.domain.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSessionValue{
  private String userId;
  private Role role;
  public static UserSessionValue of(Users users){
    return new UserSessionValue(users.getId(),users.getRole());
  }
}
