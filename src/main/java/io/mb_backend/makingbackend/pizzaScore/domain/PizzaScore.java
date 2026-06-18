package io.mb_backend.makingbackend.pizzaScore.domain;

import io.mb_backend.makingbackend.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "pizza_scores")
public class PizzaScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Lob
    @Column(nullable = false)
    private String imgPizza;

    @ElementCollection
    @CollectionTable(
            name = "pizza_score_toppings",
            joinColumns = @JoinColumn(name = "pizza_score_id")
    )
    @Column(name = "topping", nullable = false, length = 50)
    private List<String> toppings = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PizzaScoreStatus status;

    @Builder
    public PizzaScore(User user, String imgPizza, List<String> toppings) {
        this.user = user;
        this.imgPizza = imgPizza;
        this.toppings = toppings == null ? new ArrayList<>() : toppings;
        this.status = PizzaScoreStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public void markFailed() {
        this.status = PizzaScoreStatus.FAILED;
    }

}
