package com.softeer.BE.domain.entity.enums;

import lombok.Getter;

@Getter
public enum ProgramName {
    DRIVING_EXPERIENCE("트랙 주행 초심자부터 레이싱 입문자까지 각자 실력에 맞는 드라이빙 스킬을 배울 수 있는 체계적인 주행 교육 프로그램입니다."),
    DRIVING_PLEASURE("누구나 쉽게 자동차와 드라이빙 문화를 체험할 수 있는 프로그램을 통해 즐거운 추억을 남겨보세요.");

    private String detail;

    ProgramName(String detail) {
        this.detail = detail;
    }
}
