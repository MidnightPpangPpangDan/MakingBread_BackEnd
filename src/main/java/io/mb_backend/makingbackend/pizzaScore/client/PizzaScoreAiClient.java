package io.mb_backend.makingbackend.pizzaScore.client;

import io.mb_backend.makingbackend.pizzaScore.dto.PizzaScoreRequestToAI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PizzaScoreAiClient {

    private static final String PIZZA_SCORE_ANALYZE_URI = "/api/v1/pizzascore/";

    private final RestClient restClient;

    public PizzaScoreAiClient(@Value("${baseUrl}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public void send(PizzaScoreRequestToAI request) {
        restClient.post()
                .uri(PIZZA_SCORE_ANALYZE_URI)
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }
}
