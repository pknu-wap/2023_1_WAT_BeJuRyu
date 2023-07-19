package com.WAT.BEJURYU.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<String> handle(final IllegalArgumentException e) {
        logger.info("잘못된 요청: ", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(final HttpMessageNotReadableException e) {
        logger.info("잘못된 요청: ", e);
        return ResponseEntity.badRequest().body("인자가 없습니다.");
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(final Exception e) {
        logger.error("서버 내부 에러: ", e);
        return ResponseEntity.internalServerError().body("알 수 없는 에러가 발생했습니다.");
    }
}
