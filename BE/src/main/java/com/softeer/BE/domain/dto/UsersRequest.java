package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.domain.entity.enums.License;
import com.softeer.BE.domain.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UsersRequest {
  @NoArgsConstructor
  @Getter
  public static class JoinForm{
    private String name;
    private String id;
    private String password;
    public static Users toUsers(JoinForm j){
      return new Users(j.getId(),j.getName(),j.getPassword(), Role.USER, License.NOT_APPLICABLE,
              null,null);
    }
  }
  @NoArgsConstructor
  @Getter
  public static class LoginForm{
    private String id;
    private String password;
  }
}
