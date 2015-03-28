package com.curry.service;

import com.curry.base.BaseTest;
import com.curry.dao.CustomerDao;
import com.curry.model.Customer;
import com.curry.model.dto.CustomerDto;
import com.curry.model.dto.DinerDto;
import com.curry.model.dto.MealDayDto;
import com.curry.plugins.date.Week;
import com.curry.service.CustomerService;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@DatabaseSetup("customerDaoTest.xml")
public class CustomerServiceTest extends BaseTest {

    private static final Logger log = Logger.getLogger(CustomerServiceTest.class);

    @Autowired
    private CustomerService customerService;


    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSaveCustomer () {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirst_name("martin")
                .setLast_name("heidegger")
                .setEmail("martin@heidegger.com")
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
    public void testGetDiners () {
        Week week = new Week(2);
        List<DinerDto> dinerDtoList = customerService.getDiners(week);
        for (DinerDto dinerDto : dinerDtoList)
        {
            System.out.println("======================="+dinerDto.toString());
        }
        assertEquals(2, dinerDtoList.size());
    }
//
//    @Test
//    public void testSavePayment () {
//        //todo
//    }
//
//    @Test
//    public void testGetDinerSchedule() {
//
//        Customer customer1 = new Customer();
//        customer1.setId(1);
//
//        Week week = new Week(2);
//
//        List <MealDayDto> dinerSchedule = customerService.getDinerSchedule(customer1, week);
//
//        assertEquals(7, dinerSchedule.size());
//
//        int mealCount =0;
//        for (MealDayDto day : dinerSchedule) {
//            if (day.isAvailable())
//                mealCount++;
//        }
//
//        assertEquals("meal count missing", 2, mealCount);
//
//    }


}