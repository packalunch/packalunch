package com.curry.service;

import com.curry.base.BaseTest;
import com.curry.dao.CustomerDao;
import com.curry.model.Customer;
import com.curry.model.dto.CustomerDto;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@DatabaseSetup("customerDaoTest.xml")
public class CustomerServiceImplTest extends BaseTest{

    private static final Logger log = Logger.getLogger(CustomerServiceImplTest.class);

    @Resource(name = "customerService")
    private CustomerService customerService;

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSaveCustomer () {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirst_name("martin")
                .setLast_name("heidegger")
                .setTelephone("111-111-1111")
                .setAddress("being and time");

        Customer customerActual = customerService.saveCustomer(customerDto);

        assertNotNull("customer save Id is missing", customerActual.getId());
        assertEquals(customerDto.getFirst_name(), customerActual.getFirst_name());
        assertEquals(customerDto.getLast_name(), customerActual.getLast_name());
        assertEquals(customerDto.getTelephone(), customerActual.getTelephone());
        assertEquals(customerDto.getAddress(), customerActual.getAddress());

        log.debug(customerActual.toString());

    }

    @Test
    public void testGetDinerSchedule() {

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


}