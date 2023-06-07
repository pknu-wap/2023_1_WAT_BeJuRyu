package com.WAT.BEJURYU.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Base64;

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
        return textExpression == null || facialExpression == null;
    }

    public byte[] getFacialExpression() {
        return Base64.getDecoder().decode(facialExpression
                .replace('-', '+')
                .replace('_', '/'));
    }
}
