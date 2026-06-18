package io.mb_backend.makingbackend.pizzaScore.controller;

import io.mb_backend.makingbackend.common.constant.SuccessCode;
import io.mb_backend.makingbackend.common.dto.ApiResponse;
import io.mb_backend.makingbackend.pizzaScore.dto.PizzaScoreInfo;
import io.mb_backend.makingbackend.pizzaScore.dto.PizzaScoreResponse;
import io.mb_backend.makingbackend.pizzaScore.service.PizzaScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class PizzaScoreController {
    private final PizzaScoreService pizzaScoreService;

    @PostMapping("/api/v1/users/me/pizzascore")
    public ResponseEntity<ApiResponse<PizzaScoreResponse>> createPizzaScoreRequest(
            Principal principal,
            @RequestBody PizzaScoreInfo info
    ) {
        Long userId = Long.parseLong(principal.getName());

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ApiResponse.ok(
                        pizzaScoreService.createPizzaScoreRequest(userId, info),
                        SuccessCode.PIZZASCORE_REQUEST_CREATED.getSuccessMessage()
                ));
    }
}
