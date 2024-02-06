package com.softeer.BE.global.apiPayload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T>{
  private ApiCode code;
  private String message;
  private Boolean isSuccess;
  private T body;
}
