package com.curry.dao;

import com.curry.base.BaseTest;
import com.curry.model.Customer;
import com.curry.model.Meal;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSaveCustomer () {
        Customer customer1 = new Customer();
        customer1.setFirst_name("davidTest").setLast_name("gilmourTest").setAddress("address st");
        customerDao.save(customer1);
        assertNotNull(customer1.getId());
    }

    @Test
    public void testUpdateCustomer () {
        Customer customer1 = new Customer();
        customer1.setFirst_name("FTest").setLast_name("LTest").setAddress("address st");
        customer1.setId(2);
        customerDao.update(customer1);
        Customer actual =  customerDao.findById(2);

        assertEquals(actual.getFirst_name(), customer1.getFirst_name());

    }

    @Test
    public void testListCustomer () {
        Customer customer1 = new Customer();
        customer1.setFirst_name("david").setLast_name("gilmour");

        List<Customer> customerList = customerDao.list();

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
    public void testGetCustomerWithAccount () {
        Customer customer = customerDao.findById(1);
        assertNotNull(customer.getAccount());
    }


}
