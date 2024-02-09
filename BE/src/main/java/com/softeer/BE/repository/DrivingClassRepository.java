package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.enums.ProgramName;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface DrivingClassRepository extends JpaRepository<DrivingClass, Long> {
    @Query("SELECT DISTINCT FUNCTION('DATE', d.startDateTime) " +
            "FROM driving_class d JOIN d.program p " +
            "WHERE FUNCTION('DATE', d.startDateTime) < :date AND p.name = :name " +
            "GROUP BY DATE(d.startDateTime) " +
            "ORDER BY FUNCTION('DATE', d.startDateTime) DESC")
    List<Date> findAll(ProgramName name, LocalDate date, Pageable pageable);

    @Query("SELECT d " +
            "FROM driving_class d JOIN d.program p " +
            "WHERE FUNCTION('DATE', d.startDateTime) = :date AND p.name = :name")
    List<DrivingClass> findAll(ProgramName name, LocalDate date);

    @Query("SELECT d " +
            "FROM driving_class d " +
            "ORDER BY d.id DESC")
    List<DrivingClass> findAllOrderByIdDesc();

    @Query("SELECT d " +
            "FROM driving_class d " +
            "WHERE d.reserveDeadline < NOW() " +
            "ORDER BY d.startDateTime ASC")
    List<DrivingClass> findValidClass();
}
