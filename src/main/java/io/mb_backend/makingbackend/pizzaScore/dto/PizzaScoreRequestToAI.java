package io.mb_backend.makingbackend.pizzaScore.dto;


import java.util.List;

//AI서버로 보내기 위한 Request
public record PizzaScoreRequestToAI(
        Long requestId,
        String imgPizza,
        List<String> toppings
) {
}
