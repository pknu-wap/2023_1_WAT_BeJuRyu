package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.DrinkListResponseDto;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.DrinkType;
import com.WAT.BEJURYU.repository.DrinkRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {
    private static DrinkRepository drinkRepository;

    public DrinkService (DrinkRepository drinkRepository){
        this.drinkRepository = drinkRepository;
    }

    public static List<DrinkListResponseDto> getAllDrinks(){
        List<Drink> drinks = drinkRepository.findAll();
        return makeDrinksDto(drinks);
    }

    public static List<DrinkListResponseDto> getDrinksByName(String name){
        List<Drink> drinks = drinkRepository.findByName(name);
        return makeDrinksDto(drinks);
    }
    private static List<DrinkListResponseDto> makeDrinksDto(List<Drink> drinks){
        List<DrinkListResponseDto> drinksDto = new ArrayList<>();
        drinks.stream()
            .forEach(drink -> drinksDto.add(makeDrinkDto(drink)));
        return drinksDto;
    }

    private static DrinkListResponseDto makeDrinkDto(Drink drink){
        DrinkListResponseDto drinkDto = DrinkListResponseDto.builder()
            .id(drink.getId())
            .name(drink.getName())
            .dosu(drink.getDosu())
            .price(drink.getPrice())
            .type(drink.getType().toString())
            .build();
        return drinkDto;
    }
}
