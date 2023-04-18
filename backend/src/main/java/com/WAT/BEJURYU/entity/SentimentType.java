package com.WAT.BEJURYU.entity;

public enum SentimentType {
    HAPPY_1("기쁨1"),
    HAPPY_2("기쁨2"),
    HAPPY_3("기쁨3"),
    MEDIAN("중립"),
    SAD_1("슬픔1"),
    SAD_2("슬픔2"),
    SAD_3("슬픔3");

    private String title;

    SentimentType(String title){
        this.title = title;
    }
}
