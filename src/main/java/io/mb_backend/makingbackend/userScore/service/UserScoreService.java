package io.mb_backend.makingbackend.userScore.service;

import io.mb_backend.makingbackend.userScore.dto.UserScoreResponse;
import io.mb_backend.makingbackend.userScore.repository.UserScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserScoreService {
//    //받은 값의 랭킹을 환산 받는다
//    public List<UserScoreResponse> getLeaderBoard(){
//        return UserScoreRepository.findAll().stream()
//                .map(UserScoreResponse::from)
//                .collect(Collectors.toList());
//    }
}
