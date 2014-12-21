package com.curry.controller;


import com.curry.model.dto.DayDto;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class HomeController {


//    @RequestMapping(value="/")
//    public String index () {
//        return "main.html";
//    }


    @RequestMapping(value="/home")
    public ModelAndView load(ModelAndView mav) {
        Calendar now = Calendar.getInstance();
        int weekNumber = now.get(Calendar.WEEK_OF_YEAR);
        Week week = new Week (weekNumber);

        List<Day> weekList =  week.getWeekList();

        SimpleDateFormat monthFormatter = new SimpleDateFormat("MMMM");
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE");
        SimpleDateFormat dayNumberFormatter = new SimpleDateFormat("dd");

        List <DayDto> dayDtos = new ArrayList<DayDto>();
        for (Day day : weekList) {

            String monthName = monthFormatter.format(day.getDate());
            String dayName = dayFormatter.format(day.getDate());
            String dayNumber = dayNumberFormatter.format(day.getDate());

            DayDto dayDto = new DayDto();
            dayDto.setMonth(monthName);
            dayDto.setDay(dayName);
            dayDto.setDayNumber(dayNumber);

            dayDtos.add(dayDto);

            System.out.println(monthName + "::::::::::::::::::" + dayName);
        }
        mav.addObject("weekList", dayDtos);

        mav.setViewName("home");
        return mav;
    }

}
