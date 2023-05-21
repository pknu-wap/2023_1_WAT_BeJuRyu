package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.dto.DrinkResponses;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.repository.DrinkRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Optional<Drink> getDrinkById(long id) {
        Optional<Drink> drink = drinkRepository.findById(id);
        return drink;
    }
}
