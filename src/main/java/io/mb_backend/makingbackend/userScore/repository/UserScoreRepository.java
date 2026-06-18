package io.mb_backend.makingbackend.userScore.repository;

import io.mb_backend.makingbackend.userScore.domain.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Long> {

}
