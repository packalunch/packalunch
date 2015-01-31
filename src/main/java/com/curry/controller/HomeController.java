package com.curry.controller;


import com.curry.model.dto.MealDayDto;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
import com.curry.plugins.date.helpers.MealWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MealWeek mealWeek;

    @RequestMapping(value="/api/home", method = RequestMethod.GET)
    public @ResponseBody
    List <MealDayDto> load() {
        return mealWeek.getMealDayDtos();
    }



}
