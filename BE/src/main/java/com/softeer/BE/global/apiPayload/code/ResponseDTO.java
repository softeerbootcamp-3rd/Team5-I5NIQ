package com.softeer.BE.global.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseDTO {

    private final boolean isSuccess;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
