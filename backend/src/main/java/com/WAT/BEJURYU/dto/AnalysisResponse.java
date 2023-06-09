package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Analysis;
import com.WAT.BEJURYU.entity.Sentiment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public final class AnalysisResponse {

    private final Long id;
    private final String textExpression;
    private final byte[] facialExpression;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime date;
    private final Sentiment sentiment;
    private final Long drinkId;
    private final String name;
    private final double dosu;
    private final int price;
    private final int volume;
    private final int sweetness;
    private final byte[] drinkImage;
    private final String type;

    public static AnalysisResponse from(final Analysis analysis) {
        return new AnalysisResponse(analysis.getId(),
                analysis.getTextExpression(),
                analysis.getFacialExpression(),
                analysis.getDate(),
                analysis.getSentiment(),
                analysis.getRecommendDrink().getId(),
                analysis.getRecommendDrink().getName(),
                analysis.getRecommendDrink().getDosu(),
                analysis.getRecommendDrink().getPrice(),
                analysis.getRecommendDrink().getVolume(),
                analysis.getRecommendDrink().getSweetness(),
                analysis.getRecommendDrink().getImage(),
                analysis.getRecommendDrink().getType().getLabel());
    }

}
