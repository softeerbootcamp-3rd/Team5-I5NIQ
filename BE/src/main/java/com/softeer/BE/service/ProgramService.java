package com.softeer.BE.service;

import com.softeer.BE.domain.dto.ProgramResponse;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramDetail;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramInformation;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramLocations;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramService {
  private final ProgramRepository programRepository;
  public ProgramInformation getDetailInformation(long programId){
    Program program = findByIdOrElseThrow(programId, new RuntimeException("invalid program id"));
    return ProgramInformation.of(program);
  }
  public ProgramDetail getDetail(long programId){
    Program program = findByIdOrElseThrow(programId, new RuntimeException("invalid program id"));
    return ProgramDetail.of(program);
  }
  public ProgramLocations getLocations(long programId){
    Program program = findByIdOrElseThrow(programId, new RuntimeException("invalid program id"));
    return ProgramLocations.of(program);
  }
  private Program findByIdOrElseThrow(long programId, RuntimeException e){
    return programRepository.findById(programId).orElseThrow(()->e);
  }
}
