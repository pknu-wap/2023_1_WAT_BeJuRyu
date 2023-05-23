package com.WAT.BEJURYU.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnalysisSourceRequest {

    private Long userId;
    private String textExpression;
    private String facialExpression;
    private LocalDateTime date;

    public AnalysisSourceRequest(final Long userId, final String textExpression,
        final String facialExpression, final LocalDateTime date) {
        this.userId = userId;
        this.textExpression = textExpression;
        this.facialExpression = facialExpression;
        this.date = date;
    }

}
