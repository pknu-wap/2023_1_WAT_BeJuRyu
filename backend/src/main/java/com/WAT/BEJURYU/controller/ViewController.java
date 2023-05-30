package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.dto.DrinkResponse;
import com.WAT.BEJURYU.service.DrinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public final class ViewController {

    private final DrinkService drinkService;

    public ViewController(final DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("/")
    public String allDrinks(final Model model) {
        final List<DrinkResponse> drinks = drinkService.getAllDrinks().getDrinks();
        model.addAttribute("drinks", drinks);

        return "drinks";
    }
}
