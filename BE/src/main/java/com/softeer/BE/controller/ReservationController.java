package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.ReservationResponse;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;
import com.softeer.BE.global.exception.GeneralHandler;
import com.softeer.BE.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    @GetMapping("/step1/car")
    public ApiResponse<List<ReservationResponse.Step1CarStatus>> listStep1CarStatus(HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        if(httpSession == null)
            throw new GeneralHandler(ErrorStatus._UNAUTHORIZED);
        return ApiResponse.onSuccess(reservationService.getStep1CarStatusList());
    }

    @GetMapping("/step2/car/{carId}")
    public ApiResponse<List<ReservationResponse.Step2ProgramStatus>> listStep2ProgramStatus(HttpServletRequest request, @PathVariable Long carId){
        HttpSession httpSession = request.getSession(false);
        if(httpSession == null)
            throw new GeneralHandler(ErrorStatus._UNAUTHORIZED);
        return ApiResponse.onSuccess(reservationService.getStep2ProgramStatusList(carId));
    }
}
