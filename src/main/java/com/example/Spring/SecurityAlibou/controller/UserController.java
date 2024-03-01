package com.example.Spring.SecurityAlibou.controller;

import com.example.Spring.SecurityAlibou.service.user.ChangePasswordRequest;
import com.example.Spring.SecurityAlibou.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal user) {
        userService.changePassword(request, user);
        return ResponseEntity.ok("Password changed.");
    }
}
