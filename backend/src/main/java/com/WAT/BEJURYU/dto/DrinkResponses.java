package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Drink;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public final class DrinkResponses {
    private final List<DrinkResponse> drinks;
}
