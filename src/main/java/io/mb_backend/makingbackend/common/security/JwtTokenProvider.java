package io.mb_backend.makingbackend.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.mb_backend.makingbackend.common.dto.JwtProperties;
import io.mb_backend.makingbackend.common.dto.KeyPair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

// common/security/JwtTokenProvider.java
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    public KeyPair createToken(Long userId) {
        Date now = new Date();

        String accessToken = createToken(
                userId,
                jwtProperties.getPayload().getSubjectAccessToken(),
                jwtProperties.getValidations().getAccess(),
                now
        );

        String refreshToken = createToken(
                userId,
                jwtProperties.getPayload().getSubjectRefreshToken(),
                jwtProperties.getValidations().getRefresh(),
                now
        );

        return new KeyPair(accessToken, refreshToken);
    }

    private String createToken(Long userId, String subject, int expiresInSeconds, Date now) {
        SecretKey key = Keys.hmacShaKeyFor(jwtProperties.getSecrets().getAppKey().getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .issuer(jwtProperties.getPayload().getIssuer())
                .subject(subject)
                .audience().add(jwtProperties.getPayload().getAudience()).and()
                .claim("userId", userId)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiresInSeconds * 1000L))
                .signWith(key)
                .compact();
    }

    public boolean isValidAccessToken(String token) {
        try {
            return jwtProperties.getPayload().getSubjectAccessToken().equals(parseClaims(token).getSubject());
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Object userId = parseClaims(token).get("userId");

        if (userId instanceof Number number) {
            return number.longValue();
        }

        return Long.parseLong(String.valueOf(userId));
    }

    private Claims parseClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtProperties.getSecrets().getAppKey().getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
