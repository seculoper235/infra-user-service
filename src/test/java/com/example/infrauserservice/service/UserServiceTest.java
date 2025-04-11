package com.example.infrauserservice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    @Test
    @DisplayName("사용자 단일 조회 시, 사용자가 존재하지 않으면 Exception을 던진다")
    public void select_user_not_exist_throws_exception() {
        //
    }

    @Test
    @DisplayName("사용자 닉네임 중복 확인 시, 중복된 닉네임이라면 true를 반환한다")
    public void nickname_duplicate_check_param_nickname_duplicated_return_true() {
        //
    }

    @Test
    @DisplayName("사용자 닉네임 중복 확인 시, 유일한 닉네임이라면 false를 반환한다")
    public void nickname_duplicate_check_param_nickname_unique_return_false() {
        //
    }
}
