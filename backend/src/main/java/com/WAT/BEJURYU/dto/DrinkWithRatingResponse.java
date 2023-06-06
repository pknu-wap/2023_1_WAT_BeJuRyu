package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.DrinkType;
import com.WAT.BEJURYU.entity.Review;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class DrinkWithRatingResponse {
    private final Long id;
    private final String name;
    private final DrinkType type;
    private final double rating;
    private final int reviewCount;
    private final byte[] image;

    public static DrinkWithRatingResponse from(final List<Review> reviews) {
        final Drink drink = reviews.get(0).getDrink();

        return new DrinkWithRatingResponse(drink.getId(),
                drink.getName(),
                drink.getType(),
                calculateRating(reviews),
                reviews.size(),
                drink.getImage());
    }

    private static double calculateRating(final List<Review> reviews) {
        final double sum = reviews.stream()
                .mapToDouble(Review::getScore)
                .sum();

        return Math.round(sum / (double) reviews.size() * 100) / 100.0;
    }
}
