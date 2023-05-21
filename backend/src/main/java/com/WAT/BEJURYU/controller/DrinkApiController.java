package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.dto.DrinkResponses;
import com.WAT.BEJURYU.dto.ReviewResponse;
import com.WAT.BEJURYU.dto.ReviewResponses;
import com.WAT.BEJURYU.dto.WriteReviewRequest;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.service.DrinkService;
import com.WAT.BEJURYU.service.ReviewService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/drinks")
public class DrinkApiController {

    private final DrinkService drinkService;
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<DrinkResponses> findAll() {
        final DrinkResponses drinks = drinkService.getAllDrinks();

        return ResponseEntity.ok(drinks);
    }

    @GetMapping("/{name}")
    public ResponseEntity<DrinkResponses> findByName(@PathVariable String name) {
        final DrinkResponses drinks = drinkService.getDrinksByName(name);

        return ResponseEntity.ok(drinks);
    }

    @GetMapping("/{drink_id}/reviews")
    public ResponseEntity<ReviewResponses> findReviewsById(@PathVariable Long drink_id) {
        final ReviewResponses reviews = reviewService.getReviews(drink_id);

        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/{drink_id}/review")
    public ResponseEntity<ReviewResponse> createReview(@PathVariable Long drink_id,
        @RequestBody WriteReviewRequest review) {
        Optional<Drink> drink = drinkService.getDrinkById(drink_id);
        final ReviewResponse postedReview = reviewService.postReview(drink.get(), review);

        return ResponseEntity.ok(postedReview);
    }
}
