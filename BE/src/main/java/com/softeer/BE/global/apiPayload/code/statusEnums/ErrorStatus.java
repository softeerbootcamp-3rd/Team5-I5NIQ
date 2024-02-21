package com.softeer.BE.global.apiPayload.code.statusEnums;

import com.softeer.BE.global.apiPayload.code.BaseCode;
import com.softeer.BE.global.apiPayload.code.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 테스트용
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "Error 테스트"),

    // 회원 관련
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "존재하지 않는 사용자 ID입니다."),
    DUPLICATED_USERID(HttpStatus.BAD_REQUEST, "USER4091", "이미 존재하는 사용자 ID입니다."),

    // 공지 관련
    NOTICE_NOT_FOUND(HttpStatus.BAD_REQUEST, "NOTICE4001", "존재하지 않는 공지 ID입니다."),

    // 프로그램 관련
    PROGRAM_NOT_FOUND(HttpStatus.BAD_REQUEST, "PROGRAM4001", "존재하지 않는 프로그램 ID입니다."),

    // 차량 관련
    CAR_NOT_FOUND(HttpStatus.BAD_REQUEST, "CAR4001", "존재하지 않는 차량 ID입니다."),

    // 수업 차량 관련
    CLASS_CAR_NOT_FOUND(HttpStatus.BAD_REQUEST, "CLASSCAR4001", "존재하지 않는 수업 차량 ID입니다."),

    // 참여 프로그램 관련
    PARTICIPATION_NOT_FOUND(HttpStatus.BAD_REQUEST, "PARTICIPATION4001", "존재하지 않는 프로그램 예약 ID입니다."),
    UNAUTHORIZED_USER_ACCESS(HttpStatus.UNAUTHORIZED, "PARTICIPATION4002", "해당 예약정보에 접근 권한이 없는 사용자입니다."),

    // 예약 관련
    INVALID_RESERVATION_SIZE(HttpStatus.BAD_REQUEST, "RESERVATION4001", "예약 인원 수는 1 이상이어야 합니다."),

    RESERVATION_FULL(HttpStatus.CONFLICT, "RESERVATION4091", "최대 인원이 충족되어 예약할 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ResponseDTO getDto() {
        return ResponseDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ResponseDTO getHttpStatusDto() {
        return ResponseDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
