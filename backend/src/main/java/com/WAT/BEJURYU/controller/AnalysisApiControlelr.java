package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.dto.AnalysisResponse;
import com.WAT.BEJURYU.dto.AnalysisResponses;
import com.WAT.BEJURYU.dto.AnalysisSourceRequest;
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
public class AnalysisApiControlelr {

    private final AnalysisService analysisService;

    @GetMapping("/{analysis_id}")
    public ResponseEntity<AnalysisResponse> findAnalysis(
        @PathVariable(value = "analysis_id") Long analysisId) {
        final AnalysisResponse analysis = analysisService.getAnalysis(analysisId);

        return ResponseEntity.ok(analysis);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<AnalysisResponses> findAnalyzeByUserId(
        @PathVariable(value = "user_id") Long userId) {
        final AnalysisResponses analyze = analysisService.getAnalyze(userId);

        return ResponseEntity.ok(analyze);
    }

    @PostMapping("/source")
    public ResponseEntity<AnalysisResponse> createAnalysisSource(
        @RequestBody AnalysisSourceRequest sourceRequest) {
        final AnalysisResponse analysis = analysisService.postAnalysis(sourceRequest);
        
        return ResponseEntity.ok(analysis);
    }

}
