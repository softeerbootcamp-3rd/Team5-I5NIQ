package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.ClassCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ClassCarRepository extends JpaRepository<ClassCar, Long> {
    @Query("SELECT cc FROM class_car cc WHERE cc.drivingClass.reservationStartTime <= :currentDateTime AND cc.drivingClass.reservationDeadline >= :currentDateTime")
    List<ClassCar> findAvailableClassCars(@Param("currentDateTime") LocalDateTime currentDateTime);
}
