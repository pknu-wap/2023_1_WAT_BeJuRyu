package com.WAT.BEJURYU.entity;

public enum SentimentType {
    HAPPY("기쁨"),
    MEDIAN("중립"),
    SAD("슬픔");

    private String title;

    SentimentType(String title){
        this.title = title;
    }
}
