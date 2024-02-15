package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.ClassCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ClassCarRepository extends JpaRepository<ClassCar,Long> {
  @Query(value = "SELECT cc FROM class_car cc JOIN cc.drivingClass dc JOIN dc.program p WHERE " +
          ":today BETWEEN dc.reservationStartTime AND dc.reservationDeadline AND " +
          "p.id=:program_id")
  List<ClassCar> findAllByReservationDate(@Param(value = "today")LocalDateTime today,
                                          @Param(value = "program_id")long programId);
}
