package com.WAT.BEJURYU.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import com.WAT.BEJURYU.dto.DrinkListResponseDto;
import com.WAT.BEJURYU.entity.Drink;
import com.WAT.BEJURYU.entity.DrinkType;
import com.WAT.BEJURYU.repository.DrinkRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
public class DrinkServiceTest {
    @Mock
    DrinkRepository drinkRepository;

    @InjectMocks
    private DrinkService testService;

    @Test
    void 전체_주류목록_조회_테스트(){
        //given
        Drink drink1 = Drink.builder()
            .name("데낄라")
            .dosu(35)
            .price(35000)
            .type(DrinkType.LIQUEUR)
            .build();

        List<Drink> drinks = new ArrayList<>();
        drinks.add(drink1);
        given(drinkRepository.findAll()).willReturn(drinks);

        //when
        List<DrinkListResponseDto> drinksDto = testService.getAllDrinks();

        //then
        System.out.println(drinksDto);
    }
}
