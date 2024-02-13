package com.softeer.BE.domain.entity.enums;

import lombok.Getter;

@Getter
public enum ProgramLevel {
    DEFAULT("",""),
    LEVEL_1("운전에 대한 자신감을 키우고 일상의 주행을 보다 안전하게 바꾸어주는 드라이빙 기초 교육 프로그램",
            "스티어링 조작 및 브레이킹 등 자동차의 기본적인 움직임을 경험해보고 운전 실력을 확인할 수 있습니다." +
                    "\n특히 이 프로그램을 통해 서킷의 구조를 익히고 동시에 전에 경험해보지 못한 " +
                    "자동차의 성능을 함께 체험할 수 있습니다."),
    LEVEL_2("스포츠 드라이빙의 입문 단계로 차량의 퍼포먼스를 느끼고 기본적인 컨트롤 요령을 배우는 프로그램",
            ""),
    LEVEL_3("한 단계 높은 수준의 스포츠 드라이빙 테크닉을 배우고 스킬을 업그레이드하는 프로그램",
            ""),
    N_ADVANCED("인스트럭터 동승 코칭 및 주행영상분석을 통해 서킷 주행 능력을 강화하는 프로그램",
            ""),
    N_MASTERS("모터스포츠로의 입문을 위한 현대 드라이빙 익스피리언스의 최상위 프로그램",
            ""),
    DRIFT_LEVEL_1("제네시스의 RWD 차량으로 드리프트를 경험하고 컨트롤 및 긴급 대처 능력을 향상시키는 특별한 프로그램",
            ""),
    DRIFT_LEVEL_2("제네시스 RWD 차량으로 다양한 드리프트 테크닉을 학습하는 심화 프로그램",
            ""),
    OFF_ROAD("오프로드에서 빛을 발휘하는 현대 SUV의 성능을 스릴 넘치는 코스와 함께 체험하는 프로그램",
            "");

    private String detail;
    private String description;

    ProgramLevel(String detail,String description) {
        this.detail = detail;
        this.description=description;
    }
}
