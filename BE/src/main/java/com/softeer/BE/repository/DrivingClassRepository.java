package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.DrivingClass;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DrivingClassRepository extends JpaRepository<DrivingClass, Long> {
    @Query("SELECT DISTINCT FUNCTION('DATE', d.startDateTime) " +
            "FROM driving_class d JOIN d.program p " +
            "WHERE FUNCTION('DATE', d.startDateTime) < :date AND p.name = :name " +
            "ORDER BY FUNCTION('DATE', d.startDateTime) DESC")
    List<LocalDate> findAll(String name, LocalDate date, Pageable pageable);

    @Query("SELECT d " +
            "FROM driving_class d JOIN d.program p " +
            "WHERE FUNCTION('DATE', d.startDateTime) = :date AND p.name = :name")
    List<DrivingClass> findAll(String name, LocalDate date);

    @Query("SELECT d " +
            "FROM driving_class d " +
            "ORDER BY d.id DESC")
    List<DrivingClass> findAllOrderByIdDesc();

    @Query("SELECT d FROM driving_class d WHERE :today BETWEEN d.reservationStartTime and d.reservationDeadline")
    List<DrivingClass> findAllByReservationDate(@Param(value = "today")LocalDateTime today);
}
