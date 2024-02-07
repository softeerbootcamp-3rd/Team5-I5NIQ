package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.ProgramResponse;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramDetail;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramInformation;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/program")
@RequiredArgsConstructor
public class ProgramController {
  private final ProgramService programService;
  @GetMapping("/information")
  public ApiResponse<ProgramInformation> getDetailInformation(@RequestParam(value = "program-id")long programId){
    return ApiResponse.onSuccess(programService.getDetailInformation(programId));
  }
  @GetMapping("/detail")
  public ApiResponse<ProgramDetail> getDetail(@RequestParam(value = "program-id")long programId){
    return ApiResponse.onSuccess(null);
  }
}
