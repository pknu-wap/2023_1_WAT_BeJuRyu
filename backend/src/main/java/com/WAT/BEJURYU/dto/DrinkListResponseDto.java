package com.WAT.BEJURYU.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DrinkListResponseDto {
    private Long id;
    private String name;
    private double dosu;
    private int price;
    private String type;

}
