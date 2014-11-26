package com.curry.dao;

import com.curry.base.BaseTest;
import com.curry.model.Customer;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * CurryWithAri
 * Created by sadra on 11/15/14.
 */
public class MealDaoTest extends BaseTest {

    @Resource(name = "mealDao")
    private MealDaoImpl mealDao;

    @Test
    public void testListMeal () {
//        Customer customer1 = new Customer();
//        customer1.setFirst_name("david").setLast_name("gilmour").setAddress("address st");
//
//        customerDao.save(customer1);
//
//        Customer customerActual = customerDao.findById(4);
//        assertEquals (customer1.getFirst_name(), customerActual.getFirst_name());
    }



}
