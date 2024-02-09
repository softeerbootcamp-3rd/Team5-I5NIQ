package com.softeer.BE.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrivingClassResponse {

    private String level;
    private List<String> categoryList;

}
