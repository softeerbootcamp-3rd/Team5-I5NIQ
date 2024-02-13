package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface DrivingClassRepository extends JpaRepository<DrivingClass, Long> {

    @Query("SELECT d " +
            "FROM driving_class d " +
            "WHERE d.reservationDeadline > CURRENT_TIMESTAMP " +
            "ORDER BY d.startDateTime")
    List<DrivingClass> findAvailableClass();

    @Query("SELECT d " +
            "FROM driving_class d " +
            "WHERE FUNCTION('DATE', d.startDateTime) = :localDate " +
            "AND d.program = :program " +
            "AND d.reservationDeadline > CURRENT_TIMESTAMP")
    List<DrivingClass> findByProgramAndStartDateTime(Program program, LocalDate localDate);

    @Query("SELECT DISTINCT FUNCTION('DATE', d.startDateTime) " +
            "FROM driving_class d JOIN d.program p " +
            "WHERE FUNCTION('DATE', d.startDateTime) < :date AND p.name = :name " +
            "ORDER BY FUNCTION('DATE', d.startDateTime) DESC")
    Page<Date> findAll(ProgramName name, LocalDate date, Pageable pageable);

    @Query("SELECT COUNT(d) > 0 " +
            "FROM driving_class d " +
            "WHERE FUNCTION('DATE', d.startDateTime) < :lastDate")
    boolean existsByDateLessThan(LocalDate lastDate);
}
