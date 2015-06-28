package com.main.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.main.base.BaseTest;
import com.main.model.product.Plan;
import com.main.model.user.Customer;
import com.main.model.user.CustomerPlan;
import com.main.model.user.Supplier;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * PackALunch
 * Created by sadra on 11/15/14.
 */
@DatabaseSetup("userDaoTest.xml")
public class CustomerPlanDaoTest extends BaseTest {

    private static final Logger log = Logger.getLogger(CustomerPlanDaoTest.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private PlanDao planDao;
    @Autowired
    private CustomerPlanDao customerPlanDao;

    @Test
    public void testFindByCustomer () {
        Customer customer = (Customer) userDao.findOne(1);
        assertNotNull(customer.getCustomerPlanList());
        assertEquals(2, customer.getCustomerPlanList().size());
    }

    @Test
    public void testFindByPlan () {
        Plan plan = planDao.findOne(3);
        Set <CustomerPlan> customerPlanSet = customerPlanDao.findByPlan(plan);
        assertNotNull(customerPlanSet);
        assertEquals(3, customerPlanSet.size());
        for(CustomerPlan customerPlan : customerPlanSet)
            assertNotNull(customerPlan.getUser().getId());
    }

}
