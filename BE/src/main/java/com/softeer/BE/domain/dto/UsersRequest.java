package com.softeer.BE.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UsersRequest {
  @NoArgsConstructor
  @Getter
  public static class JoinForm{
    private String name;
    private String userId;
    private String password;
  }
}
