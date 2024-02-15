package com.softeer.BE.integration;

import com.softeer.BE.integration.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@Transactional   클래스 내부의 각각의 테스트 메소드가 실행될때마다, 데이터베이스를 롤백
@ActiveProfiles("dev")
class NoticeControllerTest extends BaseIntegrationTest {

    @Test
    @DisplayName("공지사항 리스트 파라미터 없이 조회")
    void readNoticeWithoutParams() throws Exception {
        // when
        ResultActions actions = mvc.perform(get("/notice/list")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result.hasNext").value(true))
                .andExpect(jsonPath("$.result.values[0].id").value(30))
                .andExpect(jsonPath("$.result.values.size()").value(10));
    }
    @Test
    @DisplayName("공지사항 리스트 파라미터로 조회")
    void readNoticeWithParams() throws Exception {
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cursorId", "6");
        params.add("pageSize", "10");

        // when
        ResultActions actions = mvc.perform(get("/notice/list")
                .params(params)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result.hasNext").value(false))
                .andExpect(jsonPath("$.result.values[0].id").value(5))
                .andExpect(jsonPath("$.result.values.size()").value(5));
    }

    @Test
    @DisplayName("공지사항 세부내용 조회")
    void readNoticeDetail() throws Exception {
        // given
        Long noticeId = 10L;

        // when
        ResultActions actions = mvc.perform(get("/notice/{noticeId}", noticeId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(10L))
                .andExpect(jsonPath("$.result.content").value("공지사항10 내용입니다."));
    }
}