package com.main.service;

import com.main.base.BaseTest;
import com.main.dao.UserDao;
import com.main.model.user.User;
import com.main.model.dto.CustomerDto;
import com.main.model.dto.DinerDto;
import com.main.plugins.date.Week;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@DatabaseSetup("userDaoTest.xml")
public class UserServiceTest extends BaseTest {

    private static final Logger log = Logger.getLogger(UserServiceTest.class);

    @Autowired
    private CustomerService customerService;


    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveCustomer () {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirst_name("martin")
                .setLast_name("heidegger")
                .setEmail("martin@heidegger.com")
                .setTelephone("111-111-1111")
                .setAddress("being and time");

        User userActual = customerService.saveCustomer(customerDto);

        assertNotNull("customer save Id is missing", userActual.getId());
        assertEquals(customerDto.getFirst_name(), userActual.getFirst_name());
        assertEquals(customerDto.getLast_name(), userActual.getLast_name());
        assertEquals(customerDto.getTelephone(), userActual.getTelephone());
        assertEquals(customerDto.getAddress(), userActual.getAddress());

        log.debug(userActual.toString());

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