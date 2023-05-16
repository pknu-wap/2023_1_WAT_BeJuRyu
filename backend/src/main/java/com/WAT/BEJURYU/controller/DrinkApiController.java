package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.dto.DrinkResponses;
import com.WAT.BEJURYU.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/drink")
public class DrinkApiController {

    private final DrinkService drinkService;

    @GetMapping("/all")
    public ResponseEntity<DrinkResponses> findAll() {
        final DrinkResponses drinks = drinkService.getAllDrinks();

        return ResponseEntity.ok(drinks);
    }

    @GetMapping("/{name}")
    public ResponseEntity<DrinkResponses> findByName(@PathVariable String name) {
        final DrinkResponses drinks = drinkService.getDrinksByName(name);

        return ResponseEntity.ok(drinks);
    }
}
