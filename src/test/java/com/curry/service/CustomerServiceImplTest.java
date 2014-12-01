package com.curry.service;

import com.curry.model.Customer;
import com.curry.model.Meal;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(value = "testApplicationContext.xml")
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
//        DbUnitTestExecutionListener.class })
public class CustomerServiceImplTest {

//    @Test
//    public void testGetDinerSchedule() throws Exception {

//        Customer customer1 = getCustomer();
//
//        Calendar now = Calendar.getInstance();
//        int weekNumber = now.get(Calendar.WEEK_OF_YEAR);
//
//        Week week = new Week(weekNumber);
//        Date startDate = week.getDate(Calendar.SUNDAY).getTime();
//        Date endDate   = week.getDate(Calendar.SATURDAY).getTime();

//        List <Meal> meals = customer1.getMealList();
//
//        List <Day> dinerSchedule = week.getWeekList();

//        for (Day day : dinerSchedule) {
//            for (Meal meal : meals) {
//                if (meal.getDate().compareTo(day.getTime()) == 0) {
//                    day.setMeal(meal);
//                }
//            }
//            day.setMeal(null);
//        }
//
//    }
//
//    private Customer getCustomer() {
//        Customer customer1 = new Customer();
//        customer1.setFirst_name("david").setLast_name("gilmour").setAddress("address st");
//        Meal meal1 = new Meal();
//        meal1.setDate(new Date()).setQuantity(10).setCustomer(customer1);
//        Meal meal2 = new Meal();
//        meal2.setDate(new Date()).setQuantity(1).setCustomer(customer1);
//        Meal meal3 = new Meal();
//        meal3.setDate(new Date()).setQuantity(1).setCustomer(customer1);
//
//        List<Meal> mealList = new ArrayList<Meal>();
//        mealList.add(meal1);
//        mealList.add(meal2);
//        mealList.add(meal3);
//
//        return customer1;
//    }
}