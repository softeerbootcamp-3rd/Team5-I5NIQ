package com.softeer.BE.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "class_car")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long maximumOccupancy;

    @ManyToOne
    @JoinColumn(name = "driving_class_id")
    private DrivingClass drivingClass;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "classCar")
    private List<Participation> participationList;

    private Long cost;

    public boolean canReservation(long amount, long totalAmountOfClassCar, long totalAmountOfClassCarList){
        long leftAmountOfProgram = drivingClass.getProgram().getMaximumOccupancy() - totalAmountOfClassCarList;
        long leftAmountOfClassCar = maximumOccupancy-totalAmountOfClassCar;
        return (amount <= leftAmountOfProgram) && (amount <= leftAmountOfClassCar);
    }
}
