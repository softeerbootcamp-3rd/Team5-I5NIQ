package com.softeer.BE.repository;

import com.softeer.BE.domain.dto.ScheduleDto;
import com.softeer.BE.domain.entity.Schedule;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "SELECT DISTINCT DATE(s.start_date_time) " +
                    "from schedule s " +
                    "join program p on s.program_id = p.id " +
                    "where DATE(start_date_time) < :date and p.name = :name " +
                    "order by DATE(s.start_date_time) desc " +
                    "limit :size", nativeQuery = true)
    List<LocalDate> findAll(String name, LocalDate date, Long size);

    @Query("SELECT DISTINCT FUNCTION('DATE', s.startDateTime) " +
            "FROM Schedule s JOIN s.program p " +
            "WHERE FUNCTION('DATE', s.startDateTime) < :date AND p.name = :name " +
            "ORDER BY FUNCTION('DATE', s.startDateTime) DESC")
    List<LocalDate> findAll(String name, LocalDate date, Pageable pageable);

    @Query("SELECT s " +
            "FROM schedule s JOIN s.program p " +
            "WHERE FUNCTION('DATE', s.startDateTime) = :date AND p.name = :name")
    List<Schedule> findAll(String name, LocalDate date);

    @Query("SELECT s " +
            "FROM schedule s " +
            "ORDER BY id DESC")
    List<Schedule> findAllOrderByIdDesc();
}
