package io.mb_backend.makingbackend.pizzaScore.dto;

import java.util.List;

public record PizzaScoreInfo(
        String imgPizza,
        List<String> toppings
) {
}
