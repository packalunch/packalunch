package com.main.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.main.base.BaseTest;
import com.main.model.product.Meal;
import com.main.model.product.Plan;
import com.main.model.user.Supplier;
import com.main.model.user.User;
import com.main.plugins.date.Week;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * PackALunch
 * Created by sadra on 11/15/14.
 */
@DatabaseSetup("userDaoTest.xml")
public class PlanDaoTest extends BaseTest {

    private static final Logger log = Logger.getLogger(PlanDaoTest.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlanDao planDao;

    @Test
    public void testSavePlan () {
        Supplier supplier = (Supplier) userDao.findOne(6);
        assertNotNull(supplier);

        Plan plan = new Plan();
        plan.setUser(supplier)
                .setName("test plan 5")
                .setNumberOfMeals(5)
                .setIsAvailable(true)
                .setUnitPrice(7.8);
        Plan plan2 = new Plan();
        plan2.setUser(supplier)
                .setName("test plan 5")
                .setNumberOfMeals(5)
                .setIsAvailable(true)
                .setUnitPrice(7.8);

        List <Plan> planList = new ArrayList<>();
        planList.add(plan);
        planList.add(plan2);

        planDao.save(planList);

        Supplier supplierExpected = (Supplier) userDao.findOne(6);
        assertNotNull(supplierExpected.getPlanList());
        assertNotNull(supplierExpected.getPlanList().get(0));

        log.debug(supplierExpected.getPlanList().get(0).toString());
    }


}
