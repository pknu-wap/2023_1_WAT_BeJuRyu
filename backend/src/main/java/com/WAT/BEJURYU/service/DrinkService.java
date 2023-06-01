package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.DrinkResponse;
import com.WAT.BEJURYU.dto.DrinkResponses;
import com.WAT.BEJURYU.dto.ReviewResponse;
import com.WAT.BEJURYU.dto.WriteReviewRequest;
import com.WAT.BEJURYU.entity.Drink;

import com.WAT.BEJURYU.entity.DrinkType;
import com.WAT.BEJURYU.repository.DrinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final ReviewService reviewService;

    public DrinkService(DrinkRepository drinkRepository, final ReviewService reviewService) {
        this.drinkRepository = drinkRepository;
        this.reviewService = reviewService;
    }

    public DrinkResponses getAllDrinks() {
        final List<Drink> drinks = drinkRepository.findAll();

        return DrinkResponses.of(drinks);
    }

    public DrinkResponse getDrinkById(final Long id) {
        final Drink drink = drinkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주류 정보입니다."));

        return DrinkResponse.from(drink);
    }

    public DrinkResponses getDrinksByName(String name) {
        List<Drink> drinks = drinkRepository.findByName(name);

        return DrinkResponses.of(drinks);
    }

    public DrinkResponses getDrinksByType(final DrinkType type) {
        final List<Drink> drinks = drinkRepository.findByType(type);

        return DrinkResponses.of(drinks);
    }

    @Transactional
    public ReviewResponse postReview(final Long drinkId, final WriteReviewRequest request) {
        final Drink drink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주류입니다."));

        return reviewService.postReview(drink, request);
    }

    @Transactional
    public ReviewResponse updateReview(final Long reviewId, final WriteReviewRequest request) {
        return reviewService.updateReview(reviewId, request);
    }
}
