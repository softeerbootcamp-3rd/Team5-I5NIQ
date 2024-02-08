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
public class ScheduleDto {

    private Long id;

    private Program program;

    private LocalDateTime startDateTime;

    private List<Participation> participationList;

    public DrivingClass toEntity() {
        return DrivingClass.builder()
                .id(this.id)
                .program(this.program)
                .startDateTime(this.startDateTime)
                .participationList(this.participationList)
                .build();
    }

    public static ScheduleDto toDto(DrivingClass drivingClass) {
        return ScheduleDto.builder()
                .id(drivingClass.getId())
                .program(drivingClass.getProgram())
                .startDateTime(drivingClass.getStartDateTime())
                .participationList(drivingClass.getParticipationList())
                .build();
    }
}
