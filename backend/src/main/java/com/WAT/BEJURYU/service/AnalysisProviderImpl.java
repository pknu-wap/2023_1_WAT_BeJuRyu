package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.AnalysisSourceRequest;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.entity.Sentiment;
import com.google.gson.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;


public class AnalysisProviderImpl implements AnalysisProvider{
    @Override
    public Sentiment analyze(AnalysisSourceRequest analysisSourceRequest) {
        final RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final JsonObject expressionJson = new JsonObject();
        if (analysisSourceRequest.isImageExist()) {
            expressionJson.addProperty("image", Base64.getEncoder().encodeToString(analysisSourceRequest.getFacialExpression()));
        }
        if (analysisSourceRequest.isTextExist()) {
            expressionJson.addProperty("text", analysisSourceRequest.getTextExpression());
        }

        HttpEntity<String> request =
                new HttpEntity<String>(expressionJson.toString(), headers);
        final String result = restTemplate.postForObject("url", request, String.class);

        return null;
    }
}
