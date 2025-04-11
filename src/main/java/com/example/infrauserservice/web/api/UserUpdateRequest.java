package com.example.infrauserservice.web.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record UserUpdateRequest(
        String nickname
) {
}
