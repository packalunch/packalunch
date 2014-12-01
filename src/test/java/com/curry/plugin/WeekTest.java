package com.curry.plugin;

import com.curry.plugins.date.Week;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * CurryWithAri
 * Created by sadra on 11/6/14.
 */
public class WeekTest {

    @Before
    public void setup() {
    }

    @Test
    public void testGetDate(){
        Calendar now = Calendar.getInstance();
        int weekNumber = now.get(Calendar.WEEK_OF_YEAR);

        Week week = new Week(weekNumber);

        Calendar startDate = week.getDate(Calendar.SUNDAY);
        Calendar endDate   = week.getDate(Calendar.SATURDAY);
        System.out.println("Sunday::::::" + startDate.getTime() + "-----" + startDate.get(Calendar.DATE));
        System.out.println("Saturday::::::::" + endDate.getTime() + "----------" + endDate.get(Calendar.DATE));
     //   assertEquals(6, startDate.get(Calendar.DATE) - endDate.get(Calendar.DATE));

    }

    @Test
    public void testThrowException () {
        try {
            Week week = new Week();
            fail("did not throw error");
        } catch (IllegalArgumentException e) {

        }

    }

}
