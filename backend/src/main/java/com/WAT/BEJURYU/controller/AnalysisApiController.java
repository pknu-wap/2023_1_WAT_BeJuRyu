package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.auth.config.AuthParam;
import com.WAT.BEJURYU.auth.dto.UserId;
import com.WAT.BEJURYU.dto.AnalysisResponse;
import com.WAT.BEJURYU.dto.AnalysisResponses;
import com.WAT.BEJURYU.dto.AnalysisSourceRequest;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/analyze")
public class AnalysisApiController {

    private final AnalysisService analysisService;

    @GetMapping("/{analysis_id}")
    public ResponseEntity<AnalysisResponse> findAnalysis(
        @PathVariable(value = "analysis_id") Long analysisId) {
        final AnalysisResponse analysis = analysisService.getAnalysis(analysisId);

        return ResponseEntity.ok(analysis);
    }

    @GetMapping
    public ResponseEntity<AnalysisResponses> findAnalyzeByUserId(@AuthParam UserId userId) {
        final AnalysisResponses analyze = analysisService.getAnalyze(userId.get());

        return ResponseEntity.ok(analyze);
    }

    @PostMapping("/sources")
    public ResponseEntity<AnalysisResponse> createAnalysisSource(@AuthParam UserId userId,
        @RequestBody AnalysisSourceRequest sourceRequest) {
        validate(sourceRequest);

        final AnalysisResponse analysis = analysisService.postAnalysis(userId.get(), sourceRequest);
        return ResponseEntity.ok(analysis);
    }

    private void validate(AnalysisSourceRequest sourceRequest) {
        if (sourceRequest.isNotExistExpressions()) {
            throw new IllegalArgumentException("텍스트 또는 사진 중 하나는 필요합니다.");
        }
    }

}
