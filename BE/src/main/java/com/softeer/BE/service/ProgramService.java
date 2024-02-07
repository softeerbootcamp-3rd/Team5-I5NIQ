package com.softeer.BE.service;

import com.softeer.BE.domain.dto.ProgramResponse.ProgramInformation;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramService {
  private final ProgramRepository programRepository;
  public ProgramInformation getDetailInformation(long programId){
    Program program = programRepository.findById(programId).orElseThrow(()->new RuntimeException("invalid program id"));
    return ProgramInformation.of(program);
  }
}
