package com.softeer.BE.service;

import com.softeer.BE.domain.dto.ProgramResponse.ProgramComments;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramDetail;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramInformation;
import com.softeer.BE.domain.dto.ProgramResponse.ProgramLocations;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;
import com.softeer.BE.global.exception.GeneralHandler;
import com.softeer.BE.repository.CommentRepository;
import com.softeer.BE.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramService {
  private final ProgramRepository programRepository;
  private final CommentRepository commentRepository;
  public ProgramInformation getDetailInformation(long programId){
    Program program = findByIdOrElseThrow(programId);
    return ProgramInformation.of(program);
  }
  public ProgramDetail getDetail(long programId){
    Program program = findByIdOrElseThrow(programId);
    return ProgramDetail.of(program);
  }
  public ProgramLocations getLocations(long programId){
    Program program = findByIdOrElseThrow(programId);
    return ProgramLocations.of(program);
  }
  public ProgramComments getComments(long programId){
    Program program = findByIdOrElseThrow(programId);
    return ProgramComments.of(commentRepository.findCommentsByProgramId(program.getId()));
  }
  private Program findByIdOrElseThrow(long programId){
    return programRepository.findById(programId).orElseThrow(()->new GeneralHandler(ErrorStatus.PROGRAM_NOT_FOUND));
  }
}
