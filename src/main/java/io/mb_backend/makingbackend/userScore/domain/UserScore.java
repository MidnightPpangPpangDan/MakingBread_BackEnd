package io.mb_backend.makingbackend.userScore.domain;

import io.mb_backend.makingbackend.user.domain.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Getter
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "user_score")
public class UserScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    private String content;

    private int score;

    @Column(nullable = false, length = 30)
    private Status status;
}
