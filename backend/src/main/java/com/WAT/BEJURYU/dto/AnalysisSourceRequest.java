package com.WAT.BEJURYU.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@Getter
@Setter
public class AnalysisSourceRequest {

    private String textExpression;
    private byte[] facialExpression;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    public AnalysisSourceRequest( final String textExpression,
        final byte[] facialExpression, final LocalDateTime date) {
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
