package com.example.infrauserservice.service;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record UserDetail(
        String name,
        String nickname,
        String email
) {
}
