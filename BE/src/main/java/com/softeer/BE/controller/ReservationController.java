package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.ReservationStep3Response.ProgramSelectMenuStep3;
import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.repository.UsersRepository;
import com.softeer.BE.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
  private final ReservationService reservationService;
  private final UsersRepository usersRepository;
  @GetMapping("/step3")
  public ApiResponse<ProgramSelectMenuStep3> getAllSelectMenuInStep3(@RequestParam(value = "program-id")Long programId
          ,@RequestParam(value = "car-id")Long carId
          ,@RequestParam(value = "reservation-date")LocalDate reservationDate){
   return ApiResponse.onSuccess(reservationService.searchForStep3AvailableClassCar(reservationDate,programId,carId));
  }

  @PostMapping
  public ApiResponse<Boolean> reservation(@RequestParam("class-id")long classCarId,
                                          @RequestParam("amount")long reservationSize){
    Users user = usersRepository.findById("userId1").get();
    if(reservationSize<1)
      ApiResponse.onFailure("invalid param","reservationSize값은 1이상이어야 합니다.",false);
    return ApiResponse.onSuccess(reservationService.classCarReservation(classCarId,reservationSize,user));
  }
}
