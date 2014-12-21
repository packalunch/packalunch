package com.curry.controller;

import com.curry.dao.CustomerDao;
import com.curry.model.Customer;
import com.curry.model.dto.CustomerInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 12/10/14.
 */
@Controller
public class MealController {

    @Autowired
    private CustomerDao customerDao;
//
//    @Transactional
//    @RequestMapping(value = "ajax/customer/{customerName}", method = RequestMethod.GET)
//    public @ResponseBody List<CustomerInputDto> getCustomer(@RequestParam String customerName) {
//
//        System.out.println("++++++++++++++CUSTOMER NAME " + customerName);
//        List <Customer> customerList = customerDao.list();
//        List <CustomerInputDto> customerInputDtoList = new ArrayList<>();
//
//        for (Customer customer : customerList) {
//            CustomerInputDto customerInputDto = new CustomerInputDto();
//            customerInputDto.setFirstName(customer.getFirst_name())
//                    .setLastName(customer.getLast_name())
//                    .setId(customer.getId());
//            customerInputDtoList.add(customerInputDto);
//
//            System.out.println("++++++++++++++CUSTOMER NAME " + customerInputDto.toString());
//        }
//        return customerInputDtoList;
//
//    }

}
