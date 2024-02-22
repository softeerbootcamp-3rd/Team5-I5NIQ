package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.KeyAndList;
import com.softeer.BE.domain.dto.KeyAndValue;
import com.softeer.BE.domain.dto.ProgramResponse;
import com.softeer.BE.domain.dto.ReservationResponse.ProgramCategorySelectMenu;
import com.softeer.BE.domain.dto.ReservationStep3Response.ProgramSelectMenuStep3;
import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.repository.UsersRepository;
import com.softeer.BE.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

import com.softeer.BE.domain.dto.ReservationResponse.DateCarSelectMenu;
import com.softeer.BE.domain.dto.ReservationResponse.ProgramSelectMenu;
import com.softeer.BE.service.ProgramReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.softeer.BE.domain.dto.ReservationResponse;
import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;
import com.softeer.BE.global.exception.GeneralHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final UsersRepository usersRepository;
    private final ProgramReservationService programReservationService;

    @GetMapping("/step3")
    public ApiResponse<ProgramSelectMenuStep3> getAllSelectMenuInStep3(@RequestParam(value = "program-id") Long programId
            , @RequestParam(value = "car-id") Long carId
            , @RequestParam(value = "reservation-date") LocalDate reservationDate) {
        return ApiResponse.onSuccess(reservationService.searchForStep3AvailableClassCar(reservationDate, programId, carId));
    }

    @PostMapping
    public ApiResponse<Boolean> reservation(@RequestParam("class-id") long classCarId,
                                            @RequestParam("amount") long reservationSize) {
        Users user = usersRepository.findById("userId1").get();
        if (reservationSize < 1)
            ApiResponse.onFailure("invalid param", "reservationSize값은 1이상이어야 합니다.", false);
        return ApiResponse.onSuccess(reservationService.classCarReservation(classCarId, reservationSize, user));
    }

    //program id 결정
    @GetMapping("/step1/program")
    public ApiResponse<ProgramCategorySelectMenu> getAllProgramReservationStatus() {
        ProgramCategorySelectMenu result = programReservationService.searchForAvailableProgram();
        return ApiResponse.onSuccess(result);
    }

    //날짜(시간 제외)결정, 차량 결정
    @GetMapping("/step2/program")
    public ApiResponse<DateCarSelectMenu> getAllDateCarSelectMenu(@RequestParam(value = "program-id") Long programId) {
        return ApiResponse.onSuccess(programReservationService.searchForAvailableDateCars(programId));
    }

    @GetMapping("/step1/car")
    public ApiResponse<List<ReservationResponse.Step1CarStatus>> listStep1CarStatus(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null)
            throw new GeneralHandler(ErrorStatus._UNAUTHORIZED);
        return ApiResponse.onSuccess(reservationService.getStep1CarStatusList());
    }

    @GetMapping("/step2/car/{carId}")
    public ApiResponse<List<ReservationResponse.Step2ProgramStatus>> listStep2ProgramStatus(HttpServletRequest request, @PathVariable Long carId) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null)
            throw new GeneralHandler(ErrorStatus._UNAUTHORIZED);
        return ApiResponse.onSuccess(reservationService.getStep2ProgramStatusList(carId));
    }

    @GetMapping("/step1/date") // 첫번째 화면, 일정마다 상태 표시
    public ApiResponse<List<KeyAndValue<LocalDate, ReservationStatus>>> getDateAndStatusList() {
        return ApiResponse.onSuccess(reservationService.getDateAndStatusList());
    }

    @GetMapping("/step1/date/{date}") // 두번째 화면, 프로그램마다 상태 표시
    public ApiResponse<List<KeyAndList<ProgramName, KeyAndList<ProgramCategory, ProgramResponse.ProgramReservationInfo>>>>
    getProgramAndStatusList(@PathVariable LocalDate date) {
        return ApiResponse.onSuccess(reservationService.getProgramAndStatusList(date));
    }

    @GetMapping("/step2/date/{date}/{programId}") // 세번째 화면, 차량마다 상태 표시
    public ApiResponse<ProgramResponse.ProgramCarStatusList> getCarAndStatusList(@PathVariable LocalDate date,
                                                                                 @PathVariable Long programId) {
        return ApiResponse.onSuccess(reservationService.getCarAndStatusList(date, programId));
    }
}
