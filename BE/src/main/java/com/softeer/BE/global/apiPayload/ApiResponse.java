package com.softeer.BE.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.softeer.BE.global.apiPayload.code.BaseCode;
import com.softeer.BE.global.apiPayload.code.statusEnums.SuccessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

  @JsonProperty("isSuccess")
  private final Boolean isSuccess;

  private final String code;

  private final String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T result;


  // 성공 응답
  public static <T> ApiResponse<T> onSuccess(T result){
    return new ApiResponse<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), result);
  }

  public static <T> ApiResponse<T> of(BaseCode code, T result){
    return new ApiResponse<>(true, code.getHttpStatusDto().getCode() , code.getHttpStatusDto().getMessage(), result);
  }

  // 실패 응답
  public static <T> ApiResponse<T> onFailure(String code, String message, T data){
    return new ApiResponse<>(false, code, message, data);
  }

}