package com.softeer.BE.global.exception;

import com.softeer.BE.global.apiPayload.code.BaseErrorCode;
import com.softeer.BE.global.apiPayload.code.ErrorDTO;
import lombok.Getter;

@Getter
public class GeneralHandler extends RuntimeException {

    private BaseErrorCode errorStatus;

    public GeneralHandler(BaseErrorCode errorStatus) {
        super(errorStatus.getDto().getMessage());
        this.errorStatus = errorStatus;
    }

    public ErrorDTO getError() {
        return this.errorStatus.getDto();
    }

    public ErrorDTO getErrorHttpStatus() {
        return this.errorStatus.getHttpStatusDto();
    }
}
