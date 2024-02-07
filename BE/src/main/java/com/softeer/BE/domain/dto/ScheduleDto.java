package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.Schedule;
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

    public Schedule toEntity() {
        return Schedule.builder()
                .id(this.id)
                .program(this.program)
                .startDateTime(this.startDateTime)
                .participationList(this.participationList)
                .build();
    }

    public static ScheduleDto toDto(Schedule schedule) {
        return ScheduleDto.builder()
                .id(schedule.getId())
                .program(schedule.getProgram())
                .startDateTime(schedule.getStartDateTime())
                .participationList(schedule.getParticipationList())
                .build();
    }
}
