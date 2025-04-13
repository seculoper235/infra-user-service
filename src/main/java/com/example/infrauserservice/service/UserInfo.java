package com.example.infrauserservice.service;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

@JsonSerialize
public record UserInfo(
        UUID id,
        String name
) {
}
