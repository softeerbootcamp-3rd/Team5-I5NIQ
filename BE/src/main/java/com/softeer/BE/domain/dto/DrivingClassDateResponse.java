package com.softeer.BE.domain.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrivingClassDateResponse {
    private LocalDate localDate;
    private String status;
}
