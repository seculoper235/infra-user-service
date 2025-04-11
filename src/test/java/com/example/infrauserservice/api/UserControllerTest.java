package com.example.infrauserservice.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserControllerTest {
    @Test
    @DisplayName("사용자 단일 조회 시, 사용자가 존재하지 않으면 404 에러를 반환한다")
    public void select_user_not_exist_return_404() {
        //
    }

    @Test
    @DisplayName("사용자 단일 조회 시, 유효하지 않은 UUID를 입력하면 400 에러를 반환한다")
    public void select_user_param_invalid_uuid_return_400() {
        //
    }

    @Test
    @DisplayName("사용자 단일 조회 시, 유효한 UUID를 입력하면 사용자 상세 정보를 반환한다")
    public void select_user_param_valid_uuid_return_200_user_detail() {
        //
    }

    @Test
    @DisplayName("사용자 전체 조회 시, 사용자들의 기본 정보를 반환한다")
    public void select_all_users_return_200_user_info_list() {
        //
    }
}
