package com.softeer.BE.integration;

import com.softeer.BE.domain.entity.enums.ProgramLevel;
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
public class ProgramControllerTest extends BaseIntegrationTest {
    @BeforeEach
    void setSession() {
        session = new MockHttpSession();
        session.setAttribute("user", "test");
    }

    @Test
    @DisplayName("[프로그램] 정보")
    void programInformation() throws Exception {
        // given
        Long programId = 1L;

        // when
        ResultActions actions = mvc.perform(get("/program/information")
                        .param("program-id", "1")
                .session(session)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result.programId").value(programId))
                .andExpect(jsonPath("$.result.levelName").value("LEVEL_1"));
    }

    @Test
    @DisplayName("[프로그램] 디테일")
    void programDetail() throws Exception {
        // given
        Long programId = 2L;

        // when
        ResultActions actions = mvc.perform(get("/program/detail")
                .param("program-id", "2")
                .session(session)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result.programId").value(programId))
                .andExpect(jsonPath("$.result.programCars.size()").value(1));
    }

    @Test
    @DisplayName("[프로그램] 장소")
    void programLocation() throws Exception {
        // given
        Long programId = 3L;

        // when
        ResultActions actions = mvc.perform(get("/program/location")
                .param("program-id", "3")
                .session(session)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result.circuitSize").value(4));
    }

    @Test
    @DisplayName("[프로그램] 댓글")
    void programComments() throws Exception {
        // given
        Long programId = 1L;

        // when
        ResultActions actions = mvc.perform(get("/program/comments")
                .param("program-id", "1")
                .session(session)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result.commentSize").value(2));
    }
}
