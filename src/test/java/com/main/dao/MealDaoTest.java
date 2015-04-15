package com.main.dao;

import com.main.base.BaseTest;
import com.main.model.Customer;
import com.main.model.Meal;
import com.main.plugins.date.Week;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * PackALunch
 * Created by sadra on 11/15/14.
 */
@DatabaseSetup("customerDaoTest.xml")
public class MealDaoTest extends BaseTest {

    @Autowired
    private MealDao mealDao;

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSaveMealList () {
        Customer customer1 = customerDao.findOne(3);

        Meal meal1 = new Meal();
        meal1.setDate(new Date()).setQuantity(10).setCustomer(customer1);
        Meal meal2 = new Meal();
        meal2.setDate(new Date()).setQuantity(1).setCustomer(customer1);
        Meal meal3 = new Meal();
        meal3.setDate(new Date()).setQuantity(1).setCustomer(customer1);

        List <Meal> mealList = new ArrayList <Meal>();
        mealList.add(meal1);
        mealList.add(meal2);
        mealList.add(meal3);

        mealDao.save(mealList);

        for (Meal meal : mealList) {
           assertNotNull(meal.getId());
        }

    }

    @Test
    public void testFindCustomerMeals(){
//        Customer customer = customerDao.findOne(1);
//        System.out.println("========================BETFORE");
//
//        Week week = new Week (2);
//        Date startDate = week.getDate(Calendar.SUNDAY).getTime();
//        Date endDate   = week.getDate(Calendar.SATURDAY).getTime();
//
//        List<Meal> mealList = mealDao.findByCustomerAndDateBetween(customer, startDate, endDate);
//        System.out.println("========================AFTER");
//        for (Meal meal : mealList) {
//            System.out.println(meal.toString());
//        }
    }

    @Test
    public void testFindMealsInWeek () {
        Week week = new Week (2);
        Date startDate = week.getDate(Calendar.SUNDAY).getTime();
        Date endDate   = week.getDate(Calendar.SATURDAY).getTime();
        List<Meal> mealList = mealDao.findByDateBetween(startDate, endDate);
        assertEquals(3, mealList.size());
        for (Meal meal : mealList) {
            assertNotNull(meal.getCustomer());
        }
    }

}
