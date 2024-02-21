package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.domain.entity.DrivingClass;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClassCarRepository extends JpaRepository<ClassCar,Long> {

  @Query("SELECT cc FROM class_car cc JOIN cc.drivingClass dc JOIN dc.program p JOIN cc.car c " +
          "WHERE DAY(:reservation_date) = DAY(dc.startDateTime) AND " +
          "MONTH(:reservation_date) = MONTH(dc.startDateTime) AND " +
          "YEAR(:reservation_date) = YEAR(dc.startDateTime) AND " +
          "p.id=:program_id AND " +
          "c.id=:car_id AND " +
          ":today BETWEEN dc.reservationStartTime AND dc.reservationDeadline")
  List<ClassCar> findAllByStep2(@Param(value = "reservation_date")LocalDate reservationDate,
                                @Param(value = "program_id")long programId,
                                @Param(value = "car_id")long carId,
                                @Param(value = "today")LocalDateTime today);
  @Query(value = "SELECT cc FROM class_car cc JOIN cc.drivingClass dc JOIN dc.program p WHERE " +
          ":today BETWEEN dc.reservationStartTime AND dc.reservationDeadline AND " +
          "p.id=:program_id")
  List<ClassCar> findAllByReservationDate(@Param(value = "today")LocalDateTime today,
                                          @Param(value = "program_id")long programId);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT cc " +
          "FROM class_car cc " +
          "JOIN cc.drivingClass dc " +
          "JOIN dc.carList cl " +
          "WHERE cl.id = :classCarId")
  List<ClassCar> findAllByClassCarId(Long classCarId);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT cc " +
          "FROM class_car cc " +
          "JOIN cc.drivingClass dc " +
          "WHERE EXISTS " +
          "(SELECT cl " +
          "FROM dc.carList cl " +
          "WHERE cl = :car)")
  List<ClassCar> findAllByClassCar(ClassCar car);
}
