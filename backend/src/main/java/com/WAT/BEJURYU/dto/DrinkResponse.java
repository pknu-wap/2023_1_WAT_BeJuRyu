package com.WAT.BEJURYU.dto;

import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.DrinkType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DrinkResponse {
    private final Long id;
    private final String name;
    private final double dosu;
    private final int sweetness;
    private final int price;
    private final int volume;
    private final byte[] image;
    private final DrinkType type;

    public static DrinkResponse from(final Drink drink) {
        return new DrinkResponse(drink.getId(),
                drink.getName(),
                drink.getDosu(),
                drink.getSweetness(),
                drink.getPrice(),
                drink.getVolume(),
                drink.getImage(),
                drink.getType());
    }
}
