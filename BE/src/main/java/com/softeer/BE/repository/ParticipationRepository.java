package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findByUserId(String userId);

    @Query("SELECT p FROM participation p WHERE p.user.id = :userId AND p.classCar.drivingClass.startDateTime > CURRENT_TIMESTAMP")
    List<Participation> findUpcomingParticipationByUserId(@Param("userId") String userId);

    @Query("SELECT p FROM participation p WHERE p.user.id = :userId AND p.completion = true")
    List<Participation> findPastParticipationByUserId(@Param("userId") String userId);

    @Query("SELECT p FROM participation p WHERE p.user.id = :userId ORDER BY p.classCar.drivingClass.startDateTime ASC")
    List<Participation> findAllByUserIdOrderByStartDateTime(String userId);
}
