package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.ReservationStep3Response;
import com.softeer.BE.domain.dto.ReservationStep3Response.ProgramSelectMenuStep3;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
  private final ReservationService reservationService;
  @GetMapping("/step3")
  public ApiResponse<ProgramSelectMenuStep3> getAllSelectMenuInStep3(@RequestParam(value = "program-id")Long programId
          ,@RequestParam(value = "car-id")Long carId
          ,@RequestParam(value = "reservation-date")LocalDate reservationDate){
   return ApiResponse.onSuccess(reservationService.searchForStep3AvailableClassCar(reservationDate,programId,carId));
  }
}
