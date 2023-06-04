package com.WAT.BEJURYU.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class DrinkRankingResponse {
    private List<DrinkWithRatingResponse> ranking;

    public DrinkRankingResponse(List<DrinkWithRatingResponse> ranking){
        this.ranking= ranking;
    }
}
