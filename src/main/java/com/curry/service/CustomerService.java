package com.curry.service;

import com.curry.model.Customer;
import com.curry.model.dto.CustomerDto;
import com.curry.model.dto.DinerDto;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 11/2/14.
 */
@Service
public interface CustomerService {

    public CustomerDto findCustomerById (int id);
    public CustomerDto findByUsername (String userName);
    public Customer getCustomerById (int id);
    public List <CustomerDto> findCustomers ();
    public Customer saveCustomer (CustomerDto customerDto);
    public Customer updateCustomer (CustomerDto customerDto);
    public void deleteCustomer (int id);

    public List <DinerDto> getDiners (Week week);

    public void savePayment(DinerDto dinerDto);
}
