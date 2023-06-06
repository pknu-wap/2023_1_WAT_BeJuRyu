package com.WAT.BEJURYU.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@Getter
@Setter
public class AnalysisSourceRequest {

    private String textExpression;
    private String facialExpression;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+9")
    private LocalDateTime date;

    public AnalysisSourceRequest( final String textExpression,
        final String facialExpression, final LocalDateTime date) {
        this.textExpression = textExpression;
        this.facialExpression = facialExpression;
        this.date = date;
    }

    public boolean isNotExistExpressions() {
        return textExpression == null && facialExpression == null;
    }

    public boolean isTextExist() {
        return textExpression != null;
    }

    public boolean isImageExist() {
        return facialExpression != null;
    }
}
