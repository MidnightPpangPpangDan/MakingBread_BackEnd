package io.mb_backend.makingbackend.common.dto;

public record KeyPair(
        String accessToken,
        String refreshToken
) {
}
