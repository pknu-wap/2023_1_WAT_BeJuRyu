package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.auth.config.AuthParam;
import com.WAT.BEJURYU.auth.dto.UserId;
import com.WAT.BEJURYU.dto.AnalysisCreatedResponse;
import com.WAT.BEJURYU.dto.AnalysisHistory;
import com.WAT.BEJURYU.dto.AnalysisResponse;
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

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/analyze")
public class AnalysisApiController {

    private final AnalysisService analysisService;

    @GetMapping("/{analysis_id}")
    public ResponseEntity<AnalysisResponse> findAnalysis(
            @PathVariable(value = "analysis_id") Long analysisId) {
        final AnalysisResponse analysis = analysisService.findAnalysisById(analysisId);

        return ResponseEntity.ok(analysis);
    }

    @GetMapping
    public ResponseEntity<List<AnalysisHistory>> findHistoriesByUserId(@AuthParam UserId userId) {
        final List<AnalysisHistory> histories = analysisService.findHistoriesByUserId(userId.get());

        return ResponseEntity.ok(histories);
    }

    @PostMapping("/sources")
    public ResponseEntity<AnalysisCreatedResponse> createAnalysisSource(@AuthParam UserId userId,
                                                                        @RequestBody AnalysisSourceRequest sourceRequest) throws IOException {
        final AnalysisResponse analysis = analysisService.postAnalysis(userId.get(), sourceRequest);

        return ResponseEntity.ok(new AnalysisCreatedResponse(analysis.getId()));
    }
}
