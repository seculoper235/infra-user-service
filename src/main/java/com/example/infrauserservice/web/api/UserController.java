package com.example.infrauserservice.web.api;

import com.example.infrauserservice.service.UserDetail;
import com.example.infrauserservice.service.UserInfo;
import com.example.infrauserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserInfo>> find() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetail> find(
            @PathVariable UUID id
    ) {
        UserDetail result = userService.find(id).getOrElseThrow(it -> it);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetail> update(
            @PathVariable UUID id,
            @RequestBody UserUpdateRequest updateRequest
    ) {
        UserDetail result = userService.update(
                id,
                updateRequest.nickname()
        ).getOrElseThrow(it -> it);

        return ResponseEntity.ok(result);
    }
}
