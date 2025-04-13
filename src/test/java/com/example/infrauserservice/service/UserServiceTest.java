package com.example.infrauserservice.service;

import com.example.infrauserservice.infra.UserRepository;
import com.example.infrauserservice.web.exception.model.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    UserRepository userRepository = mock(UserRepository.class);

    @Test
    @DisplayName("사용자 단일 조회 시, 사용자가 존재하지 않으면 Exception을 던진다")
    public void select_user_not_exist_throws_exception() {
        UUID id = UUID.randomUUID();

        given(userRepository.findById(id))
                .willReturn(Optional.empty());

        assertTrue(userService.find(id).isLeft());
        assertThrows(EntityNotFoundException.class, () ->
                userService.find(id).getOrElseThrow(it -> it));
    }

    @Test
    @DisplayName("사용자 정보 변경 시, 사용자가 존재하지 않으면 Exception을 던진다")
    public void update_user_data_not_exist_throws_exception() {
        UUID id = UUID.randomUUID();

        String nickname = "updateNickname";

        given(userRepository.findById(id))
                .willReturn(Optional.empty());

        assertTrue(userService.update(id, nickname).isLeft());
        assertThrows(EntityNotFoundException.class, () ->
                userService.find(id).getOrElseThrow(it -> it));
    }

    @Test
    @DisplayName("사용자 닉네임 중복 확인 시, 중복된 닉네임이라면 true를 반환한다")
    public void nickname_duplicate_check_param_nickname_duplicated_return_true() {
        String duplicated = "duplicated";

        given(userRepository.existsByNickname(duplicated))
                .willReturn(true);

        assertTrue(userService.duplicatedCheck(duplicated));
    }

    @Test
    @DisplayName("사용자 닉네임 중복 확인 시, 유일한 닉네임이라면 false를 반환한다")
    public void nickname_duplicate_check_param_nickname_unique_return_false() {
        String unique = "unique";

        given(userRepository.existsByNickname(unique))
                .willReturn(true);

        assertTrue(userService.duplicatedCheck(unique));
    }
}
