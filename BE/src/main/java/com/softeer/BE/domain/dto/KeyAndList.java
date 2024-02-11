package com.softeer.BE.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyAndList<T, V> {

    private T key;

    private List<V> list;

}
