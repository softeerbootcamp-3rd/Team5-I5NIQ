package com.softeer.BE.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursorResult<T> {
    private List<T> values;
    private Boolean hasNext;
}
