package com.curry.service;

import com.curry.dao.CustomerDao;
import com.curry.dao.MealDao;
import com.curry.model.Customer;
import com.curry.model.Meal;
import com.curry.plugins.date.DatePlugin;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 11/2/14.
 */
@Service (value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    static Logger log = Logger.getLogger(CustomerService.class);
//    @Autowired
//    private CustomerDao customerDao;
    @Autowired
    private MealDao mealDao;
//    @Autowired
//    private DatePlugin datePlugin;

    /**
     * always return 7 day list.
     * @param customer
     * @param week
     * @return
     */
    @Override
    public List <Day> getDinerSchedule(Customer customer, Week week) {

        Date startDate = week.getDate(Calendar.SUNDAY).getTime();
        Date endDate   = week.getDate(Calendar.SATURDAY).getTime();

        log.info("startDate:"+ startDate);
        log.info("endDate:"+ endDate);
        List <Meal> meals = mealDao.listByCustomerByRange(customer.getId(), startDate, endDate);

        List<Day> weekSchedule = getDaySchedule(week, meals);

        return weekSchedule;
    }

    private List<Day> getDaySchedule(Week week, List<Meal> meals) {
        List <Day> weekSchedule = week.getWeekList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Day day : weekSchedule) {
            for (Meal meal : meals) {
                //todo refactor if possible
                if (sdf.format(meal.getDate())
                        .equalsIgnoreCase(sdf.format(day.getDate()))) {
                    day.setMeal(meal);
                    day.setAvailabe(true);
                    log.info("meal::: " + meal);
                }
            }
            log.info("DAY:: " + day.toString());
        }
        return weekSchedule;
    }
}
