package com.curry.plugins.date;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * CurryWithAri
 * Created by sadra on 11/2/14.
 */
@Component
public class DatePlugin {
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public Calendar getStartDate(int weekNumber) {      
        Calendar weekDate = Calendar.getInstance();
        weekDate.set(Calendar.WEEK_OF_YEAR, weekNumber);
        weekDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return weekDate;
    }
    public Calendar getEndDate(int weekNumber) {      
        Calendar weekDate = Calendar.getInstance();
        weekDate.set(Calendar.WEEK_OF_YEAR, weekNumber);
        weekDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return weekDate;
    }

    public String toString(Calendar weekDate) {
        return sdf.format(weekDate.getTime());
    }
}
