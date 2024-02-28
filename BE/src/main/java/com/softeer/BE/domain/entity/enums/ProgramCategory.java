package com.softeer.BE.domain.entity.enums;

import lombok.Getter;

@Getter
public enum ProgramCategory {
    HYUNDAI("현대자동차와 함께 운전하는 즐거움을 경험하고 드라이빙 실력도 업그레이드 해보세요."),
    KIA("고성능 EV와 함께하는 완전히 새로운 드라이빙 경험을 즐겨보세요."),
    GENESIS("다양한 드라이빙 체험을 통해 제네시스의 진정한 성능 그리고 새로운 매력을 느껴보세요."),
    TAXI("전문 인스트럭터와 함께 스포츠 드라이빙을 경험하며 스릴을 만끽할 수 있는 프로그램"),
    HMG("다양한 HMG 차종들과 함께 극대화된 운전의 즐거움을 경험할 수 있는 프로그램");

    private String detail;

    ProgramCategory(String detail) {
        this.detail = detail;
    }
}
