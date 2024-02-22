package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.ClassCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Query("SELECT cc FROM class_car cc WHERE cc.drivingClass.reservationStartTime <= :currentDateTime AND cc.drivingClass.reservationDeadline >= :currentDateTime")
    List<ClassCar> findAvailableClassCars(@Param("currentDateTime") LocalDateTime currentDateTime);

  @Query(value = "SELECT * FROM class_car AS cc WHERE cc.driving_class_id = (SELECT c.driving_class_id FROM class_car c WHERE c.id = :classCarId) for update;", nativeQuery = true)
  List<ClassCar> lockClassCarsRelatedByDrivingClass(@Param("classCarId") Long classCarId);
}
