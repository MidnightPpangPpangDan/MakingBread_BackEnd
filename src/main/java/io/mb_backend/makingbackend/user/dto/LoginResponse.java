package io.mb_backend.makingbackend.user.dto;

public record LoginResponse(
        Long userId,
        String accessToken,
        String refreshToken,
        long accessExpiresInSeconds
) {
}
