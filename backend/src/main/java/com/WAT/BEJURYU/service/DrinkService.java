package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.DrinkResponses;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.repository.DrinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DrinkService {
    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public DrinkResponses getAllDrinks() {
        final List<Drink> drinks = drinkRepository.findAll();

        return DrinkResponses.of(drinks);
    }

    public DrinkResponses getDrinksByName(String name) {
        List<Drink> drinks = drinkRepository.findByName(name);

        return DrinkResponses.of(drinks);
    }
}
