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
    private final byte[] image;

    public static DrinkWithRatingResponse from(final DrinkResponse drink,final double rating) {
        return new DrinkWithRatingResponse(drink.getId(),
                drink.getName(),
                drink.getType(),
                rating,
                drink.getImage());
    }

    public String toEncodedImage() {return Base64.getEncoder().encodeToString(image);}
}
