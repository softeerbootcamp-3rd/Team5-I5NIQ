package com.softeer.BE.global.apiPayload.code.statusEnums;

import com.softeer.BE.global.apiPayload.code.BaseCode;
import com.softeer.BE.global.apiPayload.code.SuccessDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 성공 응답
    _OK(HttpStatus.OK, "COMMON200", "요청에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public SuccessDTO getDto() {
        return SuccessDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public SuccessDTO getHttpStatusDto() {
        return SuccessDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
