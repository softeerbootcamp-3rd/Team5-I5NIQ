package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.enums.ProgramName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query("SELECT p " +
            "FROM program p " +
            "WHERE p.name = :programName AND EXISTS " +
            "(SELECT d " +
            "FROM p.drivingClassList d " +
            "WHERE FUNCTION('DATE', d.startDateTime) = :localDate)")
    List<Program> findAllByDateAndName(LocalDate localDate, ProgramName programName);

    @Query("SELECT p " +
            "FROM program p " +
            "WHERE EXISTS " +
            "(SELECT d " +
            "FROM p.drivingClassList d " +
            "WHERE FUNCTION('DATE', d.startDateTime) = :localDate)")
    List<Program> findAllByDate(LocalDate localDate);

}
