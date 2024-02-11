package com.softeer.BE.domain.dto;


import com.softeer.BE.domain.entity.enums.ReservationStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyAndValue<T, V> {

    private T key;

    private V value;

}
