package com.main.service;

import com.main.model.user.User;
import com.main.model.dto.CustomerDto;
import com.main.model.dto.DinerDto;
import com.main.plugins.date.Week;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PackALunch
 * Created by sadra on 11/2/14.
 */
@Service
public interface CustomerService {

    CustomerDto findCustomerById (int id);
    CustomerDto findByUsername (String userName);
    User getCustomerById (int id);
    List <CustomerDto> findCustomers ();
    User saveCustomer (CustomerDto customerDto);
    User updateCustomer (CustomerDto customerDto);
    void deleteCustomer (int id);

    List <DinerDto> getDiners (Week week);

    void savePayment(DinerDto dinerDto);

    DinerDto getDiner(int id, Week week);

    User saveFacebookCustomer (CustomerDto customerDto);
}
