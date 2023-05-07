package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.DrinkListResponseDto;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.repository.DrinkRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {
    private DrinkRepository drinkRepository;

    public DrinkService (DrinkRepository drinkRepository){
        this.drinkRepository = drinkRepository;
    }

    @Transactional
    public List<DrinkListResponseDto> getDrinks(){
        List<Drink> drinks = drinkRepository.findAll();
        List<DrinkListResponseDto> drinksDto = new ArrayList<>();

        drinks.stream()
            .forEach(drink -> drinksDto.add(makeDrinkDto(drink)));

        return drinksDto;
    }
    private DrinkListResponseDto makeDrinkDto(Drink drink){
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
