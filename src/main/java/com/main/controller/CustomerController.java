package com.main.controller;

import com.main.model.user.User;
import com.main.model.dto.CustomerDto;
import com.main.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * sadra
 * Created by sadra on 12/10/14.
 */
@Controller
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Transactional
    @RequestMapping(value = "api/customer", method = RequestMethod.GET)
    public @ResponseBody List <CustomerDto> getCustomers () {
        return customerService.findCustomers();
    }

    @Transactional
    @RequestMapping(value = "api/customer/{id}", method = RequestMethod.GET)
    public @ResponseBody CustomerDto getCustomer (@PathVariable("id") int id) {
        return customerService.findCustomerById(id);
    }


    @Transactional
    @RequestMapping(value = "api/customer", method = RequestMethod.POST)
    public @ResponseBody int saveCustomer (@RequestBody CustomerDto customerDto) {
        User user = customerService.saveCustomer(customerDto);
        return user.getId();
    }

    @Transactional
    @RequestMapping(value = "api/customer/{id}", method = RequestMethod.PUT)
    public @ResponseBody int updateCustomer (@RequestBody CustomerDto customerDto) {
        User user = customerService.updateCustomer(customerDto);
        return user.getId();
    }

    @Transactional
    @RequestMapping(value = "api/customer/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer (@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
    }




    @Transactional
    @RequestMapping(value = "ajax/customer/{customerName}", method = RequestMethod.GET)
    public @ResponseBody List<CustomerDto> getCustomer(@RequestParam String customerName) {
        //todo filter by query...
        return customerService.findCustomers();
    }



}
