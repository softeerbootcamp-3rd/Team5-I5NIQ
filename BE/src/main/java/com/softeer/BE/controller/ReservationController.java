package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.ReservationResponse.DateCarSelectMenu;
import com.softeer.BE.domain.dto.ReservationResponse.ProgramSelectMenu;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.ProgramReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
  private final ProgramReservationService programReservationService;
  //program id 결정
  @GetMapping("/step1/program")
  public ApiResponse<ProgramSelectMenu> getAllProgramReservationStatus(){
    ProgramSelectMenu result = programReservationService.searchForAvailableProgram();
    return ApiResponse.onSuccess(result);
  }
  //날짜(시간 제외)결정, 차량 결정
  @GetMapping("/step2/program")
  public ApiResponse<DateCarSelectMenu> getAllDateCarSelectMenu(@RequestParam(value = "program-id")Long programId){
    return ApiResponse.onSuccess(programReservationService.searchForAvailableDateCars(programId));
  }
}
