package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Drink;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public final class DrinkResponses {
    private final List<DrinkResponse> drinks;

    public static DrinkResponses of(final List<Drink> drinks) {
        final List<DrinkResponse> result = drinks.stream()
                .map(DrinkResponse::from)
                .toList();

        return new DrinkResponses(result);
    }
}
