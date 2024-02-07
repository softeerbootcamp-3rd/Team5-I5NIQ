package com.softeer.BE.service;

import com.softeer.BE.global.exception.GeneralHandler;
import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new GeneralHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}
