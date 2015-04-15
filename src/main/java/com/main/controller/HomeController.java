package com.main.controller;


import com.main.model.dto.MealDayDto;
import com.main.plugins.date.helpers.MealWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MealWeek mealWeek;

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public @ResponseBody
    List <MealDayDto> load() {
        return mealWeek.getMealDayDtos();
    }

}
