package io.mb_backend.makingbackend.user.controller;

import io.mb_backend.makingbackend.common.constant.SuccessCode;
import io.mb_backend.makingbackend.common.dto.ApiResponse;
import io.mb_backend.makingbackend.user.dto.LoginResponse;
import io.mb_backend.makingbackend.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> guestLogin() {
        return ApiResponse.ok(
                authService.guestLogin(),
                SuccessCode.USER_LOGIN.getSuccessMessage()
        );
    }
}
