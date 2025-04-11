package com.example.infrauserservice.service;

import java.util.UUID;

public record UserInfo(
        UUID id,
        String name
) {
}
