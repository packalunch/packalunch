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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 11/2/14.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    static Logger log = Logger.getLogger(CustomerService.class.getName());
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private MealDao mealDao;
    @Autowired
    private DatePlugin datePlugin;

    /**
     * always return 7 day list.
     * @param Customer
     * @param week
     * @return
     */
    @Override
    public List <Day> getDinerSchedule(Customer customer, Week week) {

        Date startDate = week.getDate(Calendar.SUNDAY).getTime();
        Date endDate   = week.getDate(Calendar.SATURDAY).getTime();

        log.info("startDate:"+ startDate);
        log.info("endDate:"+ endDate);
        List <Meal> meals = customer.getMealList();// mealDao.listByCustomerByRange(customerId, startDate, endDate);

        List <Day> dinerSchedule = week.getWeekList();

        for (Day day : dinerSchedule) {
            for (Meal meal : meals) {
                if (meal.getDate().compareTo(day.getTime()) == 0) {
                    day.setMeal(meal);
                log.info("meal:: " + meal);
                }
            }
            day.setMeal(null);
            log.info("DAY::" + day.toString());
        }
        return dinerSchedule;
    }
}
