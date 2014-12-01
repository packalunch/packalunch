package com.curry.dao;

import com.curry.base.BaseTest;
import com.curry.model.Customer;
import com.curry.model.Meal;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * CurryWithAri
 * Created by sadra on 11/15/14.
 */
@DatabaseSetup("customerDaoTest.xml")
public class CustomerDaoTest extends BaseTest {


    @Resource(name = "customerDao")
    private CustomerDaoImpl customerDao;

    @Test
    public void testListCustomer () {
        Customer customer1 = new Customer();
        customer1.setFirst_name("david").setLast_name("gilmour");

        List<Customer> customerList = customerDao.list();
        for (Customer customer : customerList) {
            System.out.println(customer.getFirst_name() + " " + customer.getLast_name());
        }

        assertEquals(3, customerList.size());
        assertEquals (customer1.getFirst_name(), customerList.get(0).getFirst_name());
    }

    @Test
    public void testGetCustomer () {
        Customer customer1 = new Customer();
        customer1.setFirst_name("david").setLast_name("gilmour");
        Customer customer = customerDao.findById(1);
        assertEquals (customer.getFirst_name(), customer1.getFirst_name());
    }

    @Test
    public void testSaveCustomer () {
        Customer customer1 = new Customer();
        customer1.setFirst_name("davidTest").setLast_name("gilmourTest").setAddress("address st");
        customerDao.save(customer1);
        assertNotNull(customer1.getId());
    }

//    @Test
//    public void testSaveCustomerWithMeal () {
//        Customer customer1 = new Customer();
//        customer1.setFirst_name("david").setLast_name("gilmour").setAddress("address st");
//        Meal meal1 = new Meal();
//        meal1.setDate(new Date()).setQuantity(10).setCustomer(customer1);
//        Meal meal2 = new Meal();
//        meal2.setDate(new Date()).setQuantity(1).setCustomer(customer1);
//        Meal meal3 = new Meal();
//        meal3.setDate(new Date()).setQuantity(1).setCustomer(customer1);
//
//        List <Meal> mealList = new ArrayList<Meal>();
//        mealList.add(meal1);
//        mealList.add(meal2);
//        mealList.add(meal3);
//
//        customer1.setMealList(mealList);
//
//        customerDao.save(customer1);
//        Customer customerActual = customerDao.findById(4);
//        assertEquals (customer1.getFirst_name(), customerActual.getFirst_name());
//        assertNotNull(customerActual.getMealList());
//
//        assertEquals(3, customerActual.getMealList().size());
//
//        System.out.println("------------" + customerActual.getMealList().get(1).getCustomer().getLast_name());
//    }



}
