package com.curry.dao;

import com.curry.base.BaseTest;
import com.curry.model.Customer;
import com.curry.model.Meal;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * CurryWithAri
 * Created by sadra on 11/15/14.
 */
@DatabaseSetup("customerDaoTest.xml")
public class MealDaoTest extends BaseTest {

    @Resource(name = "mealDao")
    private MealDaoImpl mealDao;

    @Test
    public void testSaveMealList () {
        Customer customer1 = new Customer();
        customer1.setId(1);

        Meal meal1 = new Meal();
        meal1.setDate(new Date()).setQuantity(10).setCustomer(customer1);
        Meal meal2 = new Meal();
        meal2.setDate(new Date()).setQuantity(1).setCustomer(customer1);
        Meal meal3 = new Meal();
        meal3.setDate(new Date()).setQuantity(1).setCustomer(customer1);

        List <Meal> mealList = new ArrayList ();
        mealList.add(meal1);
        mealList.add(meal2);
        mealList.add(meal3);

//        mealDao.save(meal1);
        mealDao.save(mealList);
//
        for (Meal meal : mealList) {
           assertNotNull(meal.getId());
           System.out.println(meal.toString());
        }
    }

    @Test
    public void testListMeal () {


    }



}
