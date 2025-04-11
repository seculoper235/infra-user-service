package com.example.infrauserservice.model;

import com.example.infrauserservice.service.UserDetail;
import com.example.infrauserservice.service.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "\"USER\"")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Email
    @Column(nullable = false)
    private String email;

    public User(String name, String nickname, String email) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public void updateInfo(String nickname) {
        this.nickname = nickname;
    }

    public UserInfo toInfo() {
        return new UserInfo(
                id,
                nickname
        );
    }

    public UserDetail toDetail() {
        return new UserDetail(
                name,
                nickname,
                email
        );
    }
}
