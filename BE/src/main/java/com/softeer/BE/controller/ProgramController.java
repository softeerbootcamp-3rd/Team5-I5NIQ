package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.ProgramResponse;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramInformation;
import com.softeer.BE.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/program")
public class ProgramController {
  @GetMapping("/information")
  public ApiResponse<ProgramInformation> getDetailInformation(@RequestParam(value = "program-id")Long programId){
    return null;
  }
}
