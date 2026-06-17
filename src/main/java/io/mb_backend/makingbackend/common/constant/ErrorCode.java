package io.mb_backend.makingbackend.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //REFRESH TOKEN
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 Access Token입니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 Access Token입니다."),
    UNVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED,"유효하지 않은 Refresh Token입니다."),

    //User
    INVALID_LOGIN_INFORMATION(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다."),
    UNVALID_EMAIL_ADDRESS(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED,"인증이 필요합니다."),
    PASSWORD_BAD_REQUEST(HttpStatus.BAD_REQUEST,"비밀번호는 8~64자여야 합니다")
    ;

    private final HttpStatus status;
    private final String description;
}
