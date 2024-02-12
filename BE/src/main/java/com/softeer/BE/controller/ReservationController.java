package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.ReservationResponse.ProgramSelectMenu;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.ProgramReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
  private final ProgramReservationService programReservationService;
  @GetMapping("/step1/program")
  public ApiResponse<ProgramSelectMenu> getAllProgramReservationStatus(){
    ProgramSelectMenu result = programReservationService.searchForAvailableProgram();
    return ApiResponse.onSuccess(result);
  }
}
