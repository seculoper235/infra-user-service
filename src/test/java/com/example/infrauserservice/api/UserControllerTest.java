package com.example.infrauserservice.api;

import com.example.infrauserservice.service.UserDetail;
import com.example.infrauserservice.service.UserInfo;
import com.example.infrauserservice.service.UserService;
import com.example.infrauserservice.web.api.UserController;
import com.example.infrauserservice.web.api.UserUpdateRequest;
import com.example.infrauserservice.web.exception.model.EntityNotFoundException;
import com.example.infrauserservice.web.exception.model.ExceptionStatus;
import com.example.infrauserservice.web.security.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Either;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@Import(SecurityConfig.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    UserService userService;

    @Test
    @DisplayName("사용자 단일 조회 시, 사용자가 존재하지 않으면 404 에러를 반환한다")
    public void select_user_not_exist_return_404() throws Exception {
        UUID notExist = UUID.randomUUID();

        given(userService.find(any()))
                .willReturn(Either.left(new EntityNotFoundException("존재하지 않는 사용자입니다")));

        mockMvc.perform(get("/api/user/" + notExist)
//                        .queryParam("id", notExist.toString())
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ExceptionStatus.US001.name()))
        ;
    }

    @Test
    @DisplayName("사용자 단일 조회 시, 유효하지 않은 UUID를 입력하면 400 에러를 반환한다")
    public void select_user_param_invalid_uuid_return_400() throws Exception {
        String notValid = "NotValid";

        given(userService.find(any()))
                .willReturn(Either.left(new EntityNotFoundException("존재하지 않는 사용자입니다")));

        mockMvc.perform(get("/api/user/" + notValid)
//                        .queryParam("id", notExist.toString())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ExceptionStatus.US002.name()))
        ;
    }

    @Test
    @DisplayName("사용자 단일 조회 시, 유효한 UUID를 입력하면 사용자 상세 정보를 반환한다")
    public void select_user_param_valid_uuid_return_200_user_detail() throws Exception {
        UUID valid = UUID.randomUUID();

        UserDetail findResult = new UserDetail(
                "devteller",
                "devteller",
                "devteller123@gmail.com"
        );

        given(userService.find(any()))
                .willReturn(Either.right(findResult));

        mockMvc.perform(get("/api/user/" + valid)
//                        .queryParam("id", valid.toString())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(findResult.email()))
        ;
    }

    @Test
    @DisplayName("사용자 전체 조회 시, 사용자들의 기본 정보를 반환한다")
    public void select_all_users_return_200_user_info_list() throws Exception {
        List<UserInfo> findResult = List.of(
                new UserInfo(
                        UUID.randomUUID(),
                        "devteller"
                )
        );

        given(userService.findAll())
                .willReturn(findResult);

        mockMvc.perform(get("/api/user"))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    @DisplayName("사용자 정보 변경 시, 유효한 UUID를 입력하면 변경된 사용자 상세 정보를 반환한다")
    public void update_user_data_param_valid_uuid_return_200_updated_user_detail() throws Exception {
        UUID id = UUID.randomUUID();

        UserUpdateRequest request = new UserUpdateRequest(
                "updateNickname"
        );

        UserDetail updateResult = new UserDetail(
                "devteller",
                request.nickname(),
                "devteller123@gmail.com"
        );

        given(userService.update(any(), any()))
                .willReturn(Either.right(updateResult));

        mockMvc.perform(put("/api/user/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname").value(updateResult.nickname()))
        ;
    }
}
