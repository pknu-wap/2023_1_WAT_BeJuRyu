package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.DrinkResponse;
import com.WAT.BEJURYU.dto.DrinkWithRatingResponse;
import com.WAT.BEJURYU.dto.ReviewResponse;
import com.WAT.BEJURYU.dto.WriteReviewRequest;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.DrinkType;
import com.WAT.BEJURYU.repository.DrinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.comparingInt;

@Service
@Transactional(readOnly = true)
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final ReviewService reviewService;

    public DrinkService(DrinkRepository drinkRepository, final ReviewService reviewService) {
        this.drinkRepository = drinkRepository;
        this.reviewService = reviewService;
    }

    public List<DrinkResponse> getAllDrinks() {
        final List<Drink> drinks = drinkRepository.findAll();

        return drinks.stream()
                .map(drink -> DrinkResponse.from(drink,
                        reviewService.getAverageScore(drink.getId()),
                        reviewService.getReviewSize(drink.getId())))
                .collect(Collectors.toList());
    }

    public DrinkResponse getDrinkById(final Long id) {
        final Drink drink = drinkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주류 정보입니다."));


        return DrinkResponse.from(drink, reviewService.getAverageScore(id), reviewService.getReviewSize(id));
    }

    public List<DrinkResponse> getDrinksByName(String name) {
        final List<Drink> drinks = drinkRepository.findAll().stream()
                .filter(drink -> drink.getName().contains(name))
                .toList();

        return drinks.stream()
                .map(drink -> DrinkResponse.from(drink,
                        reviewService.getAverageScore(drink.getId()),
                        reviewService.getReviewSize(drink.getId())))
                .collect(Collectors.toList());
    }

    public List<DrinkResponse> getDrinksByType(final DrinkType type) {
        final List<Drink> drinks = drinkRepository.findByType(type);

        return drinks.stream()
                .map(drink -> DrinkResponse.from(drink,
                        reviewService.getAverageScore(drink.getId()),
                        reviewService.getReviewSize(drink.getId())))
                .toList();
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

    public List<DrinkWithRatingResponse> findTop10ByRating() {
        final List<DrinkWithRatingResponse> drinks = getDrinkWithRatingResponses();

        return drinks.stream()
                .filter(drink -> drink.getRating() != 0)
                .sorted(comparingDouble(DrinkWithRatingResponse::getRating).reversed())
                .limit(10)
                .toList();
    }

    private List<DrinkWithRatingResponse> getDrinkWithRatingResponses() {
        return drinkRepository.findAll().stream()
                .map(Drink::getName)
                .distinct()
                .map(reviewService::getReviewsByDrinkName)
                .filter(list -> !list.isEmpty())
                .map(DrinkWithRatingResponse::from)
                .toList();
    }

    public List<DrinkWithRatingResponse> findTop10ByReviews() {
        final List<DrinkWithRatingResponse> drinks = getDrinkWithRatingResponses();

        return drinks.stream()
                .filter(drink -> drink.getReviewCount() != 0)
                .sorted(comparingInt(DrinkWithRatingResponse::getReviewCount).reversed())
                .limit(10)
                .toList();
    }

}
