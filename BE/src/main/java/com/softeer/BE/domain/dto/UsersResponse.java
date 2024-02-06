package com.softeer.BE.domain.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class UsersResponse {
  private static final String UNIQUE = "UNIQUE";
  private static final String DUPLICATE = "DUPLICATE";
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UserIdDuplicated{
    private String userIdValidation;
    public static UserIdDuplicated of(boolean isDuplicate){
      if(isDuplicate) return new UserIdDuplicated(DUPLICATE);
      return new UserIdDuplicated(UNIQUE);
    }
  }
}
