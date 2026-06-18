package io.mb_backend.makingbackend.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    //USER
    USER_CREATED("가입되었습니다."),
    USER_LOGIN("로그인되었습니다."),
    USER_LOGOUT("로그아웃되었습니다."),

    //UserScore
    USERSCORE_SUCCESS("유저점수 받아왔습니다."),

    //PizzaScore
    PIZZASCORE_REQUEST_CREATED("피자 점수 분석요청 등록이 완료되었습니다.")
    ;

    private final String successMessage;
}
