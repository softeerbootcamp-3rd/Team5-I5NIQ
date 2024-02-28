package com.softeer.BE.integration;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ReservationControllerTest extends BaseIntegrationTest{
    @BeforeEach
    void setSession() {
        session = new MockHttpSession();
        session.setAttribute("user", "test");
    }

    @Test
    @DisplayName("[날짜 먼저 선택하기] 1단계 날짜와 상태 리스트")
    void showDateAndStatus() throws Exception {
        // when
        ResultActions actions = mvc.perform(get("/reservation/step1/date")
                        .session(session)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result.size()").value(6))
                .andExpect(jsonPath("$.result[0].value").value("FULL"))
                .andExpect(jsonPath("$.result[1].value").value("POSSIBLE"))
                .andExpect(jsonPath("$.result[4].value").value("IMPOSSIBLE_YET"))
                .andExpect(jsonPath("$.result[5].value").value("IMPOSSIBLE_YET"));
    }

    @Test
    @DisplayName("[날짜 먼저 선택하기] 1단계 해당 날짜의 예약 가능 여부 확인")
    void showAvailabilityAt() throws Exception {
        // given
        String date = "2024-03-03";

        // when
        ResultActions actions = mvc.perform(get("/reservation/step1/date/" + date)
                        .session(session)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result[0].list[0].key").value("HYUNDAI"))
                .andExpect(jsonPath("$.result[0].list[1].key").value("KIA"))
                .andExpect(jsonPath("$.result[0].list[2].key").value("GENESIS"))
                .andExpect(jsonPath("$.result[0].list[0].list[2].reservationStatus").value("IMPOSSIBLE"))
                .andExpect(jsonPath("$.result[0].list[0].list[3].reservationStatus").value("POSSIBLE"))
                .andExpect(jsonPath("$.result[0].list[1].list[1].reservationStatus").value("IMPOSSIBLE"))
                .andExpect(jsonPath("$.result[0].list[2].list[0].reservationStatus").value("POSSIBLE"))
                .andExpect(jsonPath("$.result[0].list[2].list[5].reservationStatus").value("IMPOSSIBLE"));
    }

    @Test
    @DisplayName("[날짜 먼저 선택하기] 2단계 날짜와 프로그램이 정해졌을 때, 차량이름과 상태 확인")
    void showCarAvailability() throws Exception {
        // given
        String date = "2024-03-03";
        long programId = 4L;

        // when
        ResultActions actions = mvc.perform(get("/reservation/step2/date/" + date + "/" + programId)
                        .session(session)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result.programId").value(4))
                .andExpect(jsonPath("$.result.startDate").value("2024-03-03"))
                .andExpect(jsonPath("$.result.carStatusList.size()").value(1));
    }

}
