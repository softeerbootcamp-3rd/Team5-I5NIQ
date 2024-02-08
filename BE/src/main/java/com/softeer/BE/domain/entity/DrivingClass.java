package com.softeer.BE.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "driving_class")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrivingClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    private LocalDateTime startDateTime;

    private LocalDateTime reservationStartTime;

    private LocalDateTime reservationDeadline;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "driving_class")
    private List<ClassCar> carList;
}
