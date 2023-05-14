package com.WAT.BEJURYU.repository;

import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.Review;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private DrinkRepository drinkRepository;

    private Drink drink;

    @BeforeEach
    public void before() {
        drink = Drink.builder()
                .name("데낄라")
                .build();
        drinkRepository.save(drink);
    }

    @DisplayName("저장 테스트")
    @Test
    void save() {
        //given
        final Review review = Review.builder()
                .comment("존맛")
                .score(5)
                .drink(drink)
                .build();

        //when
        final Review persisted = reviewRepository.save(review);

        //then
        assertAll(
                () -> assertThat(review.getId()).isNotNull(),
                () -> assertThat(review).isEqualTo(persisted));
    }

    @Test
    void DRINK_ID로_조회_테스트() {
        // given
        Review review = Review.builder()
                .comment("맛 없어요")
                .score(1)
                .drink(drink)
                .build();
        reviewRepository.save(review);

        // when
        List<Review> findDrinkReview = reviewRepository.findByDrinkId(drink.getId());

        // then
        assertThat(findDrinkReview.get(0).getDrink().getId()).isEqualTo(drink.getId());
    }
}
