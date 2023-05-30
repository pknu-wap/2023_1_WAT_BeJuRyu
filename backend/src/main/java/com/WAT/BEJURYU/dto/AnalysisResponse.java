package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Analysis;
import java.time.LocalDateTime;

import com.WAT.BEJURYU.entity.Sentiment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AnalysisResponse {

    private final Long id;
    private final String textExpression;
    private final byte[] facialExpression;
    private final LocalDateTime date;
    private final Sentiment sentiment;
    private final Long drinkId;

    public static AnalysisResponse from(final Analysis analysis) {
        return new AnalysisResponse(analysis.getId(),
            analysis.getTextExpression(),
            analysis.getFacialExpression(),
            analysis.getDate(),
            analysis.getSentiment(),
            analysis.getRecommendDrink().getId());
    }

}
