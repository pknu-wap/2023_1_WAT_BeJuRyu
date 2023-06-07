package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.AnalysisSourceRequest;
import com.WAT.BEJURYU.entity.Sentiment;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class AnalysisProviderImpl implements AnalysisProvider {

    private static final String TEXT_API_URL = "https://naveropenapi.apigw.ntruss.com/sentiment-analysis/v1/analyze";
    private static final String FACE_API_URL = "https://naveropenapi.apigw.ntruss.com/vision/v1/face";

    private static final String CLIENT_ID_HEADER = "X-NCP-APIGW-API-KEY-ID";
    private static final String CLIENT_SECRET_HEADER = "X-NCP-APIGW-API-KEY";
    @Value("${security.api.id}")
    private String CLIENT_ID;
    @Value("${security.api.key}")
    private String CLIENT_SECRET;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Sentiment analyze(AnalysisSourceRequest analysisSourceRequest) {
        final AnalysisResult facialResult = getFacialResult(analysisSourceRequest.getFacialExpression());
        final AnalysisResult textResult = getTextResult(analysisSourceRequest.getTextExpression());

        textResult.addFacialResult(facialResult);

        return textResult.getSentiment();
    }

    private AnalysisResult getTextResult(final String text) {
        final HttpHeaders headers = makeHeaders(MediaType.APPLICATION_JSON);

        final JsonObject body = new JsonObject();
        body.addProperty("content", text);

        HttpEntity<String> httpEntity = new HttpEntity<>(body.toString(), headers);
        final String result = restTemplate.postForObject(TEXT_API_URL, httpEntity, String.class);

        final JSONObject jsonObject = new JSONObject(result);
        final JSONObject confidence = jsonObject.getJSONObject("document").getJSONObject("confidence");
        final double positive = confidence.getDouble("positive");
        final double neutral = confidence.getDouble("neutral");

        return AnalysisResult.of(positive, neutral);
    }

    private HttpHeaders makeHeaders(final MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CLIENT_ID_HEADER, CLIENT_ID);
        headers.add(CLIENT_SECRET_HEADER, CLIENT_SECRET);
        headers.setContentType(mediaType);

        return headers;
    }

    private AnalysisResult getFacialResult(final byte[] face) {
        HttpHeaders headers = makeHeaders(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", face);

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        final String result = restTemplate.postForObject(FACE_API_URL, httpEntity, String.class);

        final JSONObject jsonObject = new JSONObject(result);
        final JSONObject faces = jsonObject.getJSONArray("faces").getJSONObject(0);
        final JSONObject emotion = faces.getJSONObject("emotion");
        final String emotionString = emotion.getString("value");
        final double confidence = emotion.getDouble("confidence");

        return AnalysisResult.of(emotionString, confidence);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    private static class AnalysisResult {
        private double positive;

        public static AnalysisResult of(final String emotionString, final double confidence) {
            final AnalysisResult analysisResult = new AnalysisResult();
            if (emotionString.equals("positive")) {
                analysisResult.setPositive(confidence);
            }
            if (emotionString.equals("neutral")) {
                analysisResult.setPositive(confidence / 2);
            }

            return analysisResult;
        }

        public static AnalysisResult of(final double positive, final double neutral) {
            return new AnalysisResult(positive + neutral / 2);
        }

        public void addFacialResult(final AnalysisResult facial) {
            this.positive *= 0.3;
            this.positive += facial.positive * 0.7;
        }

        public Sentiment getSentiment() {
            if (positive < 14) {
                return Sentiment.SAD_3;
            }
            if (positive < 28) {
                return Sentiment.SAD_2;
            }
            if (positive < 42) {
                return Sentiment.SAD_1;
            }
            if (positive < 56) {
                return Sentiment.MEDIAN_0;
            }
            if (positive < 70) {
                return Sentiment.HAPPY_1;
            }
            if (positive < 84) {
                return Sentiment.HAPPY_2;
            }
            return Sentiment.HAPPY_3;
        }
    }
}
