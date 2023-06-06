package com.WAT.BEJURYU.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class AnalysisSourceRequest {

    private String textExpression;
    private String facialExpression;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date = LocalDateTime.now();

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
