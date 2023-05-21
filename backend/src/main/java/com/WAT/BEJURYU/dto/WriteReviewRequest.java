package com.WAT.BEJURYU.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class WriteReviewRequest {

    private final long user_id;
    private final String comment;
    private final int score;
    private final String date = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
}
