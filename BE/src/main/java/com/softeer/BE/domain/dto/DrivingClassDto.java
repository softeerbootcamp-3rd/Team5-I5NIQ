package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.DrivingClass;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrivingClassDto {

    private Long id;


    private LocalDateTime startDateTime;

    private LocalDateTime reservationStartTime;

    private LocalDateTime reservationDeadline;

    public DrivingClass toEntity() {
        return DrivingClass.builder()
                .id(this.id)
                .startDateTime(this.startDateTime)
                .reservationStartTime(this.reservationStartTime)
                .reservationDeadline(this.reservationDeadline)
                .build();
    }

    public static DrivingClassDto toDto(DrivingClass drivingClass) {
        return DrivingClassDto.builder()
                .id(drivingClass.getId())
                .startDateTime(drivingClass.getStartDateTime())
                .reservationStartTime(drivingClass.getReservationStartTime())
                .reservationDeadline(drivingClass.getReservationDeadline())
                .build();
    }
}
