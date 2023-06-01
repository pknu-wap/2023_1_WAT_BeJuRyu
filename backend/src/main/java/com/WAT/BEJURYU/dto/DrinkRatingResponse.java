package com.WAT.BEJURYU.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DrinkRatingResponse {
    private Long id;
    private double rating;

    public DrinkRatingResponse(Long id,double rating){
        this.id = id;
        this.rating = rating;
    }
}
