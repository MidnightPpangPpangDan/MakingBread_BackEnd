package io.mb_backend.makingbackend.pizzaScore.repository;

import io.mb_backend.makingbackend.common.constant.ErrorCode;
import io.mb_backend.makingbackend.common.exception.BusinessException;
import io.mb_backend.makingbackend.pizzaScore.domain.PizzaScore;
import io.mb_backend.makingbackend.userScore.domain.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaScoreRepository extends JpaRepository<PizzaScore, Long> {
    default PizzaScore findByIdOrThrow(Long id){
        return findById(id).orElseThrow(()-> new BusinessException(ErrorCode.PIZZASCORE_INVALILD));
    }
}
