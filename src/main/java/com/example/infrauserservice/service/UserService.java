package com.example.infrauserservice.service;

import com.example.infrauserservice.infra.UserRepository;
import com.example.infrauserservice.model.User;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserInfo> findAll() {
        return userRepository.findAll()
                .stream().map(User::toInfo).toList();
    }

    public Either<Exception, UserDetail> find(String nickname) {
        return Option.ofOptional(userRepository.findByNickname(nickname))
                .map(User::toDetail)
                .toEither(() -> new Exception("존재하지 않는 사용자입니다"));
    }

    public Boolean duplicatedCheck(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
