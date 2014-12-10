package com.curry.service;

import com.curry.base.BaseTest;
import com.curry.model.Customer;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


@DatabaseSetup("customerDaoTest.xml")
public class CustomerServiceImplTest extends BaseTest{

    private static final Logger log = Logger.getLogger(CustomerServiceImplTest.class);

    @Resource(name = "customerService")
    private CustomerService customerService;

    @Test
    public void testGetDinerSchedule() throws Exception {

        Customer customer1 = new Customer();
        customer1.setId(1);

        Week week = new Week(50);

        List <Day> dinerSchedule = customerService.getDinerSchedule(customer1, week);

        assertEquals(7, dinerSchedule.size());

        int mealCount =0;
        for (Day day : dinerSchedule) {
            log.info("DAY MEAL:: " + day.getMeal());
            if (null != day.getMeal())
                mealCount++;
        }

        assertEquals("meal count missing", 2, mealCount);

    }

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