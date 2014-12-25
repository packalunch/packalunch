package com.curry.controller;

import com.curry.model.Customer;
import com.curry.model.dto.CustomerDto;
import com.curry.service.CustomerService;
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
        System.out.println("in delete ::::::::::" + id);
        customerService.deleteCustomer(id);
    }


//    @Transactional
//    @RequestMapping(value = "ajax/customer/{customerName}", method = RequestMethod.GET)
//    public @ResponseBody List<CustomerInputDto> getCustomer(@RequestParam String customerName) {
//
//        System.out.println("++++++++++++++CUSTOMER NAME " + customerName);
//        List <Customer> customerList = customerDao.list();
//        List <CustomerInputDto> customerInputDtoList = new ArrayList<CustomerInputDto>();
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
