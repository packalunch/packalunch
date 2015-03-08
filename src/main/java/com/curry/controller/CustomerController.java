package com.curry.controller;

import com.curry.model.Customer;
import com.curry.model.dto.CustomerDto;
import com.curry.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * CurryWithAri
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
        Customer customer = customerService.saveCustomer(customerDto);
        return customer.getId();
    }

    @Transactional
    @RequestMapping(value = "api/customer/{id}", method = RequestMethod.PUT)
    public @ResponseBody int updateCustomer (@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.updateCustomer(customerDto);
        return customer.getId();
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


    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        LOGGER.debug("in login");

//        if (error != null) {
//            LOGGER.debug("error", "Invalid username and password!");
//        }
//
//        if (logout != null) {
//            LOGGER.debug("msg", "You've been logged out successfully.");
//        }


        return "index.html";
    }
//
//    @RequestMapping(value = "login/authenticate", method = RequestMethod.POST)
//    public String authenticate(){
//        LOGGER.debug( "in authenticate");
//        return "something";
//    }

}
