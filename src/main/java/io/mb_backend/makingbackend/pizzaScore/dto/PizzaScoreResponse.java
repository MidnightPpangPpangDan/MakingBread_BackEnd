package io.mb_backend.makingbackend.pizzaScore.dto;

import io.mb_backend.makingbackend.pizzaScore.domain.PizzaScore;

public record PizzaScoreResponse(
        String status,
        String createdAt
) {

    public static PizzaScoreResponse from(PizzaScore pizzaScore) {
        return new PizzaScoreResponse(
                pizzaScore.getStatus().name(),
                pizzaScore.getCreatedAt().toString()
        );
    }
}
