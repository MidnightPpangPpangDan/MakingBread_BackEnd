package io.mb_backend.makingbackend.pizzaScore.service;

import io.mb_backend.makingbackend.common.constant.ErrorCode;
import io.mb_backend.makingbackend.common.exception.BusinessException;
import io.mb_backend.makingbackend.pizzaScore.client.PizzaScoreAiClient;
import io.mb_backend.makingbackend.pizzaScore.domain.PizzaScore;
import io.mb_backend.makingbackend.pizzaScore.dto.PizzaScoreInfo;
import io.mb_backend.makingbackend.pizzaScore.dto.PizzaScoreRequestToAI;
import io.mb_backend.makingbackend.pizzaScore.dto.PizzaScoreResponse;
import io.mb_backend.makingbackend.pizzaScore.repository.PizzaScoreRepository;
import io.mb_backend.makingbackend.user.domain.User;
import io.mb_backend.makingbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaScoreService {
    private final PizzaScoreRepository pizzaScoreRepository;
    private final UserRepository userRepository;
    private final PizzaScoreAiClient pizzaScoreAiClient;

    public PizzaScoreResponse createPizzaScoreRequest(Long userId, PizzaScoreInfo info) {
        validate(info);

        User user = userRepository.findByIdOrThrow(userId);
        PizzaScore pizzaScore = pizzaScoreRepository.save(PizzaScore.builder()
                .user(user)
                .imgPizza(info.imgPizza())
                .toppings(info.toppings())
                .build());

        sendToAiServer(pizzaScore);

        return PizzaScoreResponse.from(pizzaScore);
    }

    private void sendToAiServer(PizzaScore pizzaScore) {
        try {
            pizzaScoreAiClient.send(new PizzaScoreRequestToAI(
                    pizzaScore.getId(),
                    pizzaScore.getImgPizza(),
                    pizzaScore.getToppings()
            ));
        } catch (RuntimeException exception) {
            pizzaScore.markFailed();
            pizzaScoreRepository.save(pizzaScore);
            throw new BusinessException(ErrorCode.PIZZASCORE_AI_DELIVERY_FAILED);
        }
    }

    private void validate(PizzaScoreInfo info) {
        if (info == null || isBlank(info.imgPizza()) || isInvalidToppings(info.toppings())) {
            throw new BusinessException(ErrorCode.PIZZASCORE_BAD_REQUEST);
        }
    }

    private boolean isInvalidToppings(List<String> toppings) {
        return toppings == null || toppings.isEmpty() || toppings.stream().anyMatch(this::isBlank);
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
