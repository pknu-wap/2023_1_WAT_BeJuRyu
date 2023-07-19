package com.WAT.BEJURYU.entity;

public enum DrinkType {
    BEER("맥주"),
    WINE("와인"),
    LIQUEUR("리큐어"),
    WHISKEY("위스키"),
    RICE_WINE("청주"),
    BRANDY("브랜디"),
    FRUIT("과실주"),
    YAKJU("약주"),
    SOJU("소주"),
    MAKGEOLLI("막걸리");

    private final String label;

    DrinkType(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
