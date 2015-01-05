package com.curry.controller;


import com.curry.model.dto.MealDayDto;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
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

    @RequestMapping(value="/api/home", method = RequestMethod.GET)
    public @ResponseBody
    List <MealDayDto> load() {
        Calendar now = Calendar.getInstance();
        int weekNumber = now.get(Calendar.WEEK_OF_YEAR);
        Week week = new Week (weekNumber);

        List<Day> weekList =  week.getWeekList();

        SimpleDateFormat monthFormatter = new SimpleDateFormat("MMMM");
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE");
        SimpleDateFormat dayNumberFormatter = new SimpleDateFormat("dd");

        List <MealDayDto> mealDayDtoList = new ArrayList<MealDayDto>();
        for (Day day : weekList) {

            String monthName = monthFormatter.format(day.getDate());
            String dayName = dayFormatter.format(day.getDate());
            String dayNumber = dayNumberFormatter.format(day.getDate());

            MealDayDto mealDayDto = new MealDayDto();
            mealDayDto.setMonth(monthName)
                    .setDay(dayName)
                    .setDayNumber(dayNumber)
                    .setSelected(false)
                    .setQuantity(0);

            mealDayDtoList.add(mealDayDto);
        }

        return mealDayDtoList;
    }

}
