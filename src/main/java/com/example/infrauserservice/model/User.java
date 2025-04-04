package com.example.infrauserservice.model;

import com.example.infrauserservice.common.UUIDConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "\"USER\"")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @Convert(converter = UUIDConverter.class)
    private UUID id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Email
    @Column(nullable = false, updatable = false)
    private String email;
}
