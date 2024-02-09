package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDto {

    private Long id;

    private ProgramName name;

    private ProgramCategory category;

    private ProgramLevel level;

    private Long cost;

    private String qualification;

    private String estimatedDuration;

    private Long maximumOccupancy;

    public Program toEntity() {
        return Program.builder()
                .id(id)
                .name(name)
                .category(category)
                .level(level)
                .cost(cost)
                .qualification(qualification)
                .estimatedDuration(estimatedDuration)
                .maximumOccupancy(maximumOccupancy)
                .build();
    }

    public static ProgramDto toDto(Program program) {
        return ProgramDto.builder()
                .id(program.getId())
                .name(program.getName())
                .category(program.getCategory())
                .level(program.getLevel())
                .cost(program.getCost())
                .qualification(program.getQualification())
                .estimatedDuration(program.getEstimatedDuration())
                .maximumOccupancy(program.getMaximumOccupancy())
                .build();
    }
}
