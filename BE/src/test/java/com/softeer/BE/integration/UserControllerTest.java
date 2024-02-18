package com.softeer.BE.integration;

import com.softeer.BE.domain.dto.UsersRequest;
import com.softeer.BE.integration.BaseIntegrationTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("dev")
public class UserControllerTest extends BaseIntegrationTest {

    private final String USER_ID = "binaryrain97";

    @Test
    @DisplayName("ID 중복 검색(회원가입 전)")
    void checkDuplicateBeforeSignIn() throws Exception {
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("user-id", USER_ID);

        // when
        ResultActions actions = mvc.perform(get("/user/id/validation")
                .params(params)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.userIdValidation").value("UNIQUE"));
    }

    @Test
    @DisplayName("회원가입 진행")
    void join() throws Exception {
        // given
        String content = objectMapper.writeValueAsString(new UsersRequest.JoinForm("이진우", USER_ID, "1234"));

        // when
        ResultActions actions = mvc.perform(post("/user/join")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

        // then
        actions.andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("ID 중복 검색(회원가입 후)")
    void checkDuplicateAfterSignIn() throws Exception {
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("user-id", USER_ID);

        // when
        ResultActions actions = mvc.perform(get("/user/id/validation")
                .params(params)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result.userIdValidation").value("DUPLICATE")).andDo(print());
    }
}
