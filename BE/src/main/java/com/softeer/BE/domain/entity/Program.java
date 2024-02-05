package com.softeer.BE.domain.entity;

import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@Table(name = "program")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProgramName name;

    @Enumerated(EnumType.STRING)
    private ProgramCategory category;

    @Enumerated(EnumType.STRING)
    private ProgramLevel level;

    private Long cost;

    private String qualification;

    private String estimatedDuration;

    private Long maximumOccupancy;

    private String course;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "program")
    private List<Schedule> schedules;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "program")
    private List<ProgramImage> images;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "program")
    private List<SelectedCar> selectedCars;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "program")
    private List<Comment> comments;
}
