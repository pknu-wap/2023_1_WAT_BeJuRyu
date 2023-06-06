package com.WAT.BEJURYU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WriteReviewRequest {

    private long userId;
    private String comment;
    private int score;
    private String date;
}
