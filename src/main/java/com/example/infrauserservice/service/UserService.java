package com.example.infrauserservice.service;

import com.example.infrauserservice.infra.UserRepository;
import com.example.infrauserservice.model.User;
import com.example.infrauserservice.web.exception.model.EntityNotFoundException;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserInfo> findAll() {
        return userRepository.findAll()
                .stream().map(User::toInfo).toList();
    }

    public Either<EntityNotFoundException, UserDetail> find(UUID id) {
        return Option.ofOptional(userRepository.findById(id))
                .map(User::toDetail)
                .toEither(() -> new EntityNotFoundException("존재하지 않는 사용자입니다"));
    }

    public Either<EntityNotFoundException, UserDetail> update(
            UUID id,
            String nickname
    ) {
        return Option.ofOptional(userRepository.findById(id))
                .peek(user -> user.updateInfo(nickname))
                .map(User::toDetail)
                .toEither(() -> new EntityNotFoundException("존재하지 않는 사용자입니다"));
    }

    public Boolean duplicatedCheck(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
