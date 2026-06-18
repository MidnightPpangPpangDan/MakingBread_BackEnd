package io.mb_backend.makingbackend.user.service;

import io.mb_backend.makingbackend.common.dto.KeyPair;
import io.mb_backend.makingbackend.common.dto.JwtProperties;
import io.mb_backend.makingbackend.common.security.JwtTokenProvider;
import io.mb_backend.makingbackend.user.domain.User;
import io.mb_backend.makingbackend.user.dto.LoginResponse;
import io.mb_backend.makingbackend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    public LoginResponse guestLogin() {
        String guestId = UUID.randomUUID().toString().replace("-", "");
        String nickname = "guest_" + guestId.substring(0, 8);

        User user = userRepository.save(User.builder()
                .nickname(nickname)
                .build());

        KeyPair keyPair = jwtTokenProvider.createToken(user.getId());

        return new LoginResponse(
                user.getId(),
                keyPair.accessToken(),
                keyPair.refreshToken(),
                jwtProperties.getValidations().getAccess()
        );
    }
}
