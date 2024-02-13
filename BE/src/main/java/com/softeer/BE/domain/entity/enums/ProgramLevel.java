package com.softeer.BE.domain.entity.enums;

import lombok.Getter;

@Getter
public enum ProgramLevel {
    DEFAULT(""),
    LEVEL_1("운전에 대한 자신감을 키우고 일상의 주행을 보다 안전하게 바꾸어주는 드라이빙 기초 교육 프로그램"),
    LEVEL_2("스포츠 드라이빙의 입문 단계로 차량의 퍼포먼스를 느끼고 기본적인 컨트롤 요령을 배우는 프로그램"),
    LEVEL_3("한 단계 높은 수준의 스포츠 드라이빙 테크닉을 배우고 스킬을 업그레이드하는 프로그램"),
    N_ADVANCED("인스트럭터 동승 코칭 및 주행영상분석을 통해 서킷 주행 능력을 강화하는 프로그램"),
    N_MASTERS("모터스포츠로의 입문을 위한 현대 드라이빙 익스피리언스의 최상위 프로그램"),
    DRIFT_LEVEL_1("제네시스의 RWD 차량으로 드리프트를 경험하고 컨트롤 및 긴급 대처 능력을 향상시키는 특별한 프로그램"),
    DRIFT_LEVEL_2("제네시스 RWD 차량으로 다양한 드리프트 테크닉을 학습하는 심화 프로그램"),
    OFF_ROAD("오프로드에서 빛을 발휘하는 현대 SUV의 성능을 스릴 넘치는 코스와 함께 체험하는 프로그램"),
    IONIQ_5N_TAXI("국내 최초 고성능 전기차로 경험하는 스포츠 드라이빙 간점 체험 프로그램"),
    CIRCUIT_TAXI("서킷에서 펼쳐지는 자동차의 한계 성능 체험 프로그램"),
    CIRCUIT_RACE_TAXI("서킷에서 펼쳐지는 아찔한 레이스 배틀 체험 프로그램"),
    HIGH_SPEED_TAXI("오벌 코스를 주행하며 자동차의 한계속도를 경험하는 프로그램"),
    HIGH_SPEED_RACE_TAXI("오벌 코스에서 펼쳐지는 아찔한 레이스 배틀 체험 프로그램"),
    DRIFT_TAXI("눈으로만 봤던 드리프트를 온 몸으로 느낄 수 있는 프로그램"),
    OFF_ROAD_TAXI("극한의 지형과 험로, 장애물을 통과하며 SUV의 한계 퍼포먼스를 체험하는 프로그램"),
    TEST_DRIVE("다양한 HMG 차종들과 함께 극대화된 운전의 즐거움을 경험할 수 있는 프로그램"),
    JUNIOR_DRIVING_EXPERIENCE("자동차 기술의 현재와 미래를 아이들도 쉽게 경험하고 이해할 수 있는 주니어 교육 프로그램"),
    DRIVING_EXPERIENCE_CENTER_TOUR("HMG 드라이빙 익스피리언스 센터의 압도적 규모와 다양한 코스를 여유있게 감상할 수 있는 프로그램"),
    SCENIC_DRIVE("소중한 사람들과 태안 일대를 드라이빙하며 특별한 추억을 만들 수 있는 프로그램");

    private String detail;

    ProgramLevel(String detail) {
        this.detail = detail;
    }
}
