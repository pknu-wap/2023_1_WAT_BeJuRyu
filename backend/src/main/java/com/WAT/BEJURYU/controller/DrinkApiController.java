package com.WAT.BEJURYU.controller;

import com.WAT.BEJURYU.dto.DrinkListResponseDto;
import com.WAT.BEJURYU.service.DrinkService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/drink")
public class DrinkApiController {

    @GetMapping("/all")
    @ResponseBody
    public List<DrinkListResponseDto> findAll(){
        return DrinkService.getAllDrinks();
    }


    @GetMapping("/{name}")
    @ResponseBody
    public List<DrinkListResponseDto> findByName(@PathVariable("name")String name){
        return DrinkService.getDrinksByName(name);
    }

}
