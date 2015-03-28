package com.curry.plugins.date.helpers;

import com.curry.model.dto.MealDayDto;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by sonic on 1/31/15.
 */
@Component()
public class MealWeek {

    public List<MealDayDto> getMealDayDtos() {

        Calendar now = Calendar.getInstance();
        int weekNumber = now.get(Calendar.WEEK_OF_YEAR);
        Week week = new Week (weekNumber);
        List <MealDayDto> mealDayDtoList = new ArrayList<MealDayDto>();

        SimpleDateFormat monthFormatter = new SimpleDateFormat("MMMM");
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE");
        SimpleDateFormat dayNumberFormatter = new SimpleDateFormat("dd");
        List<Day> weekList =  week.getWeekList();
        for (Day day : weekList) {

            String monthName = monthFormatter.format(day.getDate());
            String dayName = dayFormatter.format(day.getDate());
            String dayNumber = dayNumberFormatter.format(day.getDate());

            MealDayDto mealDayDto = new MealDayDto();
            mealDayDto.setMonth(monthName)
                    .setDay(dayName)
                    .setDate(day.getDate())
                    .setDayNumber(dayNumber)
                    .setSelected(false)
                    .setQuantity(0);

            if (isAvailable(dayName))
                mealDayDto.setAvailable(false);
            else
                mealDayDto.setAvailable(true);

            mealDayDtoList.add(mealDayDto);
        }
        return mealDayDtoList;
    }

    private boolean isAvailable(String dayName) {
        return  dayName.equalsIgnoreCase("Sunday") ||
                dayName.equalsIgnoreCase("Monday") ||
                dayName.equalsIgnoreCase("Saturday");
    }

}
