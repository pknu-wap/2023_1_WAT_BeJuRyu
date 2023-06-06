package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.DrinkType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Base64;

@Getter
@RequiredArgsConstructor
public class DrinkWithRatingResponse {
    private final Long id;
    private final String name;
    private final DrinkType type;
    private final double rating;
    private final int reviewCount;
    private final byte[] image;

    public static DrinkWithRatingResponse from(final DrinkResponse drink, final double rating, final int reviewCount) {
        return new DrinkWithRatingResponse(drink.getId(),
                drink.getName(),
                drink.getType(),
                rating,
                reviewCount,
                drink.getImage());
    }

    public static DrinkWithRatingResponse from(final Drink drink, final double rating, final int reviewCount) {
        return new DrinkWithRatingResponse(drink.getId(),
                drink.getName(),
                drink.getType(),
                rating,
                reviewCount,
                drink.getImage());
    }

    public String toEncodedImage() {
        return Base64.getEncoder().encodeToString(image);
    }
}
