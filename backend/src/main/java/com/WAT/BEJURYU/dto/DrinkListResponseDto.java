package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Review;
import java.util.List;
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
    private int sweetness;
    private int price;
    private int volume;
    private String type;

}
